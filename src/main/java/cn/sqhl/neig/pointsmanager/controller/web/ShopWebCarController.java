package cn.sqhl.neig.pointsmanager.controller.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.po.NsGoods;
import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.po.NsUserPurse;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.service.CouponService;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.service.OrderService;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.service.UserPurseService;
import cn.sqhl.neig.pointsmanager.service.UserService;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
import cn.sqhl.neig.pointsmanager.vo.Address;
import cn.sqhl.neig.pointsmanager.vo.Goods;
import cn.sqhl.neig.pointsmanager.vo.web.ErrorInfo;


@Controller
@RequestMapping("/shopcar_web")
public class ShopWebCarController extends basicInfo{
	private Logger logger=LogManager.getLogger(ShopWebCarController.class);
	
	@Autowired
	private ShopCarService shopCarService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private UserService userServices;
	@Autowired
	private AddressService addressServices;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserPurseService userPurseService;
	
	@RequestMapping("/search")
	public String queryShopCar(HttpServletRequest request,
			HttpServletResponse response,Model model) throws IOException{
		ErrorInfo einfo;
		List list;
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		System.out.println("----用户余额为"+user.getUserKyBalance());
		if(user!=null){
			
			
			
			//根据用户查询收货地址
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", user.getId());
			
			List<Address> ads=addressServices.queryObj(map);
			model.addAttribute("ads", ads);
			
			
			list=shopCarService.selectList(map);
			//查询优惠券
			List<NsUserCoupon> myCouponList= couponService.selectByUserId(user.getId(), null);
			model.addAttribute("myCouponList", myCouponList);
			model.addAttribute("baseimg", baseimg);
			model.addAttribute("cartlist", list);
			model.addAttribute("userid", user.getId());
			return "/jsp/order/shopcart";
		}else{
			einfo=new ErrorInfo();
			einfo.setResult("1");
			einfo.setMessage("查询失败");
			einfo.setCause("请先登录后 在进行查看~");
			model.addAttribute("result", einfo);
			return "/login";
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/addShopCar")
	public JSONObject addShopCar(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="goodsid",required=false) Long goodsid,
			@RequestParam(value="buynumb",required=false) int buynumb){
		
		JSONObject rsJson = new JSONObject();
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		
		int result =0;
		int i=0;
		if(user!=null){
			
			Map map = new HashMap();
			map.put("userid", user.getId());
			map.put("goodsid", goodsid);
			List list= new ArrayList();
			list = shopCarService.queryObj(map);
			NsCart nsCart =new NsCart();
			if(list.size()>0){
				nsCart = (NsCart) list.get(0);
				nsCart.setCount(buynumb);
				shopCarService.updateObj(nsCart);//更新数量
			}else{
				nsCart.setGoodsid(goodsid);
				nsCart.setCount(buynumb);
				nsCart.setUserid(user.getId());
				nsCart.setCreateTime(new Date());
				i= shopCarService.addObj(nsCart);  //插入一条
				list = shopCarService.queryObj(map);
				nsCart=(NsCart)list.get(0);
			}
			  result =1;
			  System.err.println("nsCartid:"+nsCart.getId());
			  rsJson.put("cartId", nsCart.getId());
			
		}else{
			 result =2;
		}
		
		
		rsJson.put("msg", result);
		return rsJson;
		
	}
	
	@RequestMapping("/pay")
	public String pay(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		ErrorInfo einfo;
		List list;
		
		
		String cartid = request.getParameter("cartid").trim(); 
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			
			//根据用户查询收货地址
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", user.getId());
			
			List<Address> ads=addressServices.queryObj(map);
			model.addAttribute("ads", ads);
			
			map.put("cartid", cartid);
			list=shopCarService.selectList(map);
			
			
			model.addAttribute("baseimg", baseimg);
			model.addAttribute("cartlist", list);
			model.addAttribute("userid", user.getId());
			return "/jsp/order/shopcart";
			
		}else{
			einfo=new ErrorInfo();
			einfo.setResult("1");
			einfo.setMessage("失败");
			einfo.setCause("请先登录后 在进行~");
			model.addAttribute("result", einfo);
			return "/login";
		}
		
	}
	
	
	
	@RequestMapping("/paycar")
	public String paycar(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		ErrorInfo einfo;
		List list;
		String[] cardids = request.getParameterValues("cartid");
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			//根据用户查询收货地址
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userid", user.getId());
				
//				List<Address> ads=addressServices.queryObj(map);
//				model.addAttribute("ads", ads);

			//根据用户查询优惠卷	
			//根据商品ID查询商品对象 返回一个list
				List<Map>  goodsList = new ArrayList<Map>();
				
				BigDecimal sumbalance = new BigDecimal("0"); //总额
				int sumcount = 0;//商品的总数量
				
				//for循环 根据 cartID获取当前记录
				for(int i=0;i<cardids.length;i++){
					
					Map<String, Object> cartMap = new HashMap<String, Object>();
					
					cartMap.put("id", cardids[i]);
					cartMap.put("userid", user.getId());
					
				List cartList = shopCarService.queryObj(cartMap); 
				NsCart nsCar = (NsCart)cartList.get(i);	
			    Goods goods = goodsService.queryObj(nsCar.getGoodsid());
			    System.err.println("购物车id为+"+cardids[i]+"---"+i);
			    System.err.println("商品价格为+"+goods.getPrice()+"---"+i);
			    System.err.println("商品数量为+"+nsCar.getCount()+"---"+i);
			    sumbalance = sumbalance.add(goods.getPrice().multiply(new BigDecimal(nsCar.getCount())));	    
			    sumcount += nsCar.getCount();
			    
			    
			    
			    System.out.println("第"+i+"个订单金额："+sumbalance);
//			    Map goodsMap = new HashMap();
//			    goodsMap.put("goods", goods);
//			    goodsMap.put("shuliang", nsCar.getCount());
			    
			    //goodsList.add(goodsMap);
				}
				System.out.println("最后的商品价格："+sumbalance);
				System.out.println("最后的商品总数："+ sumcount);
				
				//判断总额-账户余额 >0  执行下面方法：
				
				//生成订单  订单编号 更新订单金额 数量 支付金额 订单状态
				String orderSn = FormatUtils.getOrderIdByUUId();
				NsOrder nsOrder = new NsOrder();
				nsOrder.setUserid(user.getId());
				nsOrder.setName(orderSn);
				nsOrder.setPaytype("1");
				nsOrder.setAddress("地址");
				nsOrder.setCreateTime(new Date());
				nsOrder.setCounts(sumcount); //总数量
				nsOrder.setTotal(sumbalance);   //总金额
				nsOrder.setOrderstatus("1"); //待付款
				nsOrder.setOutway("1");
				nsOrder.setPaynumb(orderSn);
				orderService.addObj(nsOrder);
				
				
				//保存  根据 orderSn 查询出  order表的id
				Map<String,Object> ordermap = new HashMap<String, Object>();
				ordermap.put("name", orderSn);
				List orderlist=orderService.queryObj(ordermap);
				if (orderlist.size()>0) {
					nsOrder=(NsOrder) orderlist.get(0);
				}
				
				
				
				//for循环 根据 cartID获取当前记录
				for(int i=0;i<cardids.length;i++){
					
					Map<String, Object> cartMap = new HashMap<String, Object>();
					cartMap.put("id", cardids[i]);
					cartMap.put("userid", user.getId());
					
				List cartList = shopCarService.queryObj(cartMap); 
				
				NsCart nsCar = (NsCart)cartList.get(i);	
			    Goods goods = goodsService.queryObj(nsCar.getGoodsid());			    			    			    			    			    
			    NsOrderDetail detail=new NsOrderDetail();
			    detail.setCount(nsCar.getCount());
			    detail.setGoodsid(nsCar.getGoodsid());
			    detail.setOrderid(nsOrder.getId());
			    detail.setPrice(goods.getPrice());
			    orderService.insertOrderDetail(detail);
			    
			    System.out.println("数量"+nsCar.getCount()+"单价"+goods.getPrice());
//			    Map goodsMap = new HashMap();
//			    goodsMap.put("goods", goods);
//			    goodsMap.put("shuliang", nsCar.getCount());
			    
			    //goodsList.add(goodsMap);
				}
				System.out.println("总额为"+sumbalance+"----用户余额为"+user.getUserKyBalance());
				//判断用户可用余额减去订单总额大于等于0 购买成功
				int r=user.getUserKyBalance().subtract(sumbalance).compareTo(BigDecimal.ZERO);
				
				if(r==0||r==1){
					//扣钱
					NsUser nsuser=new NsUser();
					nsuser.setUserKyBalance(user.getUserKyBalance().subtract(sumbalance));
					nsuser.setId(user.getId());
					int code=0;
					code=userServices.updateObj(nsuser);
					if(code>0){
						//修改成功
						System.out.println("扣钱成功------------");
						//修改订单状态为已付款
						nsOrder.setOrderstatus("2");
						orderService.updateObj(nsOrder);
						
						
						//从购物车中删除这条记录  根据 userid  goodsid
						for (int i = 0; i < cardids.length; i++) {
							shopCarService.removeByUserId(Long.valueOf(cardids[i]), user.getId());	
						}
						//写钱包流水日志
					
						NsUserPurse purse=new NsUserPurse();
						purse.setTradeType("1");
						purse.setTradeAmount(sumbalance);
						purse.setTradeSn(orderSn);
						purse.setTradeState("2");
						purse.setOptionType("1");
						purse.setUserAmount(user.getUserKyBalance().subtract(sumbalance));
						purse.setUserId(user.getId());
						purse.setPurseType(0);
						purse.setPurseStatus(user.getUserStatus().toString());
						purse.setCreateTime(new Date());
						purse.setOptionAdminid(Long.valueOf("1"));
						userPurseService.addObj(purse);
						System.out.println("流水日志------------");
						//刷新Session
						request.getSession().setAttribute("user", userServices.queryByUserPhone(user.getUserPhone(), null));
						
						
					}else{
						//余额不足跳转充值页面
						
					}	
				}
				
				 request.setAttribute("baseimg",  baseimg);  
				 request.setAttribute("goodList", goodsList);
				//sucess页面显示订单结果
				return "/jsp/pay/success";
			
		}else{
			einfo=new ErrorInfo();
			einfo.setResult("1");
			einfo.setMessage("失败");
			einfo.setCause("请先登录后 在进行~");
			model.addAttribute("result", einfo);
			return "/login";
		}
		
	}
	
	
//	@RequestMapping("/gotopay")
//	public String gotopay(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
//		ErrorInfo einfo;
//		List list;
//		
//		NsUser user=(NsUser) request.getSession().getAttribute("user");
//		if(user!=null){
//			
//			//生成订单  订单编号 更新订单金额 数量 支付金额 订单状态
//			
//			
//			//从购物车中删除这条记录  根据 userid  goodsid
//
//			//sucess页面显示订单结果
//			
//			
//			
//			return "/jsp/pay/success";
//		}else{
//			einfo=new ErrorInfo();
//			einfo.setResult("1");
//			einfo.setMessage("失败");
//			einfo.setCause("请先登录后 在进行~");
//			model.addAttribute("result", einfo);
//			return "/login";
//		}
//		
//	}

}

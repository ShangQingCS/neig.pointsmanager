package cn.sqhl.neig.pointsmanager.controller.web;

import java.io.IOException;
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
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
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
	private AddressService addressServices;
	
	@RequestMapping("/search")
	public String queryShopCar(HttpServletRequest request,
			HttpServletResponse response,Model model) throws IOException{
		ErrorInfo einfo;
		List list;
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			
			
			
			//根据用户查询收货地址
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", user.getId());
			
			List<Address> ads=addressServices.queryObj(map);
			model.addAttribute("ads", ads);
			
			
			list=shopCarService.selectList(map);
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
			
			map.put("id", cartid);
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
				
				List<Address> ads=addressServices.queryObj(map);
				model.addAttribute("ads", ads);

			//根据用户查询优惠卷
			
			//根据商品ID查询商品对象 返回一个list
				List<Map>  goodsList = new ArrayList<Map>();
				
				//for循环 根据 cartID获取当前记录
				for(int i=0;i<cardids.length;i++){
					
					Map<String, Object> cartMap = new HashMap<String, Object>();
					cartMap.put("cartid", cardids[i]);
					
				List cartList = shopCarService.queryObj(cartMap);
				NsCart nsCar = (NsCart)cartList.get(0);	
			    Goods goods = goodsService.queryObj(nsCar.getGoodsid());
			    
			    Map goodsMap = new HashMap();
			    goodsMap.put("goods", goods);
			    goodsMap.put("shuliang", nsCar.getCount());
			    
			    goodsList.add(goodsMap);
				}
				
				
				
				//生成订单  订单编号 更新订单金额 数量 支付金额 订单状态
				
				
				
				
				
				//从购物车中删除这条记录  根据 userid  goodsid

				//sucess页面显示订单结果
				
				
			    request.setAttribute("baseimg",  baseimg);
			    
			    request.setAttribute("goodList", goodsList);
			
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

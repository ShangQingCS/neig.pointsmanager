package cn.sqhl.neig.pointsmanager.controller.web;

import java.io.IOException;
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
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.vo.Address;
import cn.sqhl.neig.pointsmanager.vo.web.ErrorInfo;


@Controller
@RequestMapping("/shopcar_web")
public class ShopWebCarController extends basicInfo{
	private Logger logger=LogManager.getLogger(ShopWebCarController.class);
	
	@Autowired
	private ShopCarService shopCarService;
	@Autowired
	private AddressService addressServices;
	
	@RequestMapping("/search")
	public String queryShopCar(HttpServletRequest request,
			HttpServletResponse response,Model model) throws IOException{
		ErrorInfo einfo;
		List list;
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			Map map=new HashMap();
			map.put("userid", user.getId());
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
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		
		String result ="添加购物车失败";
		
		if(user!=null){
			NsCart nsCart = new NsCart();
			nsCart.setGoodsid(goodsid);
			nsCart.setCount(buynumb);
			nsCart.setUserid(user.getId());
			nsCart.setCreateTime(new Date());
			int i = shopCarService.addObj(nsCart);
			if(i>0){
			  result ="添加购物车成功";
			}
		}else{
			result = null;
		}
		
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
		
	}
	
	@RequestMapping("/pay")
	public String pay(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		ErrorInfo einfo;
		List list;
		
		
		String goodsId = request.getParameter("goodsid");  //商品ID  多个商品用 ,分割  方便用于购物车传递过来的数据
		String buynumb = request.getParameter("buynumb");  //商品数量  多个不同商品用 ,分割 方便用于购物车传递过来的数据
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			
			//生成订单  插入订单表

			
			
			//根据用户查询收货地址
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userid", "4002");
				
				List<Address> ads=addressServices.queryObj(map);
				model.addAttribute("ads", ads);

			//根据用户查询优惠卷
			
			//根据商品ID查询商品对象 返回一个list
			
			
			
			//根据表单中的数量 直接传输
			
			
			
			return "/jsp/pay/pay";
		}else{
			einfo=new ErrorInfo();
			einfo.setResult("1");
			einfo.setMessage("失败");
			einfo.setCause("请先登录后 在进行~");
			model.addAttribute("result", einfo);
			return "/login";
		}
		
	}
	
	
	@RequestMapping("/gotopay")
	public String gotopay(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException{
		ErrorInfo einfo;
		List list;
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			
			//生成订单  订单编号 更新订单金额 数量 支付金额 订单状态

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

}

package cn.sqhl.neig.pointsmanager.controller.web;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.service.OrderService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Order;

@Controller
@RequestMapping("/order_web")
public class OrderWebController extends basicInfo{
	private Logger logger=LogManager.getLogger(OrderWebController.class);
	
	@Autowired
	private OrderService orderService;
	
	
	@RequestMapping("/search")
	public String queryGoods(HttpServletRequest request,
			HttpServletResponse response,Model model) throws IOException{
		
		if(request.getSession().getAttribute("user")!=null){	
			model.addAttribute("baseimg", baseimg);
			request.setAttribute("ordetype", request.getParameter("typeCode"));
			return "/jsp/order/order";
		}else{
			return "/login";
		}
			
	}
	
	@ResponseBody
	@RequestMapping("/order/search")
	public JSONObject GetOrder(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="pagesize",required=false) String pagesize,
			@RequestParam(value="nowpage",required=false) String nowpage){
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		
		JSONObject rsJson = new JSONObject();
		String result="";
		String message="";
		Object data="";
		
		Long userid=user.getId();
		
		if(StringUtils.isBlank(pagesize)){
			pagesize="15";
		}
		if(StringUtils.isBlank(nowpage)){
			nowpage="0";
		}
		
		Map map=new HashMap();
		if(StringUtils.isNotBlank(type)){
			map.put("status", type);
		}
		
		PageCond page=new PageCond(Integer.parseInt(nowpage)*Integer.parseInt(pagesize),Integer.parseInt(pagesize));
		
		map.put("userid", userid);
		
		List<Order> obj=orderService.queryOrder(page, map);
		
		if(obj!=null){
			result="0";
			message="查询成功";
			logger.log(INFO, message);
			data=obj;
		}else{
			result="1";
			message="查询失败 为空请确认无误后再行调用";
			logger.log(INFO, message);
			data="";
		}
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		rsJson.put("page", page);
		response.setContentType("charset=utf-8");
		return rsJson;
		
	}
	
	@RequestMapping("/manager")
	public String eidtOrders(HttpServletRequest request,
			HttpServletResponse response,Model model,
			@RequestParam(value="goodsid",required=false) Long goodsid,
			@RequestParam(value="count",required=false) Integer count) throws IOException{
		model.addAttribute("baseimg", baseimg);
		return "/jsp/order/order";
	}
	
	@ResponseBody
	@RequestMapping("/order/receipt")
	public JSONObject receipt(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="orderid",required=false) String id){		
		JSONObject rsJson = new JSONObject();
		System.out.println(id+"---------------");
		
		NsOrder order= orderService.queryByPrimaryKey(Long.parseLong(id));
		order.setOrderstatus("4");
		int i=orderService.updateObj(order);
		rsJson.put("msg", i);
		return rsJson;
	}
}

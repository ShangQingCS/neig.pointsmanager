package cn.sqhl.neig.pointsmanager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sqhl.neig.pointsmanager.service.OrderService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;

import com.alibaba.fastjson.JSONObject;


@Controller
@RequestMapping("/order")
public class OrderController extends ContextInfo{
	private Logger logger=LogManager.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@ResponseBody
	@RequestMapping("/search")
	public JSONObject queryGoods(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="userid",required=false) Long userid,
			@RequestParam(value="orderid",required=false) Long orderid,
			@RequestParam(value="pagesize",required=false) String pagesize,
			@RequestParam(value="nowpage",required=false) String nowpage) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		
		if(StringUtils.isEmpty(userid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("userid"))){
				String uid=requestString.get("userid")+"";
				userid=Long.parseLong(uid);
			}
		}
		if(StringUtils.isEmpty(orderid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("orderid"))){
				String oid=requestString.get("orderid")+"";
				orderid=Long.parseLong(oid);
			}
		}
		if(StringUtils.isEmpty(pagesize) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("pagesize"))){
				pagesize=requestString.get("pagesize")+"";
			}else{
				pagesize="15";
			}
		}else{
			pagesize="15";
		}
		if(StringUtils.isEmpty(nowpage) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("nowpage"))){
				nowpage=requestString.get("nowpage")+"";
			}else{
				nowpage="1";
			}
		}else{
			nowpage="1";
		}
		List list=null;
		PageCond page=new PageCond(Integer.parseInt(nowpage), Integer.parseInt(pagesize));
		if(StringUtils.isEmpty(userid) && StringUtils.isEmpty(orderid)){
			result="1";
			message="userid 与 orderid  为空请确认无误后再行调用";
			logger.log(INFO, message);
			data="";
		}else{
			Map map=new HashMap();
			if(!StringUtils.isEmpty(userid)){
				map.put("userid",userid);
			}
			if(!StringUtils.isEmpty(orderid)){
				map.put("orderid",orderid);
			}
			list=orderService.queryOrder(page,map);
		}
		if(list!=null && list.size() > 0 ){
			result="0";
			message="查询成功";
			logger.log(INFO, message);
			data=list;
		}else{
			result="1";
			message="查询失败";
			logger.log(INFO, message);
			data=list;
		}
		
		
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
}

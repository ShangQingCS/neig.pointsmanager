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

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsComment;
import cn.sqhl.neig.pointsmanager.service.CommentService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;

@Controller
@RequestMapping("/comment")
public class CommentController extends ContextInfo{
	private Logger logger=LogManager.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping("/search")
	public JSONObject queryComment(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="goodsid",required=false) Long goodsid,
			@RequestParam(value="userid",required=false) Long userid,
			@RequestParam(value="orderid",required=false) Long orderid,
			@RequestParam(value="ispage",required=false) String ispage,
			@RequestParam(value="pagesize",required=false) String pagesize,
			@RequestParam(value="nowpage",required=false) String nowpage) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		List list=null;
		
		if(StringUtils.isEmpty(userid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("userid"))){
				String usid=requestString.get("userid")+"";
				if(!StringUtils.isEmpty(usid)){
					userid=Long.parseLong(usid);
				}
			}
		}
		if(StringUtils.isEmpty(orderid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("orderid"))){
				String odid=requestString.get("orderid")+"";
				if(!StringUtils.isEmpty(odid)){
					orderid=Long.parseLong(odid);
				}
			}
		}
		
		if(StringUtils.isEmpty(ispage) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("ispage"))){
				ispage=requestString.get("ispage")+"";
			}else{
				ispage="0";
			}
		}
		
		if(StringUtils.isEmpty(goodsid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("goodsid"))){
				String gdid=requestString.get("goodsid")+"";
				if(!StringUtils.isEmpty(gdid)){
					goodsid=Long.parseLong(gdid);
				}
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
				nowpage="0";
			}
		}else{
			nowpage="0";
		}
		
		
		PageCond page=new PageCond(Integer.parseInt(nowpage)*Integer.parseInt(pagesize),Integer.parseInt(pagesize));
		if(!StringUtils.isEmpty(userid) || !StringUtils.isEmpty(goodsid) || !StringUtils.isEmpty(orderid)){
			Map map=new HashMap();
			if(!StringUtils.isEmpty(userid)){
				map.put("userid",userid);
			}
			if(!StringUtils.isEmpty(goodsid)){
				map.put("goodsid",goodsid);
			}
			if(!StringUtils.isEmpty(orderid)){
				map.put("orderid",orderid);
			}
			if(ispage.equals("0")){
				list=commentService.queryComment(page,map);
			}else{
				list=commentService.queryObj(map);
			}
			result="0";
			message="查询成功~";
			logger.log(INFO, message);
			data=list;
			rsJson.put("page", page);
		}else{
			result="1";
			message="userid goodsid orderid 均为空请确认无误后再行调用";
			logger.log(INFO, message);
			data="";
		}
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
	@ResponseBody
	@RequestMapping(value="/manager")
	public JSONObject managerComments(HttpServletRequest request,
			HttpServletResponse response,
			NsComment comment) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);

		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		
		comment=(NsComment)autoLoad(comment,"userid",requestString);
		comment=(NsComment)autoLoad(comment,"goodsid",requestString);
		comment=(NsComment)autoLoad(comment,"comment",requestString);
		comment=(NsComment)autoLoad(comment,"score",requestString);
		comment=(NsComment)autoLoad(comment,"ishidden",requestString);
		comment=(NsComment)autoLoad(comment,"orderid",requestString);
		
		if(comment!=null){
			int i=commentService.addObj(comment);
			if(i > 0){
				result="0";
				message="执行成功~";
				logger.log(INFO, message);
				data="";
			}else{
				result="1";
				message="执行失败~";
				logger.log(INFO, message);
				data="";
			}
		}else{
			result="1";
			message="传递值为空 执行失败~";
			logger.log(INFO, message);
			data="";
		}
		
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
}

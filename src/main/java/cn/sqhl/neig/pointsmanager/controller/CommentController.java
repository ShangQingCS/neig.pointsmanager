package cn.sqhl.neig.pointsmanager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.po.NsComment;
import cn.sqhl.neig.pointsmanager.service.CommentService;
import cn.sqhl.neig.pointsmanager.service.ShopCarService;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
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
			@Param(value="goodsid") Long goodsid,
			@Param(value="userid") Long userid,
			@Param(value="pagesize") String pagesize,
			@Param(value="nowpage") String nowpage) throws IOException{
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
				nowpage="1";
			}
		}else{
			nowpage="1";
		}
		
		
		PageCond page=new PageCond(Integer.parseInt(nowpage), Integer.parseInt(pagesize));
		if(!StringUtils.isEmpty(userid) || !StringUtils.isEmpty(goodsid)){
			Map map=new HashMap();
			if(!StringUtils.isEmpty(userid)){
				map.put("userid",userid);
			}
			if(!StringUtils.isEmpty(goodsid)){
				map.put("goodsid",goodsid);
			}
			list=commentService.queryObj(page,map);
			result="0";
			message="查询成功~";
			logger.log(INFO, message);
			data=list;
			rsJson.put("page", page);
		}else{
			result="1";
			message="userid goodsid 均为空请确认无误后再行调用";
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

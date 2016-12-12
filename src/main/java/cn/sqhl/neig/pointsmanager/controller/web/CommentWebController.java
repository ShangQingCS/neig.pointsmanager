package cn.sqhl.neig.pointsmanager.controller.web;

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

import cn.sqhl.neig.pointsmanager.service.CommentService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;


@Controller
@RequestMapping("/comment_web")
public class CommentWebController extends basicInfo{
	private Logger logger=LogManager.getLogger(CommentWebController.class);
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping("/search")
	public JSONObject cmQuery(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="goodsid",required=false) String goodsid,
			@RequestParam(value="pagesize",required=false) String pagesize,
			@RequestParam(value="nowpage",required=false) String nowpage){
		JSONObject rsJson = new JSONObject();
		String result="";
		String message="";
		Object data="";
		if(StringUtils.isBlank(pagesize)){
			pagesize="15";
		}
		if(StringUtils.isBlank(nowpage)){
			nowpage="0";
		}
		
		PageCond page=new PageCond(Integer.parseInt(nowpage)*Integer.parseInt(pagesize),Integer.parseInt(pagesize));
		List list=null;
		if(StringUtils.isNotBlank(goodsid+"")){
			Map map=new HashMap();
			map.put("goodsid", goodsid);
			list=commentService.queryComment(page, map);
			result="0";
			message="查询成功~";
			logger.log(INFO, message);
			data=list;
			rsJson.put("page", page);
		}else{
			result="1";
			message="goodsid 为空请确认无误后再行调用";
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

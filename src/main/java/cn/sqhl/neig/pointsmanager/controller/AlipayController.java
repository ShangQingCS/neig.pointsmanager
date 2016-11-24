package cn.sqhl.neig.pointsmanager.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;



@Controller
@RequestMapping("/alipay")
public class AlipayController extends ContextInfo{

	private Logger logger=LogManager.getLogger(AlipayController.class);
	
	@ResponseBody
	@RequestMapping("/gateway")
	public JSONObject queryAddress(HttpServletRequest request,HttpServletResponse response) throws Exception{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);
	    JSONObject requestString=JSONObject.parseObject(locationsJSONString);
	    logger.debug("/address/search param:"+requestString);
	    
			result="1";
			message="userid不能为空";
			logger.log(INFO, message);
			data="";
		
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		response.setContentType("charset=utf-8");
		return rsJson;
	}
}

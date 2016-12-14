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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.po.NsCart;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
import cn.sqhl.neig.pointsmanager.utils.Test;
import cn.sqhl.neig.pointsmanager.vo.Address;

@Controller
@RequestMapping("/address")
public class AddressController extends ContextInfo{

	@Autowired
	private AddressService addressServices;
	
//	@Value("${ns.version}")
//	protected String ver;
//	protected String result;
//	protected String message;
//	protected Object data;
	
	private Logger logger=LogManager.getLogger(AddressController.class);
	
	@ResponseBody
	@RequestMapping("/search")
	public JSONObject queryAddress(HttpServletRequest request,@Param("isuse") String isuse,@Param("userid") String userid,HttpServletResponse response) throws Exception{
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);
	    JSONObject requestString=JSONObject.parseObject(locationsJSONString);
	    logger.debug("/address/search param:"+requestString);
	    if(StringUtils.isEmpty(userid) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("userid"))){
				userid=requestString.getString("userid");
			}
	    }
	    if(StringUtils.isEmpty(isuse) && requestString!=null){
			if(!StringUtils.isEmpty(requestString.get("isuse"))){
	    	isuse=requestString.getString("isuse");
			}
	    }
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		if(!StringUtils.isEmpty(userid)){
			try{
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userid", userid);	
				map.put("isuse", isuse);
				List<Address> ads=addressServices.queryObj(map);
				result="0";
				data=ads;
				message="查询成功";
			}catch(Exception e){
				result="1";
				message=e.getMessage();
				logger.log(ERROR,e.toString());
				data=null;
			}
		}else{
			result="1";
			message="userid不能为空";
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
	public JSONObject managerAddress(HttpServletRequest request,HttpServletResponse response,NsAddress address,
			@RequestParam(value="addressid",required=false) Long id,
			@RequestParam(value="isuse",required=false) Integer isuse,
			@RequestParam(value="uerid",required=false) Long uerid,
			@RequestParam(value="address",required=false) String adds,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="post",required=false) String post,
			@RequestParam(value="phonenumb",required=false) String phonenumb,
			@RequestParam(value="telnumb",required=false) String telnumb,
			@RequestParam(value="type",required=false) String type) throws IOException{
		JSONObject rsJson = new JSONObject();
		rsJson.put("ver", ver);
		InputStream requestjson = request.getInputStream();
		String encoding = request.getCharacterEncoding(); 
		String locationsJSONString=IOUtils.toString(requestjson,encoding);
		JSONObject requestString=JSONObject.parseObject(locationsJSONString);
		logger.log(DEBUG, requestString);
		try {
			if(StringUtils.isEmpty(type)&&!StringUtils.isEmpty(requestString)){				
				type=requestString.get("type")+"";
			}
			if(StringUtils.isEmpty(id)&&!StringUtils.isEmpty(requestString)){				
				if(!StringUtils.isEmpty(requestString.get("addressid"))){
					String addressid=requestString.get("addressid")+"";
					if(!StringUtils.isEmpty(addressid)){
						id=Long.parseLong(addressid);
					}
				}else{
					String addressid=request.getParameter("addressid");
					if(!StringUtils.isEmpty(addressid)){
						id=Long.parseLong(addressid);
					}
				}
			}
			if(address==null){
				address=new NsAddress();
			}
//			address=(NsAddress)getValue(address,"uerid",requestString);
			if(StringUtils.isEmpty(uerid)&&!StringUtils.isEmpty(requestString)){
				address=(NsAddress)autoLoad(address,"uerid",requestString);
			}else{
				address.setUerid(uerid);
			}
			if(StringUtils.isEmpty(name)&&!StringUtils.isEmpty(requestString)){
				address=(NsAddress)autoLoad(address,"name",requestString);
			}else{
				address.setName(name);
			}
			if(StringUtils.isEmpty(post)&&!StringUtils.isEmpty(requestString)){
				address=(NsAddress)autoLoad(address,"post",requestString);
			}else{
				address.setPost(post);
			}
			if(StringUtils.isEmpty(phonenumb)&&!StringUtils.isEmpty(requestString)){
				address=(NsAddress)autoLoad(address,"phonenumb",requestString);
			}else{
				address.setPhonenumb(phonenumb);
			}
			if(StringUtils.isEmpty(telnumb)&&!StringUtils.isEmpty(requestString)){
				address=(NsAddress)autoLoad(address,"telnumb",requestString);
			}else{
				address.setTelnumb(telnumb);
			}
			
			if(StringUtils.isEmpty(isuse)&&!StringUtils.isEmpty(requestString)){
				address=(NsAddress)autoLoad(address,"isuse",requestString);
			}else{
				address.setIsuse(isuse);
			}
			if(StringUtils.isEmpty(adds)&&!StringUtils.isEmpty(requestString)){
				address=(NsAddress)autoLoad(address,"address",requestString);
			}else{
				address.setAddress(adds);
			}
		    
		    
			String userid=address.getUerid().toString();
			if(!StringUtils.isEmpty(userid)){
				if(!StringUtils.isEmpty(type)){// 1 删除 2修改 0新增
					if(!StringUtils.isEmpty(id)){ 
						int i=0;
						address.setId(id);
						if(type.equals("1")){
							i=addressServices.removeObj(address);
							
						}else if(type.equals("2")){
							try{								
								i=addressServices.updateObj(address);
							}catch(Exception e){
								result="1";
								message="操作失败 请重新尝试";
								logger.log(INFO, message);
								data="";
							}
							
						}else{
							result="1";
							message="type 操作类型有误";
							logger.log(INFO, message);
							data="";
						}
						if(i > 0){
							result="0";
							message="操作成功";
							logger.log(INFO, message);
							data="";
						}else{
							result="1";
							message="操作失败";
							logger.log(INFO, message);
							data="";
						}
						
					}else{
						if(type.equals("0")){
							try{
								int i=addressServices.addObj(address);
								if(i > 0){
									result="0";
									message="操作成功";
									logger.log(INFO, message);
									data="";
								}else{
									result="1";
									message="操作失败";
									logger.log(INFO, message);
									data="";
								}
							}catch(Exception e){
								result="1";
								message="操作失败 请重新尝试";
								logger.log(INFO, message);
								data="";
							}
						}else{
							result="1";
							message="该type操作类型 userid不能为空";
							logger.log(INFO, message);
							data="";
						}
					}
				}else{
					result="1";
					message="type 操作类型不能为空";
					logger.log(INFO, message);
					data="";
				}
			}else{
				result="1";
				message="userid不能为空";
				logger.log(INFO, message);
				data="";
			}

		    
		} catch (Exception e) {
			result="1";
			message=e.getMessage();
			logger.log(ERROR,e.toString());
			data=null;
		}
		rsJson.put("result", result);
		rsJson.put("message", message);
		rsJson.put("data", data);
		return rsJson;
	}
}

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

import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/user")
public class UserController extends ContextInfo{

	@Autowired
	private UserServiceImpl userService;
	
//	@Value("${ns.version}")
//	protected String ver;
//	protected String result;
//	protected String message;
//	protected Object data;
	
	private Logger logger=LogManager.getLogger(UserController.class);
	
	@ResponseBody
	@RequestMapping(value="/manager")
	public JSONObject managerUser(HttpServletRequest request,HttpServletResponse response,
			NsUser nsUser,@Param("type") String type) throws IOException{
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
			nsUser=(NsUser)autoLoad(nsUser,"userid",requestString);
			nsUser=(NsUser)autoLoad(nsUser,"username",requestString);
			nsUser=(NsUser)autoLoad(nsUser,"name",requestString);
			nsUser=(NsUser)autoLoad(nsUser,"email",requestString);
			nsUser=(NsUser)autoLoad(nsUser,"address",requestString);
			nsUser=(NsUser)autoLoad(nsUser,"phoneno",requestString);
			nsUser=(NsUser)autoLoad(nsUser,"sex",requestString);
			if(type.equals("0")){
				if(!StringUtils.isEmpty(nsUser.getUserid())){
					Map<String, Object> map=new HashMap<String,Object>();
					map.put("userid", nsUser.getUserid());
					List<NsUser> list=userService.selectUser(map);
					if(list!=null && list.size()>0){
						result="1";
						message="该用户已经存在";
						logger.log(INFO,message);
						data=null;
					}else{
						int i=userService.addObj(nsUser);
						if(i>0){
							result="0";
							message="操作成功~该用户已经正常插入";
							logger.log(INFO,message);
							data=null;
						}else{
							result="1";
							message="操作失败~请重试尝试";
							logger.log(INFO,message);
							data=null;
						}
					}
					
				}else{
					result="1";
					message="用户id为空~ 请确认后再行查询";
					logger.log(INFO,message);
					data=null;
				}
			}else{
				result="1";
				message="操作类型有误~ 请确认后再行查询";
				logger.log(INFO,message);
				data=null; 
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

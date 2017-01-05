package cn.sqhl.neig.pointsmanager.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.UserService;
import cn.sqhl.neig.pointsmanager.service.impl.UserServiceImpl;
import cn.sqhl.neig.pointsmanager.utils.CheckUserUtils;
import cn.sqhl.neig.pointsmanager.utils.MD5Util;

import com.alibaba.fastjson.JSONObject;




@Controller
@RequestMapping("/login_web")
public class LoginController {
	private Logger logger=LogManager.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		return "login";
	}
	@ResponseBody
	@RequestMapping("/loginjson")
	public JSONObject Login(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="loginPwd",required=false) String loginPwd
			) throws Exception{	
		boolean flag=false;
		HttpSession session=request.getSession();
		if(CheckUserUtils.isNum(username)){
			NsUser user=userService.queryByUserPhone(username, MD5Util.MD5(loginPwd));
			
			if(user !=null){
				flag=true;	
				session.setAttribute("user", user);
			}		
		}else{
			NsUser user=userService.queryByUserName(username, MD5Util.MD5(loginPwd));
			
			if(user !=null){
				flag=true;
				
				session.setAttribute("user", user);
			}	
		}
		
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", flag);
		return rsJson;
	}
	@RequestMapping("/share")
	public String share(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		return "/jsp/share";
	}
	
	
}

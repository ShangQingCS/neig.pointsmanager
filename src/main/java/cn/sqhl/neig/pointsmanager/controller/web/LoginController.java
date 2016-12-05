package cn.sqhl.neig.pointsmanager.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/login_web")
public class LoginController {
	private Logger logger=LogManager.getLogger(LoginController.class);
	
	
	@RequestMapping("/login")
	public String Login(HttpServletRequest request,HttpServletResponse response,Model model){
		boolean k=true;
		if(k){
			return "/jsp/home";
		}else{
			return "/login";
		}
	}
}

package cn.sqhl.neig.pointsmanager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Login")
public class LoginController {
	
	@RequestMapping("singin")
	public String loginAuthentication(Model model, HttpServletRequest request){
		request.getAttribute("");
		
		
		return "";
	}
}

package cn.sqhl.neig.pointsmanager.controller.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.service.UserService;
import cn.sqhl.neig.pointsmanager.service.impl.UserServiceImpl;
import cn.sqhl.neig.pointsmanager.utils.CheckUserUtils;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
import cn.sqhl.neig.pointsmanager.utils.MD5Util;
import cn.sqhl.neig.pointsmanager.utils.SmsHelper;
import cn.sqhl.neig.pointsmanager.vo.Address;
import cn.sqhl.neig.pointsmanager.vo.web.ErrorInfo;


@Controller
@RequestMapping("/user_web")
public class UserWebController extends basicInfo{
	private Logger logger=LogManager.getLogger(UserWebController.class);
	
	@Autowired
	private AddressService addressServices;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/main")
	public String Main(HttpServletRequest request,HttpServletResponse response,Model model){

		return "/jsp/person/information";
	}
	
	@ResponseBody
	@RequestMapping("/information")
	public JSONObject information(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="nickName",required=false) String nickName,
			@RequestParam(value="userSex",required=false) String userSex,
			@RequestParam(value="userMail",required=false) String userMail,
			@RequestParam(value="birthday",required=false) String birthday
			
			) throws Exception{		
		int result=0;
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			user.setNickName(nickName);
			user.setUserSex(Integer.parseInt(userSex));
			
			user.setUserMail(userMail);
			result=userService.updateByNickName(user);
		}		
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	
	
	@RequestMapping("/user/address")
	public String adddress(HttpServletRequest request,HttpServletResponse response,Model model){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", "4002");	
		List<Address> ads=addressServices.queryObj(map);
		model.addAttribute("ads", ads);
		return "/jsp/person/address";
	}
	
	@RequestMapping("/user/edit/address")
	public String EditAdddress(HttpServletRequest request,HttpServletResponse response,Model model,
			NsAddress address,
			@RequestParam(value="addressid",required=false) Long id,
			@RequestParam(value="isuse",required=false) Integer isuse,
			@RequestParam(value="uerid",required=false) Long uerid,
			@RequestParam(value="address",required=false) String adds,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="post",required=false) String post,
			@RequestParam(value="phonenumb",required=false) String phonenumb,
			@RequestParam(value="telnumb",required=false) String telnumb,
			@RequestParam(value="type",required=false) String type){
		
		if(address==null){
			address=new NsAddress();
		}
//		address=(NsAddress)getValue(address,"uerid",requestString);
		if(StringUtils.isNotBlank(uerid+"")){
			address.setUerid(uerid);
		}
		if(StringUtils.isNotBlank(name)){
			address.setName(name);
		}
		if(StringUtils.isNotBlank(post)){
			address.setPost(post);
		}
		if(StringUtils.isNotBlank(phonenumb)){
			address.setPhonenumb(phonenumb);
		}
		if(StringUtils.isNotBlank(telnumb)){
			address.setTelnumb(telnumb);
		}
		
		if(StringUtils.isNotBlank(isuse+"")){
			address.setIsuse(isuse);
		}
		if(StringUtils.isNotBlank(adds)){
			address.setAddress(adds);
		}
		if(StringUtils.isNotBlank(id+"")){
			address.setId(id);
		}
		int i=0;
		ErrorInfo einfo=null;
		if(StringUtils.isNotBlank(type)){
			try{
				if(type.equals("0")){
					i=addressServices.addObj(address);
				}else if(type.equals("2")){
					i=addressServices.updateObj(address);
				}
			}catch(Exception e){
				einfo=new ErrorInfo();
				einfo.setResult("1");
				einfo.setMessage("操作失败");
				einfo.setCause("数据操作 出错 请重新尝试");
			}
		}else{
			einfo=new ErrorInfo();
			einfo.setResult("1");
			einfo.setMessage("操作失败");
			einfo.setCause("type 异常 请重新尝试");
		}
		if(i>0){
			einfo=new ErrorInfo();
			einfo.setResult("0");
			einfo.setMessage("操作成功");
			einfo.setCause("操作成功~");
		}else{
			einfo=new ErrorInfo();
			einfo.setResult("1");
			einfo.setMessage("操作失败");
			einfo.setCause("未成功存储 请重新尝试");
		}
		model.addAttribute("returnInfo", einfo);
		return "/jsp/person/address";
	}
	
	
	@RequestMapping("/user/register")
	public String register(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		return "/jsp/register";
	}
	
	@ResponseBody
	@RequestMapping("/user/sendcode")
	public JSONObject sendCode(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="tel",required=false) String tel
			) throws Exception{		
		int result=0;
		
		String mobileCode=FormatUtils.getRandom();
		System.err.println(tel+"------发送验证码------"+mobileCode);
		//SmsHelper.sendSms(tel, mobileCode);
		
		HttpSession session=request.getSession();
		session.setAttribute("mobileCode", mobileCode);	
		
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	@ResponseBody
	@RequestMapping("/user/checkcode")
	public JSONObject checkcode(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="mobilecode",required=false) String mobileCode
			) throws Exception{		
		int result=0;
		
		if(CheckUserUtils.checkMobileCode(mobileCode, request.getSession())){
			result=1;
		}
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	
	
	@ResponseBody
	@RequestMapping("/user/checkusername")
	public JSONObject checkUserName(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="tel",required=true) String tel
			) throws Exception{
		
		boolean result=CheckUserUtils.checkUser(userService, username, tel);

		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	
	@ResponseBody
	@RequestMapping("/user/registerjson")
	public JSONObject saveUser(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="loginPwd",required=false) String loginPwd,
			@RequestParam(value="tel",required=false) String tel
			) throws Exception{
		//获取推荐人ID
		//request.getParameter("pid");
		int result=0;
		
		if(CheckUserUtils.checkUser(userService, username, tel)){
			NsUser user= new NsUser();
			user.setUserName(username);
			user.setLoginPwd(MD5Util.MD5(loginPwd));	
			user.setUserPhone(tel);
			result=userService.addObj(user);	
		}
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	@RequestMapping("/user/safety")
	public String safety(HttpServletRequest request,HttpServletResponse response,Model model){
		
		return "/jsp/person/safety";
	}
	@RequestMapping("/user/paypwd")
	public String payPwd(HttpServletRequest request,HttpServletResponse response,Model model){
		
		return "/jsp/person/paypwd";
	}
	@ResponseBody
	@RequestMapping("/user/paypwdjson")
	public JSONObject setPayPwd(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="mobilecode",required=false) String mobileCode,
			@RequestParam(value="payPwd",required=false) String payPwd
			) throws Exception{
			int result=0;	
			NsUser user=(NsUser) request.getSession().getAttribute("user");
			if(user!=null&&CheckUserUtils.checkMobileCode(mobileCode, request.getSession())){
				user.setPayPwd(MD5Util.MD5(payPwd));
				
				result=userService.updateByPayPwd(user);
			}
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	
	
	@RequestMapping("/user/loginpwd")
	public String loginPwd(HttpServletRequest request,HttpServletResponse response,Model model){
		
		
		return "/jsp/person/loginpwd";
	}
	@ResponseBody
	@RequestMapping("/user/loginpwdjson")
	public JSONObject setLoginPwd(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="mobilecode",required=false) String mobileCode,
			@RequestParam(value="loginPwd",required=false) String loginPwd
			) throws Exception{
			int result=0;	
			NsUser user=(NsUser) request.getSession().getAttribute("user");
			if(user!=null&&CheckUserUtils.checkMobileCode(mobileCode, request.getSession())){
				user.setLoginPwd(MD5Util.MD5(loginPwd));
				result=userService.updateByLoginPwd(user);
			}
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	
	
	@RequestMapping("/user/idcard")
	public String idcard(HttpServletRequest request,HttpServletResponse response,Model model){
		
		return "/jsp/person/idcard";
	}
	@ResponseBody
	@RequestMapping("/user/idcardjson")
	public JSONObject saveIDcard(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="truename",required=false) String trueName,
			@RequestParam(value="IDcard",required=false) String IDcard,
			@RequestParam(value="issuing",required=false) String issuing,
			@RequestParam(value="IDCardValidity",required=false) String idCardValidity
			) throws Exception{
			int result=0;	
			NsUser user=(NsUser) request.getSession().getAttribute("user");
			if(user!=null){
				user.setTrueName(trueName);
				user.setIdentityCard(IDcard);	
				user.setIdentityIssuing(issuing);
				user.setIdentityCardValidity(new Date());
				user.setIdentityStatus(1);
				result=userService.updateByIDcard(user);	
			}
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	
	@RequestMapping("/user/bindphone")
	public String bindPhone(HttpServletRequest request,HttpServletResponse response,Model model){
		
		return "/jsp/person/bindphone";
	}
	@ResponseBody
	@RequestMapping("/bindphonejson")
	public JSONObject bindphonejson(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="truename",required=false) String trueName,
			@RequestParam(value="IDcard",required=false) String IDcard,
			@RequestParam(value="issuing",required=false) String issuing,
			@RequestParam(value="IDCardValidity",required=false) String idCardValidity
			) throws Exception{
			int result=0;	
			NsUser user=(NsUser) request.getSession().getAttribute("user");
			if(user!=null){
				user.setTrueName(trueName);
				user.setIdentityCard(IDcard);	
				user.setIdentityIssuing(issuing);
				user.setIdentityCardValidity(new Date());
				user.setIdentityStatus(1);
				result=userService.updateByIDcard(user);	
			}
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
		
}

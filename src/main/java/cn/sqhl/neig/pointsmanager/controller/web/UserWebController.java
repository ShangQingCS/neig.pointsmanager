package cn.sqhl.neig.pointsmanager.controller.web;

import java.math.BigDecimal;
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

import cn.sqhl.neig.pointsmanager.chat.IChat;
import cn.sqhl.neig.pointsmanager.mapper.NsUserPurseMapper;
import cn.sqhl.neig.pointsmanager.pay.IPay;
import cn.sqhl.neig.pointsmanager.pay.util.UtilOther;
import cn.sqhl.neig.pointsmanager.po.NsAddress;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.po.NsUserGrade;
import cn.sqhl.neig.pointsmanager.po.NsUserPurse;
import cn.sqhl.neig.pointsmanager.service.AddressService;
import cn.sqhl.neig.pointsmanager.service.CouponService;
import cn.sqhl.neig.pointsmanager.service.UserPurseService;
import cn.sqhl.neig.pointsmanager.service.UserService;
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
	
	@Autowired
	private CouponService couponService ;
	
	@Autowired
	private UserPurseService userPurseService;
	
	@RequestMapping("/user/main")
	public String Main(HttpServletRequest request,HttpServletResponse response,Model model){
		if((NsUser) request.getSession().getAttribute("user")!=null){	
			return "/jsp/person/information";
		}else{
			return "/login";
		}
	}
	
	@ResponseBody
	@RequestMapping("/information")
	public JSONObject information(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="nickName",required=false) String nickName,
			@RequestParam(value="userSex",required=false) String userSex,
			@RequestParam(value="userMail",required=false) String userMail
			
			
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
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userid", user.getId());
			
			List<Address> ads=addressServices.queryObj(map);
			model.addAttribute("ads", ads);
			return "/jsp/person/address";
		}else{
			
			return "/login";
		}
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
			@RequestParam(value="tel",required=false) String tel,
			@RequestParam(value="mobileCode",required=false) String mobileCode
			) throws Exception{
		//获取推荐人ID
		
		int result=0;
		
		if(CheckUserUtils.checkUser(userService, username, tel)&&CheckUserUtils.checkMobileCode(mobileCode, request.getSession())&&CheckUserUtils.isNum(tel)){
			NsUser user= new NsUser();
			user.setUserName(username);
			user.setLoginPwd(MD5Util.MD5(loginPwd));	
			user.setUserPhone(tel);
			user.setUserGrade(0l);
			user.setUserSex(0);
			if(request.getParameter("userPid")!=null){
				user.setUserPid(Long.valueOf(request.getParameter("userPid")));				
			}
			result=userService.addObj(user);
			
			user = userService.queryByUserName(username, null);
			
			IChat ichat = IChat.getInstance();
			ichat.reg(user);
			
			
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
			
			@RequestParam(value="tel",required=false) String tel,
			@RequestParam(value="mobileCode",required=false) String mobileCode
			) throws Exception{
			int result=0;
	
			NsUser user=(NsUser) request.getSession().getAttribute("user");
			if(user!=null){
				if(CheckUserUtils.checkUser(userService, null, tel)&&CheckUserUtils.checkMobileCode(mobileCode, request.getSession())){
					NsUser nsuser=new NsUser();
					nsuser.setId(user.getId());
					nsuser.setUserPhone(tel);
					result=userService.updateObj(nsuser);
				}	
			}
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
		
	}
	
	@RequestMapping("/user/tixian")
	public String tixian(HttpServletRequest request,HttpServletResponse response){
		
		NsUser user=(NsUser) request.getSession().getAttribute("user"); //获取到session中的用户对象
		if(user!=null){
			user = userService.queryByPrimaryKey(Integer.valueOf(user.getId().toString())); //对用户钱包操作，必须每次重新从数据库加载最新数据，放到session
			long userGradeId = user.getUserGrade();
			NsUserGrade userGrade = couponService.selectUserGradebyID(userGradeId);
			request.setAttribute("userGrade", userGrade);
			request.getSession().setAttribute("user",user);
			return "/jsp/person/tixian";
			
		}else{
			
			return "/login";}	
		}
	
	@ResponseBody
	@RequestMapping("/user/tixianjson")
	public JSONObject tixianjson(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="trade_amount",required=false) String trade_amount,
			@RequestParam(value="pay_account",required=false) String pay_account,
			@RequestParam(value="paypwd",required=false) String paypwd,
			@RequestParam(value="pay_open_bank",required=false) String pay_open_bank,
			@RequestParam(value="trade_type",required=false) String trade_type,
			@RequestParam(value="gradeTxBalance",required=false) String gradeTxBalance){
				
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		String result =null;
		JSONObject rsJson = new JSONObject();
		
			paypwd = MD5Util.MD5(paypwd.trim());//接收的支付密码加密
			String pay_pwd = user.getPayPwd();//支付密码
			String payaccount = pay_account.trim();//支付账号
			String payopenbank = pay_open_bank.trim();//开户行
			String tradetype = trade_type;
			
			BigDecimal fxBalance = user.getUserFxBalance();           //用户分红可用金额
			BigDecimal tradeamount = new BigDecimal(trade_amount);    //提现金额
			BigDecimal gradeBalance = new BigDecimal(gradeTxBalance); // 等级提现限制金额
			
			
			
			if(payaccount.equals("")){
				result = "提现账号不能为空";
			}else if(tradetype.equals("3") && payopenbank.equals("")){
				result = "银联开户行不能为空";
			}else if(tradeamount.compareTo(gradeBalance)==1){
				result = "单笔可提现"+gradeTxBalance+"元";
			}else if(tradeamount.compareTo(fxBalance)==1){
				result = "分红可用余额不足";
			}else if(!paypwd.equals(pay_pwd)){
				result = "支付密码错误";
			}else{
				
//				user_id
//				option_type 操作类型 0-increase 增加  1-decrease 减少
//				trade_sn 生成流水号
//				trade_state 1 冻结
//				purse_type 1-分销
				
				int state = 0;
				
				user.setTixianStatus(1); //设置提现状态 申请中
				user.setUserDjBalance(tradeamount); //冻结金额
				user.setUserFxBalance(fxBalance.subtract(tradeamount)); //剩余金额
				//数据库操作
				// 更新 nsuser
				
				state = userService.updateObj(user);
				
				// 写入 pruse
				
				String tradeSn = FormatUtils.getOrderIdByUUId();
				
				tradeSn = "S"+tradeSn; //充值单唯一对账编号
				
				if(tradetype.equals("1")){//支付宝
					tradeSn = "Z"+tradeSn;
				} else if(tradetype.equals("2")){//微信
					tradeSn = "W"+tradeSn;
				} else if(tradetype.equals("3")){//银联
					tradeSn = "U"+tradeSn;
					
				}
				
				NsUserPurse nsUserPurse=new NsUserPurse();
				nsUserPurse.setOptionType("1");
				nsUserPurse.setTradeSn(tradeSn);
				nsUserPurse.setPurseStatus(user.getUserStatus().toString());
				nsUserPurse.setTradeType(tradetype);
				nsUserPurse.setTradeState("1");
				nsUserPurse.setPurseType(1);
				nsUserPurse.setOptionAdminid(0l);
				nsUserPurse.setCreateTime(new Date());
				nsUserPurse.setTradeAmount(tradeamount);
				nsUserPurse.setUserAmount(user.getUserFxBalance());
				nsUserPurse.setUserId(user.getId());			
				state = userPurseService.addObj(nsUserPurse);
				
				request.getSession().setAttribute("user",user);
				
				if(state>0){
					
					result = "申请成功";
					
				}else{
					
					result = "服务器错误,请重新提交";
				}			
			}	
		
		
		
		rsJson.put("msg", result);
		return rsJson;
	}
	@RequestMapping("/user/chongzhi")
	public String chongzhi(HttpServletRequest request,HttpServletResponse response){
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			user = userService.queryByPrimaryKey(Integer.valueOf(user.getId().toString())); //对用户钱包操作，必须每次重新从数据库加载最新数据，放到session

			long userGradeId = user.getUserGrade();
			
			NsUserGrade userGrade = couponService.selectUserGradebyID(userGradeId);
			request.setAttribute("userGrade", userGrade);
			request.getSession().setAttribute("user",user);
			
			return "/jsp/person/chongzhi";
			
		}else{
			
			return "/login";
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/user/chongzhijson")
	public JSONObject chongzhijson(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="trade_amount",required=false) String trade_amount,
			@RequestParam(value="trade_type",required=false) String trade_type){
				
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		
		JSONObject rsJson = new JSONObject();

		String tradetype = trade_type;
		BigDecimal tradeamount = new BigDecimal(trade_amount);    //充值金额
		
		String tradeSn = FormatUtils.getOrderIdByUUId(); //充值单唯一对账编号
		
		String result = null;
		

		//支付接口初始化
		IPay ipay = IPay.getInstance();
		String requestString = null;
		
		if(tradetype.equals("1")){//支付宝
			tradeSn = "Z"+tradeSn;
			
			// zfb调用统一下单
			result = ipay.createPay("aliPay", "pc", UtilOther.yuanToFen(tradeamount), tradeSn,"贝翔商城"+trade_amount+"元储值卡");
			//System.out.println(requestString);
			// zfb调用发送请求
			//result = ipay.httpsPayRequest("aliPay", "pc", requestString);
			System.out.println(result);
			
		} else if(tradetype.equals("2")){//微信
			tradeSn = "W"+tradeSn;
			
			// 微信调用统一下单
			requestString = ipay.createPay("wechatPay", "pc", UtilOther.yuanToFen(tradeamount), tradeSn,"贝翔商城"+trade_amount+"元储值卡");
			// 微信调用发送请求
			result = ipay.httpsPayRequest("wechatPay", "pc", requestString);
			// 生成二维码
			result = ipay.getQRCodeImge(tradeSn, result,request.getSession().getServletContext().getRealPath("/")+"/upload/qrcode/");
			
			result = result.replace(request.getSession().getServletContext().getRealPath("/"), "");
		}
			
//			user_id
//			option_type 操作类型 0-increase 增加  1-decrease 减少
//			trade_sn 生成流水号    UUID uuid = UUID.randomUUID()
//			trade_state 1 冻结
//			purse_type 1-分销
		
		
			// 写入 pruse
			int state=0;
			NsUserPurse nsUserPurse=new NsUserPurse();
			nsUserPurse.setOptionType("0");
			nsUserPurse.setTradeSn(tradeSn);
			nsUserPurse.setTradeState("1");
			nsUserPurse.setPurseType(0);
			nsUserPurse.setTradeType(tradetype);
			nsUserPurse.setTradeAmount(tradeamount);
			nsUserPurse.setCreateTime(new Date());
			nsUserPurse.setOptionAdminid(0l);
			nsUserPurse.setPurseStatus(user.getUserStatus().toString());
			nsUserPurse.setUserId(user.getId());
			nsUserPurse.setUserAmount(user.getUserKyBalance());
			state = userPurseService.addObj(nsUserPurse);
			request.getSession().setAttribute("user",user);
			
			//result = "申请成功";
		
		
		rsJson.put("msg", result);
		return rsJson;
	}
	
	
	@RequestMapping("/user/alichongzhi")
	public String alichongzhi(HttpServletRequest request,HttpServletResponse response){
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		
		
		return "/jsp/person/alipay";
	}
	
	@RequestMapping("/user/wxchongzhi")
	public String wxchongzhi(HttpServletRequest request,HttpServletResponse response){
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		
		
		return "/jsp/person/wxpay";
	}
		
}

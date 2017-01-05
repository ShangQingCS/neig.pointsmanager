package cn.sqhl.neig.pointsmanager.controller.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.po.NsUserPurse;
import cn.sqhl.neig.pointsmanager.service.CouponService;
import cn.sqhl.neig.pointsmanager.service.UserPurseService;


@Controller
@RequestMapping("/asset_web")
public class AssetWebController extends basicInfo{
	private Logger logger=LogManager.getLogger(AssetWebController.class);
	
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserPurseService userPurseService;
	
	@RequestMapping("/coupon")
	public String coupon(HttpServletRequest request,HttpServletResponse response,Model model){
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		List<NsUserCoupon> myCouponList= couponService.selectByUserId(Long.valueOf(1), null);
		
		model.addAttribute("myCouponList", myCouponList);
		
		List<NsUserCoupon> expireCouponList= couponService.selectByUserId(Long.valueOf(1), "1");
		
		model.addAttribute("expireCouponList", expireCouponList);
		
		return "/jsp/person/coupon";
	}
	
	
	@RequestMapping("/walletlist")
	public String myWalletlist(HttpServletRequest request,HttpServletResponse response,Model model){
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		String dateCode=request.getParameter("dateCode");
		//List<NsUserPurse> walletList=userPurseService.selectByUserId(user.getId(),);
		//model.addAttribute("walletList", walletList);
		
		return "/jsp/person/walletlist";
	}
	
	
	
	
	
	
	
}

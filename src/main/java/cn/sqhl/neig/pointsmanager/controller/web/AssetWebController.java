package cn.sqhl.neig.pointsmanager.controller.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import cn.sqhl.neig.pointsmanager.utils.DateHelper;


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
		List<NsUserPurse> walletList=new ArrayList<NsUserPurse>();
		String dateCode=request.getParameter("dateCode");
		if(StringUtils.isNotEmpty(dateCode)&&user!=null){
			if(dateCode.equals("0")){
				//查当天
				Date beforeTime=new  Date();
				String time=DateHelper.sdfd.format(beforeTime);
				try {
					beforeTime=DateHelper.sdfd.parse(time);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				walletList=userPurseService.selectByUserId(user.getId(),beforeTime);
			}else if(dateCode.equals("1")){	
				//一个星期
				request.setAttribute("dateCode", dateCode);
				walletList=userPurseService.selectByUserId(user.getId(),DateHelper.getBeforeDate(-7));	
			}else if(dateCode.equals("2")){
				walletList=userPurseService.selectByUserId(user.getId(),DateHelper.getDateByInterval(Calendar.MONTH, -1));
				request.setAttribute("dateCode", dateCode);	
			}else{	
				walletList=userPurseService.selectByUserId(user.getId(),DateHelper.getDateByInterval(Calendar.MONTH, -3));
				request.setAttribute("dateCode", dateCode);
			}
		}
		model.addAttribute("walletList", walletList);
		
		return "/jsp/person/walletlist";
	}
	
	
	
	
	
	
	
}

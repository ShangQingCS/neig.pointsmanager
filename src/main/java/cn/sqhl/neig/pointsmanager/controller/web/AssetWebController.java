package cn.sqhl.neig.pointsmanager.controller.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.po.NsUserGrade;
import cn.sqhl.neig.pointsmanager.po.NsUserPurse;
import cn.sqhl.neig.pointsmanager.service.CouponService;
import cn.sqhl.neig.pointsmanager.service.UserPurseService;
import cn.sqhl.neig.pointsmanager.service.UserService;
import cn.sqhl.neig.pointsmanager.utils.DateHelper;
import cn.sqhl.neig.pointsmanager.utils.PageCond;


@Controller
@RequestMapping("/asset_web")
public class AssetWebController extends basicInfo{
	private Logger logger=LogManager.getLogger(AssetWebController.class);
	
	@Autowired
	private CouponService couponService;
	@Autowired
	private UserPurseService userPurseService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/coupon")
	public String coupon(HttpServletRequest request,HttpServletResponse response,Model model){
		
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			List<NsUserCoupon> myCouponList= couponService.selectByUserId(user.getId(), null);
			model.addAttribute("myCouponList", myCouponList);
			List<NsUserCoupon> expireCouponList= couponService.selectByUserId(user.getId(), "1");
			model.addAttribute("expireCouponList", expireCouponList);
			return "/jsp/person/coupon";
		}else{
			return "/login";
			
		}
		
	}
	
	@ResponseBody
	@RequestMapping("/delcoupon")
	public JSONObject information(HttpServletRequest request,HttpServletResponse response,Model model,			
			@RequestParam(value="id",required=false) String id
			) throws Exception{		
		int result=0;
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			result=couponService.deleteObj(Long.valueOf(id));
		}		
		JSONObject rsJson = new JSONObject();
		rsJson.put("msg", result);
		return rsJson;
	}
	
	@RequestMapping("/walletlist")
	public String myWalletlist(HttpServletRequest request,HttpServletResponse response,Model model){
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		List<NsUserPurse> walletList=new ArrayList<NsUserPurse>();
		String dateCode=request.getParameter("dateCode");
		if(user!=null){
			if(StringUtils.isNotEmpty(dateCode)){
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
			
			
			if(request.getParameter("type").equals("0")){
				return "/jsp/person/walletlist";
			}else if(request.getParameter("type").equals("1")){
				
				return "/jsp/person/mypoints";
			}else{
				
				return "/jsp/person/bonus";
			}
		}else{
			
			return "/login";
		}
	}
	@RequestMapping("/myteam")
	public String safety(HttpServletRequest request,HttpServletResponse response,Model model){
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){			
			String nowpage=request.getParameter("nowpage");
			//每页显示6条数据
			int pageCount=6;
			Long userid=user.getId();						
			if(StringUtils.isBlank(nowpage)){
				nowpage="0";
			}
		
			System.out.println(nowpage+"-----------");
			
			PageCond page=new PageCond(Integer.parseInt(nowpage)*pageCount,pageCount);
			
			List<NsUser> userList=userService.queryByUserPid(page,user.getId());
			
			/**
			 * 分页步骤
			 * 实现方案:
				总页数sumPage
				当前页码nowpage
				每页显示6条数据
			 */
			
			int p=0;//默认为第一页
			if(nowpage!=null||"".equals(nowpage)){
				p=Integer.parseInt(nowpage);
			}
			//总记录数
			int sumPage=page.getTotalRows();
				
			//求出总页数
			int count=page.getTotalPage();
				
			if(p>count-1){
				p=count;
					 
			}else if(p<=0){
				p=0;					 
			}
				
			if(!page.isFirst()&&!page.isLast()||userList.size()>pageCount){
				
				List<NsUser> list=new ArrayList<NsUser>();							
				for (int i = 0; i <pageCount; i++) {
					list.add(userList.get(i));										
					userList.add(list.get(i));
				}
				userList.removeAll(userList);
				for (int i = 0; i <pageCount; i++) {														
					userList.add(list.get(i));
				}
			}								
			request.setAttribute("userList", userList);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("sumPage", sumPage);
			request.setAttribute("sumCount", count);
			request.setAttribute("nowpage", p);	
			return "/jsp/person/myteam";
		}else{			
			return "/login";
		}
		
	}
	@RequestMapping("/vip")
	public String vip(HttpServletRequest request,HttpServletResponse response,Model model){
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			List<NsUserGrade> gradeList= couponService.selectUserGrade();
			
			request.setAttribute("gradeList", gradeList);
			
			return "/jsp/person/vip";
			
		}else{
			return "/login";
		}
		
	}
	
	@RequestMapping("/usercenter")
	public String usercenter(HttpServletRequest request,HttpServletResponse response,Model model){
		NsUser user=(NsUser) request.getSession().getAttribute("user");
		if(user!=null){
			long userGradeId = user.getUserGrade();
			NsUserGrade userGrade = couponService.selectUserGradebyID(userGradeId);
				
			List<NsUserCoupon> myCouponList= couponService.selectByUserId(user.getId(), null);
			int numCoupon=myCouponList.size();
			request.setAttribute("numCoupon", numCoupon);
			request.setAttribute("userGrade", userGrade);
			return "/jsp/person/usercenter";
		}else{
			return "/login";
		}
	}
	
	
	
}

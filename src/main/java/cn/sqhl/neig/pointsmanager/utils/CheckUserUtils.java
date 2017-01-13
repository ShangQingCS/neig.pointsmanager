package cn.sqhl.neig.pointsmanager.utils;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.UserService;

public class CheckUserUtils {
	private static NsUser user;
	public static boolean checkUser(UserService userService,String username,String tel){
		
		boolean result=false;
		if(StringUtils.isEmpty(tel)){
			user =userService.queryByUserName(username,null);
			
			if(user==null){
				result=true;	
			}	
		}else if(StringUtils.isEmpty(username)){
			user =userService.queryByUserPhone(tel,null);
			if(user==null){
				result=true;	
			}	
			
		}else{
			user =userService.queryByUserName(username,null);
			
			NsUser userTel =userService.queryByUserPhone(tel,null);
			if(user==null&&userTel==null){				
				result=true;
			}
		}
		return result;
	}
	
	public static boolean isNum(String str){ 
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$"); 
	}
	
	public static boolean checkMobileCode(String mobileCode,HttpSession session){
		boolean result=false;
		if(StringUtils.isNotEmpty((String) session.getAttribute("mobileCode"))){
			if(session.getAttribute("mobileCode").equals(mobileCode)){
				
				result=true;	
			}	
		}
		return  result;
	}
		
	
	
	
}

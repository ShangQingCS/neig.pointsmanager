package cn.sqhl.neig.pointsmanager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.service.impl.UserServiceImpl;
import cn.sqhl.neig.pointsmanager.utils.DataSecret;
import cn.sqhl.neig.pointsmanager.utils.DateHelper;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
import cn.sqhl.neig.pointsmanager.utils.MD5Util;
import cn.sqhl.neig.pointsmanager.utils.SmsHelper;

@Controller
@RequestMapping("/getway")
public class GateWayController extends ContextInfo {
	
	
	@Autowired
	private UserServiceImpl userService;

	private final String ver = "v1.0"; // 接口版本

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		Map<String, Object> outParams = new HashMap<String, Object>();// 存储出流参数信息
		

		if (request.getRequestURL().indexOf("https://") == 0) {
			
			Object dataParams = null;// 存储出流data信息
			
			outParams.put("ver", ver);
			outParams.put("result", "0");
			outParams.put("message", "成功");
			String params = request.getParameter("params"); // 获取输入流
			
			if (params != null && !params.isEmpty()) {// 解密
				
				System.out.println("解密后的字符串"+ DataSecret.decryptDES(params));
				
				Map<String, String> inParams = JSON.parseObject(DataSecret.decryptDES(params),Map.class);

					if (inParams != null && inParams.get("services") != null) {// 调用方法

						String services = inParams.get("services");
						
						NsUser nsUser = new NsUser();
						
						//-------------必须参数-----------------
						String userName = inParams.get("username");
						//--------------------------------------
						String passWord = inParams.get("password");  //登陆密码
						String payWord = inParams.get("pay_pwd"); //支付
						String user_phone = inParams.get("user_phone");//电话
						String user_dj_balance =   inParams.get("user_dj_balance");//冻结金额
						String tixian_status   = inParams.get("tixian_status");//提现状态
						String nick_name= inParams.get("tixian_status");  //昵称
						String user_mail = inParams.get("tixian_status"); //邮箱
						String user_sex = inParams.get("tixian_status");  //性别
						String true_name = inParams.get("true_name"); //真实姓名
						String identity_card = inParams.get("identity_card");//身份证号
						String identity_card_validity = inParams.get("identity_card_validity");//有效期
						String identity_issuing = inParams.get("identity_issuing");//发证机关
						String identity_status = inParams.get("identity_status"); //申请认证为1
						
						switch (services) {
						case "user_login"://用户登陆 必须参数  username password
							
							
							
							
							if(userName!=null && passWord!=null && !userName.equals("") && !passWord.equals("")){
								
								//根据 用户名 密码（MD5加密）  状态 查询user对象  
								nsUser = userService.queryByUserName(userName, MD5Util.MD5(passWord));
								
								if(nsUser==null){
									nsUser = userService.queryByUserPhone(userName, MD5Util.MD5(passWord));
								}
							}

							dataParams = nsUser;
							//返回单个对象
							outParams.put("data", dataParams);
							
							break;
							case "user_update"://用户更新
								
								nsUser = userService.queryByUserName(userName, null);
								
								//修改用户密码 分支 
								if(passWord!=null && !passWord.equals("")){
								
									nsUser.setLoginPwd(MD5Util.MD5(passWord));
									userService.updateByLoginPwd(nsUser);

								}
								//修改支付密码 分支
								if(payWord!=null && !payWord.equals("")){
									
									nsUser.setPayPwd(MD5Util.MD5(payWord));
									userService.updateByPayPwd(nsUser);

								}
								
								//修改实名认证 分支
								if(identity_status!=null && identity_status.equals("1")){
									
									
									nsUser.setIdentityCard(identity_card);
									nsUser.setIdentityCardValidity(DateHelper.sdfd.parse(identity_card_validity));
									nsUser.setIdentityIssuing(identity_issuing);
									nsUser.setIdentityStatus(1);
									userService.updateByIDcard(nsUser);
								}
								
								
								//修改手机号码 分支
								
								
								
								
								
								//提现分支
							
								dataParams = nsUser;
								//返回单个对象
								outParams.put("data", dataParams);
							
							break;	
							case "phone_getcode"://获取手机验证码 必须参数 telphone
								JSONObject jsonObject = new JSONObject();
								
								String telphone = inParams.get("telphone");
								if(telphone!=null && !telphone.equals("")){
									
									String getcode = FormatUtils.getRandom();
									jsonObject.put("telphone", telphone);
									jsonObject.put("getcode", getcode);
									
									SmsHelper.sendSms(telphone, getcode);
								}
								dataParams = jsonObject;
								outParams.put("data", dataParams);
								
							break;	
							
							case "user_getpurselist"://钱包流水查询接口  类型： 积分、分红、可用余额（分页）
								
								//username purse_type  最新10条   一个月内   三个月内
								
								
							break;	
							case "user_getcouponlist"://优惠券查询接口（人、分页）
								
								//username coupon
							
							break;
							
							case "user_getgradelist"://会员分级查询接口
							
								
							break;	
							
							case "user_getuserinfo"://用户信息查询
								
								//username
								
								
							break;	
							case "user_getuserlist"://团队成员查询接口
								
							   //
								
								
							break;	
							
						default:
							outParams.put("result", "1");
							outParams.put("message", "未找到" + services + "服务名");
							break;
						}

						
						
						
						outParams.put("data", JSON.toJSONString(dataParams));

					}

			} else {
				outParams.put("result", "1");
				outParams.put("message", "没有传入参数");
			}

			

		} else {
			outParams.put("ver", ver);
			outParams.put("result", "1");
			outParams.put("message", "请使用https请求接口");

		}


		return DataSecret.encryptDES(JSON.toJSONString(outParams));
		
	}

}

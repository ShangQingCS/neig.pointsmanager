package cn.sqhl.neig.pointsmanager.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.po.NsGoods;
import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.po.NsUserGrade;
import cn.sqhl.neig.pointsmanager.po.NsUserPurse;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.service.OrderService;
import cn.sqhl.neig.pointsmanager.service.impl.CouponServiceImpl;
import cn.sqhl.neig.pointsmanager.service.impl.GoodsServiceImpl;
import cn.sqhl.neig.pointsmanager.service.impl.OrderServiceImpl;
import cn.sqhl.neig.pointsmanager.service.impl.UserPurseServiceImpl;
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
	@Autowired
	private CouponServiceImpl couponService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private UserPurseServiceImpl purseService;

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
						//-----------------优惠券------------------
						String coupon_status = inParams.get("coupon_status");  //优惠券状态
						
						//-----------------钱包 分红 积分 日志表------------
						
						String purse_type = inParams.get("purse_type");  //钱包类型 0-可用余额   1-三级分销   2-积分
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date_time = new Date();
						
						if(inParams.get("date_time")!=null){
							date_time= (Date)sdf.parse(inParams.get("date_time"));
						}
						
						//-----------------订单---------------------------
						
						String order_status = inParams.get("order_status"); //订单状态
						
						
						switch (services) {
						case "user_login":
							
							//用户登陆 必须参数  username password
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
								if(user_phone!=null){
									nsUser.setUserPhone(user_phone);
									userService.updateObj(nsUser);
									
								}

								//提现分支
								if(tixian_status!=null){
									nsUser.setTixianStatus(Integer.parseInt(tixian_status));
									nsUser.setUserDjBalance(new BigDecimal(user_dj_balance));
								}
							
								dataParams = nsUser;
								//返回单个对象
								outParams.put("data", dataParams);
							
							break;	
							case "phone_getcode"://获取手机验证码 必须参数 telphone
								/*
								 * 必要参数 telphone
								 */
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
							
							case "user_getpurselist"://钱包流水查询接口  类型： 积分、分红、可用余额
								/*
								 * 必要参数 username  purse_type  开始时间 date_time 截至都是今天
								 */
								nsUser = userService.queryByUserName(userName, null);
								List<NsUserPurse> purseList = purseService.queryByUserId(nsUser.getId(),purse_type, date_time);
								dataParams =purseList;
								outParams.put("data", dataParams);
								
							break;	
							case "user_getcouponlist"://优惠券查询接口
								/*
								 * 必要参数 username  coupon_status  0未使用1已使用2作废 
								 */
								nsUser = userService.queryByUserName(userName, null);
								List<NsUserCoupon> couponlist=couponService.selectByUserId(nsUser.getId(), coupon_status);
								dataParams =couponlist;
								outParams.put("data", dataParams);
								
							
							break;
							
							case "user_getgradelist"://会员等级全部信息查询接口
								/*
								 * 无必要参数
								 */
								List<NsUserGrade> gradeList= couponService.selectUserGrade();
								dataParams =gradeList;
								outParams.put("data", dataParams);
							break;	
							
							case "user_getuserinfo"://用户信息查询
								/*
								 * 必要参数username
								 */
								nsUser = userService.queryByUserName(userName, null);
								dataParams =nsUser;
								outParams.put("data", dataParams);
								
							break;	
							case "user_getuserlist"://团队成员查询接口
								
							   //必要参数username
								
								nsUser = userService.queryByUserName(userName, null);
								
								List<NsUser> nsUsers = userService.queryByUserPid(nsUser.getId());
								dataParams = nsUsers;
								outParams.put("data", dataParams);
								
							break;	
							
							case "user_getuserorder"://用户订单查询
								
								   //必要参数username order_status 1 2 3 4
									
									nsUser = userService.queryByUserName(userName, null);
									
									Map map = new HashMap();
									map.put("userid", nsUser.getId());
									map.put("status", order_status);
									List orderList = orderService.queryObj(map) ;
									dataParams = orderList;
									outParams.put("data", dataParams);
									
								break;	
								
							case "user_getuserorderDetail"://单个订单查询
								
								   //必要参数 order id

									List orderDetailList = null;
									dataParams = orderDetailList;
									outParams.put("data", dataParams);
									
								break;	
							
						default:
							outParams.put("result", "1");
							outParams.put("message", "未找到" + services + "服务名");
							break;
						}

						
//						System.out.println(JSON.toJSONString(dataParams));
//						
//						outParams.put("data", JSON.toJSONString(dataParams));

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

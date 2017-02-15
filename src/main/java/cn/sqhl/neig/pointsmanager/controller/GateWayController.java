package cn.sqhl.neig.pointsmanager.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.sqhl.neig.pointsmanager.pay.IPay;
import cn.sqhl.neig.pointsmanager.pay.util.UtilOther;
import cn.sqhl.neig.pointsmanager.po.NsGoods;
import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.po.NsUserCoupon;
import cn.sqhl.neig.pointsmanager.po.NsUserGrade;
import cn.sqhl.neig.pointsmanager.po.NsUserPurse;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.service.OrderService;
import cn.sqhl.neig.pointsmanager.service.UserPurseService;
import cn.sqhl.neig.pointsmanager.service.impl.CouponServiceImpl;
import cn.sqhl.neig.pointsmanager.service.impl.GoodsServiceImpl;
import cn.sqhl.neig.pointsmanager.service.impl.OrderServiceImpl;
import cn.sqhl.neig.pointsmanager.service.impl.UserPurseServiceImpl;
import cn.sqhl.neig.pointsmanager.service.impl.UserServiceImpl;
import cn.sqhl.neig.pointsmanager.utils.DataSecret;
import cn.sqhl.neig.pointsmanager.utils.DateHelper;
import cn.sqhl.neig.pointsmanager.utils.FormatUtils;
import cn.sqhl.neig.pointsmanager.utils.MD5Util;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
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
	private UserPurseService userPurseService;
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
		outParams.put("ver", ver);
		outParams.put("result", "0");
		outParams.put("message", "连接接口成功");
			
			
			String params = request.getParameter("params"); // 获取输入流
			
			if (params != null && !params.isEmpty()) {// 解密
				

                try{ //try catch 控制解密失败的处理
				
				Map<String, String> inParams = JSON.parseObject(DataSecret.decryptDES(params),Map.class);
				
				
				/**
				 * 返回用到的对象 
				 */
				NsUserPurse nsUserPurse = null;
				NsUser nsUser = null;
				Map map = new HashMap();
				
				

					if (inParams != null && inParams.get("services") != null) {// 调用方法

						String services = inParams.get("services");
						
						nsUser = new NsUser();
						
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
						
						
						//------------------提现--充值----------------------
						
						String trade_type = inParams.get("trade_type"); //支付方式    0 系统1支付宝2微信3银联
						String pay_account  = inParams.get("pay_account"); 
						String pay_open_bank = inParams.get("pay_open_bank"); 
						String trade_amount = inParams.get("trade_amount"); //金额
						
						String trade_sn = inParams.get("trade_sn"); //订单编号
						
						
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

							//返回单个对象
							outParams.put("data", nsUser);
							
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
							
								//返回单个对象
								outParams.put("data", nsUser);
							
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
								outParams.put("data", jsonObject);
								
							break;	
							
							case "user_getpurselist"://钱包流水查询接口  类型： 积分、分红、可用余额
								/*
								 * 必要参数 username  purse_type  开始时间 date_time 截至都是今天
								 */
								nsUser = userService.queryByUserName(userName, null);
								List<NsUserPurse> purseList = purseService.queryByUserId(nsUser.getId(),purse_type, date_time);
								outParams.put("data", purseList);
								
							break;	
							case "user_getcouponlist"://优惠券查询接口
								/*
								 * 必要参数 username  coupon_status  0未使用1已使用2作废 
								 */
								nsUser = userService.queryByUserName(userName, null);
								List<NsUserCoupon> couponlist=couponService.selectByUserId(nsUser.getId(), coupon_status);
								outParams.put("data", couponlist);
								
							
							break;
							
							case "user_getgradelist"://会员等级全部信息查询接口
								/*
								 * 无必要参数
								 */
								List<NsUserGrade> gradeList= couponService.selectUserGrade();
								outParams.put("data", gradeList);
							break;	
							
							case "user_getuserinfo"://用户信息查询
								/*
								 * 必要参数username
								 */
								nsUser = userService.queryByUserName(userName, null);
								outParams.put("data", nsUser);
								
							break;	
							case "user_getuserlist"://团队成员查询接口
								
							   //必要参数username
								String nowpage = inParams.get("nowpage");
								nsUser = userService.queryByUserName(userName, null);								
								//每页显示10条数据
								int pageCount=10;
								Long userid=nsUser.getId();						
								if(StringUtils.isBlank(nowpage)){
									nowpage="0";
								}								
								PageCond page=new PageCond(Integer.parseInt(nowpage)*pageCount,pageCount);								
								List<NsUser> nsUsers=userService.queryByUserPid(page,userid);
								int p=0;//默认为第一页
								if(!StringUtils.isBlank(nowpage)){
									p=Integer.parseInt(nowpage);
								}
								//求出总页数
								int sumPage=page.getTotalPage();
									
								//总记录数
								int sumCount=page.getTotalRows();
									
								if(p>sumPage-1){
									p=sumPage;
										 
								}else if(p<=0){
									p=0;					 
								}
								//每页只需要10条数据多余的清除	
								if(!page.isFirst()&&!page.isLast()||nsUsers.size()>pageCount){
									
									List<NsUser> list=new ArrayList<NsUser>();							
									for (int i = 0; i <pageCount; i++) {
										list.add(nsUsers.get(i));										
										nsUsers.add(list.get(i));
									}
									nsUsers.removeAll(nsUsers);
									for (int i = 0; i <pageCount; i++) {														
										nsUsers.add(list.get(i));
									}
								}
								JSONObject pageJson = new JSONObject();
								pageJson.put("nsUsers", nsUsers);
								pageJson.put("sumCount", sumCount);
								pageJson.put("sumPage", sumPage);
								pageJson.put("nowpage", nowpage);
								
								outParams.put("data", pageJson);
								
							break;	
							
							case "user_getuserorder"://用户订单查询
								
								   //必要参数username order_status 1 2 3 4
									
									nsUser = userService.queryByUserName(userName, null);
									
									map = new HashMap();
									map.put("userid", nsUser.getId());
									map.put("status", order_status);
									List orderList = orderService.queryObj(map) ;
									outParams.put("data", orderList);
									
								break;	
								
							case "user_getuserorderDetail"://单个订单查询
								
								   //必要参数 order id

									List orderDetailList = null;
									
									outParams.put("data", orderDetailList);
									
								break;	
								
							
							case "user_tixian"://提现申请
								
								/*
								 * 必要参数  username  tixian_status user_dj_balance trade_type  pay_account  pay_open_bank
								 */
								
								nsUser = userService.queryByUserName(userName, null);
								  
								//提现分支
								if(nsUser.getTixianStatus()==0){ //没有正在申请的提现 才可以提现
									
									
									nsUser.setTixianStatus(1); //设置提现状态 申请中
									nsUser.setUserDjBalance(new BigDecimal(user_dj_balance)); //冻结金额
									nsUser.setUserFxBalance(nsUser.getUserFxBalance().subtract(new BigDecimal(user_dj_balance))); //剩余金额
									//数据库操作
									// 更新 nsuser
									userService.updateObj(nsUser);
									
									
									// 写入 pruse
									
									String tradeSn = FormatUtils.getOrderIdByUUId();
									
									tradeSn = "S"+tradeSn; //充值单唯一对账编号
									
									if(trade_type.equals("1")){//支付宝
										tradeSn = "Z"+tradeSn;
									} else if(trade_type.equals("2")){//微信
										tradeSn = "W"+tradeSn;
									} else if(trade_type.equals("3")){//银联
										tradeSn = "U"+tradeSn;
										
									}
									
									nsUserPurse=new NsUserPurse();
									nsUserPurse.setOptionType("1");
									nsUserPurse.setTradeSn(tradeSn);
									nsUserPurse.setPurseStatus(nsUser.getUserStatus().toString());
									nsUserPurse.setTradeType(trade_type);
									nsUserPurse.setTradeState("1");
									nsUserPurse.setPurseType(1);
									nsUserPurse.setPayAccount(pay_account);
									nsUserPurse.setPayOpenBank(pay_open_bank);
									nsUserPurse.setOptionAdminid(0l);
									nsUserPurse.setCreateTime(new Date());
									nsUserPurse.setTradeAmount(new BigDecimal(user_dj_balance));
									nsUserPurse.setUserAmount(nsUser.getUserFxBalance());
									nsUserPurse.setUserId(nsUser.getId());			
									userPurseService.addObj(nsUserPurse);
									
									
									List tradeList = new ArrayList();
									tradeList.add(nsUserPurse);
									tradeList.add(nsUser);
									
									outParams.put("data", tradeList);
									
								}else{
									
									outParams.put("data", "已有一笔提现申请");
									
								}
								
								break;	
								
								
							  case "user_tochongzhi"://充值申请  返回充值流水
								
								nsUser = userService.queryByUserName(userName, null);
								
								/**
								 * 用户名
								 * 金额额
								 * 充值类型  微信  支付宝
								 */
								  
								   IPay ipay = IPay.getInstance();
									// 写入 pruse
								   
								   String returnString = "";
								   
									String tradeSn = FormatUtils.getOrderIdByUUId();
									
									tradeSn = "S"+tradeSn; //充值单唯一对账编号
									
									if(trade_type.equals("1")){//支付宝
										tradeSn = "Z"+tradeSn;
										
										returnString = ipay.createPayAPP("aliPay", "", UtilOther.yuanToFen(trade_amount), tradeSn,"贝翔商城"+trade_amount+"元储值卡");
										
									} else if(trade_type.equals("2")){//微信
										tradeSn = "W"+tradeSn;
										returnString = ipay.createPayAPP("wechatPay", "app", UtilOther.yuanToFen(trade_amount), tradeSn,"贝翔商城"+trade_amount+"元储值卡");
										
									} else if(trade_type.equals("3")){//银联
										tradeSn = "U"+tradeSn;
									}
									
									nsUserPurse=new NsUserPurse();
									nsUserPurse.setOptionType("1");
									nsUserPurse.setTradeSn(tradeSn);                           //充值唯一SN
									nsUserPurse.setPurseStatus(nsUser.getUserStatus().toString());
									nsUserPurse.setTradeType(trade_type);                      //支付方式
									nsUserPurse.setTradeState("0");                            //充值状态 0 
									nsUserPurse.setPurseType(1);                               //钱包类型  可用余额
									nsUserPurse.setOptionAdminid(0l);
									nsUserPurse.setCreateTime(new Date());
									nsUserPurse.setTradeAmount(new BigDecimal(trade_amount));  //充值金额
									nsUserPurse.setUserAmount(nsUser.getUserFxBalance());
									nsUserPurse.setUserId(nsUser.getId());			
									userPurseService.addObj(nsUserPurse);
									
									//List tradeList = new ArrayList();
									//tradeList.add(nsUserPurse);
									//tradeList.add(returnString);

									
									outParams.put("data", nsUserPurse);
									
								break;	
								

							  case "user_getchongzhi"://获取充值结果    
								  
								    /**
								     * 必要参数： username  tradeSn 
								     */
		
									nsUser = userService.queryByUserName(userName, null);
									
									map = new HashMap();
									map.put("userid", nsUser.getId());
									map.put("tradesn", trade_sn);
									
									nsUserPurse=(NsUserPurse)userPurseService.queryObj(map);
									  
									
									outParams.put("data", nsUserPurse);
									break;	
								
								
								
								
								
							
						default:
							outParams.put("result", "1");
							outParams.put("message", "未找到" + services + "服务名");
							break;
						}

						
//						System.out.println(JSON.toJSONString(dataParams));
//						outParams.put("data", JSON.toJSONString(dataParams));

					}
                }catch(Exception e){
                	outParams.put("result", "1");
    				outParams.put("message", "解密失败");
                }

			} else {
				outParams.put("result", "1");
				outParams.put("message", "没有传入参数");
			}

			


		return DataSecret.encryptDES(JSON.toJSONString(outParams));
		
	}

}

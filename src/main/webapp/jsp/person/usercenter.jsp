<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
		<jsp:include page="/common/include.jsp"></jsp:include>
		<title>个人中心</title>

		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/vipstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
	</head>

	<body>

		<!--顶部导航条 -->
		<jsp:include page="/common/header.jsp"></jsp:include>
		<!--悬浮搜索框-->
		<jsp:include page="/common/searchbar.jsp"></jsp:include>
		<!-- catagroy -->
		<jsp:include page="/common/shopNav.jsp"></jsp:include>
		
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">
					<div class="wrap-left">
						<div class="wrap-list">
							<div class="m-user">
								<!--个人信息 -->

								<div class="m-userinfo">
									<a href="javascript:;">
										<div class="tipsBox"></i></div>
									</a>
									<div class="m-baseinfo" style="background:url(${path}/images/icon_bg.png) no-repeat 0 0;">
										<a class="m-pic" href="javascript:;">
											<img src="${path }/images/Avatar${user.userSex }.jpg">
										</a>
										<div class="m-info">
											<em class="s-name">${user.userName} </em>
											<div class="vip1"><a href="#"><span>会员等级</span><em><c:if test="${user.userGrade ==null}">0</c:if>${user.userGrade }</em></a></div>
											<div class="safeText"><a href="${path }/asset_web/vip.do"><span style="margin-left:45px">查看会员等级说明</span></a>
												<div class="progressBar"><span style="left: 0px" class="progress"></span></div>
											</div>
											<div class="m-address">
												<a href="${path }/user_web/user/address.do" class="i-trigger">收货地址<i class="am-icon-angle-right" style="padding-left:5px ;"></i></a>
											</div>
										</div>
									</div>
									<div class="m-right">
										<div class="m-new">
											<a href="javascript:;"><i class="am-icon-dropbox  am-icon-md" style="padding-right:5px ;"></i>消息盒子</a>
										</div>

									</div>
								</div>

								<!--个人资产-->
								<div class="m-userproperty">
									<div class="s-bar">
										<i class="s-icon"></i>我的资产
									</div>
									<p class="m-coupon">
										<a href="${path }/asset_web/coupon.do">
											<em class="m-num">${numCoupon } 张</em>
											<span class="m-title">优 &nbsp;惠 &nbsp;券</span>
										</a>
									</p>
									<p class="m-wallet">
										<a href="${path }/asset_web/walletlist.do?dateCode=1&type=0">
											<em class="m-num">${user.userKyBalance }</em>
											<span class="m-title">我的钱包</span>
										</a>
									</p>
									<p class="m-bill">
										<a href="${path }/asset_web/walletlist.do?dateCode=1&type=1">
											<em class="m-num">${user.userJfBalance }</em>
											<span class="m-title">我的积分</span>
										</a>
									</p>
									<p class="m-bill">
										<a href="${path }/asset_web/walletlist.do?dateCode=1&type=2">
											<em class="m-num">${user.userFxBalance }</em>
											<span class="m-title">我的分红</span>
										</a>
									</p>
								</div>

								<!--我的钱包-->
								<div class="wallet">
									<div class="s-bar">
										<i class="s-icon"></i>快捷设置
									</div>
									<p class="m-big squareS">
										<a href="${path }/order_web/search.do">
											<i><img src="${path }/images/shopping.png"/></i>
											<span class="m-title">我的订单</span>
										</a>
									</p>
									<p class="m-big squareA">
										<a href="${path }/user_web/user/safety.do">
											<i><img src="${path }/images/safe.png"/></i>
											<span class="m-title">安全设置</span>
										</a>
									</p>
									<p class="m-big squareL">
										<a href="#">
											<i><img src="${path }/images/profit.png"/></i>
											<span class="m-title">账户充值</span>
										</a>
									</p>
								</div>

							</div>
							<div class="box-container-bottom"></div>

							<!--订单 -->
							<div class="m-order">
								<div class="s-bar">
									<i class="s-icon"></i>我的订单
									<a class="i-load-more-item-shadow" href="order.html">全部订单</a>
								</div>
								<ul>
									<li><a href="javascript:;"><i><img src="${path }/images/pay.png"/></i><span>待付款</span></a></li>
									<li><a href="javascript:;"><i><img src="${path }/images/send.png"/></i><span>待发货<em class="m-num">1</em></span></a></li>
									<li><a href="javascript:;"><i><img src="${path }/images/receive.png"/></i><span>待收货</span></a></li>
									<li><a href="javascript:;"><i><img src="${path }/images/comment.png"/></i><span>待评价<em class="m-num">3</em></span></a></li>
									<li><a href="javascript:;"><i><img src="${path }/images/refund.png"/></i><span>退换货</span></a></li>
								</ul>
								<div class="orderContentBox">
									<div class="orderContent">
										<div class="orderContentpic">
											<div class="imgBox">
												<a href="javascript:;"><img src="${path }/images/youzi.jpg"></a>
											</div>
										</div>
										<div class="detailContent">
											<a href="javascript:;" class="delivery">签收</a>
											<div class="orderID">
												<span class="time">2016-03-09</span>
												<span class="splitBorder">|</span>
												<span class="time">21:52:47</span>
											</div>
											<div class="orderID">
												<span class="num">共1件商品</span>
											</div>
										</div>
										<div class="state">待评价</div>
										<div class="price"><span class="sym">¥</span>23.<span class="sym">80</span></div>

									</div>
									<a href="javascript:void(0);" class="btnPay">再次购买</a>
								</div>

								<div class="orderContentBox">
									<div class="orderContent">
										<div class="orderContentpic">
											<div class="imgBox">
												<a href="javascript:;"><img src="${path }/images/heart.jpg"></a>
											</div>
										</div>
										<div class="detailContent">
											<a href="javascript:;" class="delivery">派件</a>
											<div class="orderID">
												<span class="time">2016-03-09</span>
												<span class="splitBorder">|</span>
												<span class="time">21:52:47</span>
											</div>
											<div class="orderID">
												<span class="num">共2件商品</span>
											</div>
										</div>
										<div class="state">已发货</div>
										<div class="price"><span class="sym">¥</span>246.<span class="sym">50</span></div>

									</div>
									<a href="javascript:void(0);" class="btnPay">再次购买</a>
								</div>
								
							</div>
							
							

							<!--优惠券积分
							<div class="twoTab">
								<div class="twoTabModel Coupon">
									<h5 class="squareTitle"><a href="#"><span class="splitBorder"></span>优惠券<i class="am-icon-angle-right"></i></a></h5>
									<div class="Box">
										<div class="CouponList">
											<span class="price">¥<strong class="num">50</strong></span>
	                                        <p class="brandName"><a href="#">ABC品牌499减50</a></p>
	                                        <p class="discount">满<span>499</span>元抵扣</p>
                                            <a  href="#" class="btnReceive">立即领取</a>
										</div>
									</div>
								</div>
								
							</div>
-->
						</div>
					</div>
					<div class="wrap-right">

						<!-- 日历-->
						<div class="day-list">
							<div class="s-title">
								Android扫码下载
							</div>
							<div class="s-box">
								
							</div>					
						</div>
						
						<div class="day-list" style="margin-top:15px">
							<div class="s-title">
								IOS扫码下载
							</div>
							<div class="s-box">
								
							</div>
							
							
						</div>
						<!--新品 
						<div class="new-goods">
						
							<div class="s-bar">
								<i class="s-icon">手机APP扫码下载</i>
								
							</div>
							
							<div class="new-goods-info">
								
							</div>
						</div>						
-->
											
     				</div>
     				
     				
     				 
				</div>
				
			<jsp:include page="/common/footer.jsp"></jsp:include>
			
			</div>
		<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
		</div>
		<script>
			$(document).ready(function() {
							loadCategory();	
			});
		</script>
		
	</body>

</html>

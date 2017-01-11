<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>

	<head>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
		<title>优惠券</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">

		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/cpstyle.css" rel="stylesheet" type="text/css">
			
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js"></script>

		<link href="${path }/css/infstyle.css" rel="stylesheet" type="text/css">
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

					<div class="user-coupon">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">优惠券</strong> / <small>Coupon</small></div>
						</div>
						<hr/>

						<div class="am-tabs-d2 am-tabs  am-margin" data-am-tabs>

							<ul class="am-avg-sm-2 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1">可用优惠券</a></li>
								<li><a href="#tab2">已用/过期优惠券</a></li>

							</ul>
							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									<div class="coupon-items">
									<!-- 循环开始 -->
									<c:forEach items="${myCouponList}" var="mycoupon">
										<div class="coupon-item coupon-item-d">
											<div class="coupon-list">
												<div class="c-type">
													<div class="c-class">
														<strong>购物券</strong>
													</div>
													<div class="c-price">
														<strong>${mycoupon.couponBlance}</strong>
													</div>
													<div class="c-limit">
														【消费满&nbsp;${mycoupon.couponXfBalance}元&nbsp;可用】
													</div>
													<div class="c-time"><span>使用期限</span><fmt:formatDate value="${mycoupon.couponCreatdate}" type="date"/>--<fmt:formatDate value="${mycoupon.couponExpirydate}" type="date"/></div>
													<div class="c-type-top"></div>

													<div class="c-type-bottom"></div>
												</div>

												<div class="c-msg">
													<div class="c-range">
														<div class="range-all">
															<div class="range-item">
																<span class="label">券&nbsp;编&nbsp;号：</span>
																<span class="txt">35730144</span>
															</div>
														</div>
													</div>
													<div class="op-btns">
														<a href="#" class="btn"><span class="txt">立即使用</span><b></b></a>
													</div>
												</div>
											</div>	
										</div>
										</c:forEach>
										<!-- END -->
									</div>
								</div>
								<!--过期  -->
								
								
								<div class="am-tab-panel am-fade" id="tab2">
									<div class="coupon-items">
									
									<c:forEach items="${expireCouponList}" var="expireCoupon">
										<div class="coupon-item coupon-item-d">
											<div class="coupon-list">
												<div class="c-type">
													<div class="c-class">
														<strong>购物券</strong>
														<span class="am-icon-trash"></span>
													</div>
													<div class="c-price">
														<strong>￥${expireCoupon.couponBlance}</strong>
													</div>
													<div class="c-limit">
														【消费满&nbsp;${expireCoupon.couponXfBalance}元&nbsp;可用】
													</div>
													<div class="c-time"><span>使用期限</span>2015-12-21--2015-12-31</div>
													<div class="c-type-top"></div>

													<div class="c-type-bottom"></div>
												</div>

												<div class="c-msg">
													<div class="c-range">
														<div class="range-all">
															<div class="range-item">
																<span class="label">券&nbsp;编&nbsp;号：</span>
																<span class="txt">35730144</span>
															</div>
														</div>
													</div>
													<div class="op-btns c-del">
														<a href="#" class="btn"><span class="txt">删除</span><b></b></a>
													</div>
												</div>
												
												<li class="td td-usestatus ">
													<div class="item-usestatus ">
														<span><img src="../images/gift_stamp_31.png"</span>
													</div>
												</li>												
											</div>
										</div>
										</c:forEach>
										<!-- END -->
									
									</div>
									
								</div>
							</div>

						</div>

					</div>

				</div>
				<!--底部-->
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>
			<!-- 菜单栏-->
			<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
		</div>
		<script>
			$(document).ready(function() {
							loadCategory();	
			});
		</script>
	</body>

</html>
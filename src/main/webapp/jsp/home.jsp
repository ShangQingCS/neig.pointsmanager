<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> -->
		<title>首页</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />
		<link href="${path }/basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="${path }/css/hmstyle.css" rel="stylesheet" type="text/css"/>
		<link href="${path }/css/skin.css" rel="stylesheet" type="text/css" />
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>
	</head>

	<body>
		<div class="hmtop">
			<!--顶部导航条 -->
			<jsp:include page="/common/header.jsp"></jsp:include>

			<!--悬浮搜索框-->
			<jsp:include page="/common/searchbar.jsp"></jsp:include>
		</div>
		<div class="banner">
  			<!--轮播 -->
			<div class="am-slider am-slider-default scoll" data-am-flexslider id="demo-slider-0">
				<ul class="am-slides">
				<c:forEach items="${headlist}" varStatus="ad">
					<li class="banner${ad.ordernumb}">
					<c:choose>
						<c:when test="${ad.linkkind == 0 }"><!-- 商品 -->
							<a href="${ad.imglink}">
						</c:when>
						<c:when test="${ad.linkkind == 1 }"><!-- 活动 -->
							<a href="${ad.imglink}">
						</c:when>
						<c:when test="${ad.linkkind == 3 }"><!-- 类别 -->
							<a href="${ad.imglink}">
						</c:when>
						<c:otherwise><!-- 其他链接 -->
							<a href="${ad.imglink}">
						</c:otherwise>
					</c:choose>
					<img src="${path }${ad.imgurl}" /></a></li>
					
				</c:forEach>
					<li class="banner1"><a href="introduction.html"><img src="${path }/images/ad1.jpg" /></a></li>
					<li class="banner2"><a><img src="${path }/images/ad2.jpg" /></a></li>
					<li class="banner3"><a><img src="${path }/images/ad3.jpg" /></a></li>
					<li class="banner4"><a><img src="${path }/images/ad4.jpg" /></a></li>
				</ul>
			</div>
			<div class="clear"></div>	
		</div>
			<div class="shopNav">
				<div class="slideall">
					   <div class="long-title"><span class="all-goods">全部分类</span></div>
					   
		        				
						<!--侧边导航 -->
						<div id="nav" class="navfull">
							<div class="area clearfix">
								<div class="category-content" id="guide_2">
									<div class="category">
										<ul class="category-list" id="js_climit_li">
											<c:forEach items="${leve1}" var="lv1">
											<li class="appliance js_toggle relative">
												<div class="category-info">
													<h3 class="category-name b-category-name"><%-- <i><img src="${path }/images/cake.png"></i> --%><a title="${lv1.cateName }">${lv1.cateName }</a></h3>
													<em>&gt;</em>
												</div>
												<div class="menu-item menu-in top">
													<div class="area-in">
														<div class="area-bg">
															<div class="menu-srot">
																<div class="sort-side">
																	<c:forEach items="${leve2}" var="lv2">
																	<c:if test="${lv1.id eq lv2.parentId }">
																	<dl class="dl-sort">
																		<dt><span title="${lv2.cateName }">${lv2.cateName }</span></dt>
																		<c:forEach items="${leve3}" var="lv3">
																		<c:if test="${lv2.id eq lv3.parentId }">
																		<dd><a title="${lv3.cateName }" href="${lv2.url }"><span>${lv3.cateName }</span></a></dd>
																		</c:if>
																		</c:forEach>
																	</dl>
																	</c:if>
																	</c:forEach>
																	
																</div>
																
															</div>
														</div>
													</div>
												</div>
												<b class="arrow"></b>	
											</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
						
					<!--轮播-->
					<script type="text/javascript">
						(function() {
							$('.am-slider').flexslider();
						});
						$(document).ready(function() {
							$("li").hover(function() {
								$(".category-content .category-list li.first .menu-in").css("display", "none");
								$(".category-content .category-list li.first").removeClass("hover");
								$(this).addClass("hover");
								$(this).children("div.menu-in").css("display", "block")
							}, function() {
								$(this).removeClass("hover")
								$(this).children("div.menu-in").css("display", "none")
							});
						})
					</script>

					<!--小导航 -->
					<%-- <div class="am-g am-g-fixed smallnav">
						<div class="am-u-sm-3">
							<a href="sort.html"><img src="${path }/images/navsmall.jpg" />
								<div class="title">商品分类</div>
							</a>
						</div>
						<div class="am-u-sm-3">
							<a href="#"><img src="${path }/images/huismall.jpg" />
								<div class="title">大聚惠</div>
							</a>
						</div>
						<div class="am-u-sm-3">
							<a href="#"><img src="${path }/images/mansmall.jpg" />
								<div class="title">个人中心</div>
							</a>
						</div>
						<div class="am-u-sm-3">
							<a href="#"><img src="${path }/images/moneysmall.jpg" />
								<div class="title">投资理财</div>
							</a>
						</div>
					</div> --%>

					<!--走马灯 -->
					<!--<div class="marqueen" style="display:none;">
						<span class="marqueen-title">商城头条</span>
						<div class="demo">

							<ul>
								<li class="title-first"><a target="_blank" href="#">
									<img src="${path }/images/TJ2.jpg"></img>
									<span>[特惠]</span>商城爆品1分秒								
								</a></li>
								<li class="title-first"><a target="_blank" href="#">
									<span>[公告]</span>商城与广州市签署战略合作协议
								     <img src="${path }/images/TJ.jpg"></img>
								     <p>XXXXXXXXXXXXXXXXXX</p>
							    </a></li>
							    
						<div class="mod-vip">
							<div class="m-baseinfo">
								<a href="person/index.html">
									<img src="${path }/images/getAvatar.do.jpg">
								</a>
								<em>
									Hi,<span class="s-name">小叮当</span>
									<a href="#"><p>点击更多优惠活动</p></a>									
								</em>
							</div>
							<div class="member-logout">
								<a class="am-btn-warning btn" href="login.html">登录</a>
								<a class="am-btn-warning btn" href="register.html">注册</a>
							</div>
							<div class="member-login">
								<a href="#"><strong>0</strong>待收货</a>
								<a href="#"><strong>0</strong>待发货</a>
								<a href="#"><strong>0</strong>待付款</a>
								<a href="#"><strong>0</strong>待评价</a>
							</div>
							<div class="clear"></div>	
						</div>																	    
							    
								<li><a target="_blank" href="#"><span>[特惠]</span>洋河年末大促，低至两件五折</a></li>
								<li><a target="_blank" href="#"><span>[公告]</span>华北、华中部分地区配送延迟</a></li>
								<li><a target="_blank" href="#"><span>[特惠]</span>家电狂欢千亿礼券 买1送1！</a></li>
								
							</ul>
                        <div class="advTip"><img src="${path }/images/advTip.jpg"/></div>
						</div>
					</div>
					<div class="clear"></div>-->
				</div>
				<script type="text/javascript">
					if ($(window).width() < 640) {
						function autoScroll(obj) {
							$(obj).find("ul").animate({
								marginTop: "-39px"
							}, 500, function() {
								$(this).css({
									marginTop: "0px"
								}).find("li:first").appendTo(this);
							})
						}
						$(function() {
							setInterval('autoScroll(".demo")', 3000);
						})
					}
				</script>
			</div>
		<div class="shopMainbg">
			<div class="shopMain" id="shopmain">
			<c:forEach items="${adkind }" var="adk">
				<div class="am-g am-g-fixed recommendation">
					<div class="am-container ">
						<div class="shopTitle ">
							<h4>${adk.name }</h4>
						</div>
					</div>
					<div class="am-g am-g-fixed floodFour">
						
						<div class="am-u-sm-7 am-u-md-4 text-two ">
							<div class="outer-con ">
								<div class="title ">
									111
								</div>									
								<div class="sub-title ">
									¥13.8
								</div>
								<i class="am-icon-shopping-basket am-icon-md  seprate"></i>
							</div>
							<a href="${path }/jsp/introduction.jsp"><img src="${path }/images/2.jpg" /></a>
						</div>
						
					</div>
					
					<c:forEach items="${otherlist}" varStatus="oth">
					<div class="am-g am-g-fixed floodFour">
					<c:choose>
						<c:when test="${oth.linkkind == 0 }"><!-- 商品 -->
							<div class="am-u-sm-7 am-u-md-4 text-two ">
								<div class="outer-con ">
									<div class="title ">
										111
									</div>									
									<div class="sub-title ">
										¥13.8
									</div>
									<i class="am-icon-shopping-basket am-icon-md  seprate"></i>
								</div>
							<a href="${path }/jsp/introduction.jsp"><img src="${path }/images/2.jpg" /></a>
							</div>
						</c:when>
						<c:when test="${oth.linkkind == 1 }"><!-- 活动 -->
							<a href="${oth.imglink}">
						</c:when>
						<c:when test="${oth.linkkind == 3 }"><!-- 类别 -->
							<a href="${oth.imglink}">
						</c:when>
						<c:otherwise><!-- 其他链接 -->
							<a href="${oth.imglink}">
						</c:otherwise>
					</c:choose>
					
					</div>
				</c:forEach>
				</div>
				<div class="clear "></div>
			</c:forEach>
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>
		</div>
		<!--引导 -->
		<!-- <div class="navCir">
			<li class="active"><a href="home.html"><i class="am-icon-home "></i>首页123123</a></li>
			<li><a href="sort.html"><i class="am-icon-list"></i>分类</a></li>
			<li><a href="shopcart.html"><i class="am-icon-shopping-basket"></i>购物车</a></li>	
			<li><a href="person/index.html"><i class="am-icon-user"></i>我的</a></li>					
		</div> -->

		<!--菜单 -->
		<%-- <div class=tip>
			<div id="sidebar">
				<div id="wrap">
					<div id="prof" class="item ">
						<a href="# ">
							<span class="setting "></span>
						</a>
						<div class="ibar_login_box status_login ">
							<div class="avatar_box ">
								<p class="avatar_imgbox "><img src="${path }/images/no-img_mid_.jpg " /></p>
								<ul class="user_info ">
									<li>用户名sl1903</li>
									<li>级&nbsp;别普通会员</li>
								</ul>
							</div>
							<div class="login_btnbox ">
								<a href="# " class="login_order ">我的订单</a>
								<a href="# " class="login_favorite ">我的收藏</a>
							</div>
							<i class="icon_arrow_white "></i>
						</div>

					</div>
					<div id="shopCart " class="item ">
						<a href="# ">
							<span class="message "></span>
						</a>
						<p>
							购物车
						</p>
						<p class="cart_num ">0</p>
					</div>
					<div id="asset " class="item ">
						<a href="# ">
							<span class="view "></span>
						</a>
						<div class="mp_tooltip ">
							我的资产
							<i class="icon_arrow_right_black "></i>
						</div>
					</div>

					<div id="foot " class="item ">
						<a href="# ">
							<span class="zuji "></span>
						</a>
						<div class="mp_tooltip ">
							我的足迹
							<i class="icon_arrow_right_black "></i>
						</div>
					</div>

					<div id="brand " class="item ">
						<a href="#">
							<span class="wdsc "><img src="${path }/images/wdsc.png " /></span>
						</a>
						<div class="mp_tooltip ">
							我的收藏
							<i class="icon_arrow_right_black "></i>
						</div>
					</div>

					<div id="broadcast " class="item ">
						<a href="# ">
							<span class="chongzhi "><img src="${path }/images/chongzhi.png " /></span>
						</a>
						<div class="mp_tooltip ">
							我要充值
							<i class="icon_arrow_right_black "></i>
						</div>
					</div>

					<div class="quick_toggle ">
						<li class="qtitem ">
							<a href="# "><span class="kfzx "></span></a>
							<div class="mp_tooltip ">客服中心<i class="icon_arrow_right_black "></i></div>
						</li>
						<!--二维码 -->
						<li class="qtitem ">
							<a href="#none "><span class="mpbtn_qrcode "></span></a>
							<div class="mp_qrcode " style="display:none; "><img src="${path }/images/weixin_code_145.png " /><i class="icon_arrow_white "></i></div>
						</li>
						<li class="qtitem ">
							<a href="#top " class="return_top "><span class="top "></span></a>
						</li>
					</div>

					<!--回到顶部 -->
					<div id="quick_links_pop " class="quick_links_pop hide "></div>

				</div>

			</div>
			<div id="prof-content " class="nav-content ">
				<div class="nav-con-close ">
					<i class="am-icon-angle-right am-icon-fw "></i>
				</div>
				<div>
					我
				</div>
			</div>
			<div id="shopCart-content " class="nav-content ">
				<div class="nav-con-close ">
					<i class="am-icon-angle-right am-icon-fw "></i>
				</div>
				<div>
					购物车
				</div>
			</div>
			<div id="asset-content " class="nav-content ">
				<div class="nav-con-close ">
					<i class="am-icon-angle-right am-icon-fw "></i>
				</div>
				<div>
					资产
				</div>

				<div class="ia-head-list ">
					<a href="# " target="_blank " class="pl ">
						<div class="num ">0</div>
						<div class="text ">优惠券</div>
					</a>
					<a href="# " target="_blank " class="pl ">
						<div class="num ">0</div>
						<div class="text ">红包</div>
					</a>
					<a href="# " target="_blank " class="pl money ">
						<div class="num ">￥0</div>
						<div class="text ">余额</div>
					</a>
				</div>

			</div>
			<div id="foot-content " class="nav-content ">
				<div class="nav-con-close ">
					<i class="am-icon-angle-right am-icon-fw "></i>
				</div>
				<div>
					足迹
				</div>
			</div>
			<div id="brand-content " class="nav-content ">
				<div class="nav-con-close ">
					<i class="am-icon-angle-right am-icon-fw "></i>
				</div>
				<div>
					收藏
				</div>
			</div>
			<div id="broadcast-content " class="nav-content ">
				<div class="nav-con-close ">
					<i class="am-icon-angle-right am-icon-fw "></i>
				</div>
				<div>
					充值
				</div>
			</div>
		</div> --%>
		<script>
			window.jQuery || document.write('<script src="${path }/basic/js/jquery.min.js "><\/script>');
		</script>
		<script type="text/javascript " src="${path }/basic/js/quick_links.js "></script>
	</body>
</html>
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
		<style type="text/css">
			div .slideall:hover > div #guide_2{ display:block; }
			div #guide_2 { display:none; }
		</style>
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
				<c:forEach items="${headlist}" var="ad">
					<li class="banner${ad.id}">
					<c:choose>
						<c:when test="${ad.linkkind == 0 }"><!-- 商品 -->
							<a href="${path }/goods_web/goods/${oth.imglink}/search.do">
						</c:when>
						<c:when test="${ad.linkkind == 1 }"><!-- 活动 -->
							<a href="${path }/index_web/${ad.imglink}/eventsgoodslist.do">
						</c:when>
						<c:when test="${ad.linkkind == 3 }"><!-- 类别 -->
							<a href="${path }/index_web/${ad.imglink}/catagroygoodslist.do">
						</c:when>
						<c:otherwise><!-- 其他链接 -->
							<a href="${ad.imglink}">
						</c:otherwise>
					</c:choose>
					<img src="${baseimg }${ad.imgurl}" /></a></li>
					
				</c:forEach>
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
																		<dd><a title="${lv3.cateName }" href="${path }/index_web/${lv3.id}/catagroygoodslist.do"><span>${lv3.cateName }</span></a></dd>
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
						<c:forEach items="${otherlist}" var="oth">
						<c:if test="${adk.id == oth.kind }">
							<div class="am-u-sm-3 am-u-md-2 text-three">
								<div class="outer-con ">
									<div class="title ">${oth.name}</div>
									<div class="sub-title ">${oth.memo }</div>
									<i class="am-icon-md "></i>
								</div>
								<c:choose>
									<c:when test="${oth.linkkind == 0 }">
										<a href="${path }/goods_web/goods/${oth.imglink}/search.do">
									</c:when>
									<c:when test="${oth.linkkind == 1 }"><!-- 活动 -->
										<a href="${path }/index_web/${oth.imglink}/eventsgoodslist.do"> 
									</c:when> 
									<c:when test="${oth.linkkind == 3 }"><!-- 类别 -->
										<a href="${path }/index_web/${oth.imglink}/catagroygoodslist.do">
									</c:when>
									<c:otherwise><!-- 其他链接 -->
										<a href="${oth.imglink}">
									</c:otherwise>
								</c:choose>
								<img src="${baseimg }${oth.imgurl}" /></a>
							</div>
						</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="clear "></div>
			</c:forEach>
			<jsp:include page="/common/footer.jsp"></jsp:include>
		</div>
	</div>
		<script>
			window.jQuery || document.write('<script src="${path }/basic/js/jquery.min.js "><\/script>');
		</script>
		<script type="text/javascript " src="${path }/basic/js/quick_links.js "></script>
	</body>
</html>
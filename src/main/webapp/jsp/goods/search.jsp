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
		<title>搜索页面</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />
		<link href="${path }/basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="${path }/css/seastyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path }/basic/js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="${path }/js/script.js"></script>
	</head>

	<body>

			<!--顶部导航条 -->
			<jsp:include page="/common/header.jsp"></jsp:include>

			<!--悬浮搜索框-->
			<jsp:include page="/common/searchbar.jsp"></jsp:include>

			
			<b class="line"></b>
           <div class="search">
			<div class="search-list">
			<jsp:include page="/common/shopNav.jsp"></jsp:include>
			<ol class="am-breadcrumb am-breadcrumb-slash">
				<li>
					商品搜索结果
				</li>
				
			</ol>
				
					<div class="am-g am-g-fixed">
						<div class="am-u-sm-12 am-u-md-12">
	                  	
							<div class="search-content">
								<div class="sort">
									
								</div>
								<div class="clear"></div>

								<ul class="am-avg-sm-2 am-avg-md-3 am-avg-lg-5 boxes">
									<c:forEach items="${list}" var="goods">
									<li>
										<div class="i-pic limit goods" >
											<input type="hidden" class="id" value="${goods.id}"/>
											<img src="${baseimg }${goods.goodimg}" />											
											<p class="title fl">${goods.gname}</p>
											<p class="price fl">
												<b>¥</b>
												<strong>${goods.price}</strong>
											</p>
											<p class="number fl">
												
											</p>
										</div>
									</li>
									</c:forEach>
								</ul>
							</div>
							
							<div class="clear"></div>
							<!--分页 -->
							<ul class="am-pagination am-pagination-right">
								<li class="am-disabled"><a href="">&laquo;</a></li>
								<li class="am-active"><a href="#">1</a></li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#">5</a></li>
								<li><a href="#">&raquo;</a></li>
							</ul>

						</div>
					</div>
					<jsp:include page="/common/footer.jsp"></jsp:include>
				</div>
			</div>
		<script>
			window.jQuery || document.write('<script src="${path }/basic/js/jquery-1.9.min.js"><\/script>');
			
			$(document).ready(function(){
				$("ul").on("click",".goods",function(){
					var goodsid=$(".goods").find("input.id").val();
					var url="${path}/goods_web/goods/"+goodsid+"/search.do";
					location.href = url;
				});
			});
		</script>
		<script type="text/javascript" src="${path }/basic/js/quick_links.js"></script>

<div class="theme-popover-mask"></div>

	</body>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
		<jsp:include page="/common/include.jsp"></jsp:include>
		<title>我的团队</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/infstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
		<link href="${path }/css/point.css" rel="stylesheet" type="text/css">
		
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
					<div class="points">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">我的团队</strong> / <small>My&nbsp;Point</small></div>
						</div>
						<hr/>
						<!--  
						<div class="pointsTitle">
						   <div class="usable">可用积分<span>120</span></div>
						   <div class="pointshop"><a href="#"><i><img src="${path }/images/u5.png" /></i>积分商城</a></div>
						   <div class="signIn"><a href="#"><i class="am-icon-calendar"></i><em>+5</em>每日签到</a></div>
						</div>
						-->
						<div class="pointlist am-tabs" data-am-tabs>
							<ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1">全部</a></li>
								<!-- 
								<li><a href="#tab2">获得</a></li>
								<li><a href="#tab3">支出</a></li>
								 --> 
							</ul>
							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									<table>
										<b></b>
										<thead>
											<tr>												
												<th class="th1" style="width:50%">姓名</th>
												<th class="th2">账户</th>
												
											</tr>
										</thead>										
										<tbody>
										<c:forEach items="${userList}" var="piduser">
											<tr>
												<td class="pointType">${piduser.trueName}</td>
												<td class="pointNum">${piduser.userName}</td>
												
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- 
								<div class="am-tab-panel am-fade" id="tab2">
									<table>
										<b></b>
										<thead>
											<tr>												
												<th class="th1">积分详情</th>
												<th class="th2">获取积分</th>
												<th class="th3">日期</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="pointType">订单号7745926347132商品评论</td>
												<td class="pointNum">+2</td>
												<td class="pointTime">2016-03-12&nbsp09:32</td>
											</tr>
											<tr>
												<td class="pointType">每日签到</td>
												<td class="pointNum">+5</td>
												<td class="pointTime">2016-03-12&nbsp07:32</td>
											</tr>
											<tr>
												<td class="pointType">每日签到</td>
												<td class="pointNum">+5</td>
												<td class="pointTime">2016-03-11&nbsp12:24</td>
											</tr>
											<tr>
												<td class="pointType">邮箱验证</td>
												<td class="pointNum">+50</td>
												<td class="pointTime">2016-03-10&nbsp16:18</td>
											</tr>
											<tr>
												<td class="pointType">手机绑定</td>
												<td class="pointNum">+100</td>
												<td class="pointTime">2016-03-10&nbsp15:27</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="am-tab-panel am-fade" id="tab3">
									<table>
										<b></b>
										<thead>
											<tr>												
												<th class="th1">积分详情</th>
												<th class="th2">消耗积分</th>
												<th class="th3">日期</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="pointType">积分兑换</td>
												<td class="pointNum">-300</td>
												<td class="pointTime">2016-03-10&nbsp15:27</td>
											</tr>
										</tbody>
									</table>
								</div>

-->
							</div>

						</div>
					</div>
				</div>


				
		<!--底部-->
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>
			<!-- 菜单栏-->
			<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
		
		<script>
			$(document).ready(function() {
							loadCategory();	
			});
		</script>

	</body>

</html>
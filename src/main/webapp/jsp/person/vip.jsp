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
		<title>会员等级</title>
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
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">会员等级</strong> / <small>VIP</small></div>
						</div>
						<hr/>
						
						<div class="pointlist am-tabs" data-am-tabs>
							<ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active"><a href="#tab1">全部</a></li>
								
							</ul>
							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									<table>
										<b></b>
										<thead>
											<tr>												
												<th class="th1" style="">会员等级</th>
												<th class="th2">默认</th>
												<th class="th2">一级</th>
												<th class="th2">二级</th>
												<th class="th2">三级</th>
												<th class="th2">一级满足条件</th>
												<th class="th2">团队满足条件</th>
											</tr>
										</thead>										
										<tbody>
										<c:forEach items="${gradeList}" var="userGrade">
											<tr>
												<td class="pointType">${userGrade.gradeName}</td>
												<td class="pointNum">${userGrade.gradeFcLevel}</td>
												<td class="pointNum">${userGrade.gradeFcLevel1}</td>
												<td class="pointNum">${userGrade.gradeFcLevel2}</td>
												<td class="pointNum">${userGrade.gradeFcLevel3}</td>
												<td class="pointNum">${userGrade.gradeBalance}</td>
												<td class="pointNum">${userGrade.gradeTxBalance}</td>
											</tr>
											</c:forEach>
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
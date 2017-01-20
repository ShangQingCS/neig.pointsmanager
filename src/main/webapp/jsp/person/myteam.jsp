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
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
		<link href="${path }/css/point.css" rel="stylesheet" type="text/css">
		<style type="text/css">
			.input_url{
				width:60%;
				height:30px;
				border:0px solid #eeeeee;
				font-size: 15px;
				
			}
		</style>
		<script>
			 function jsCopy(){
        		var e=document.getElementById("content");//对象是content
       			e.select(); //选择对象
        		document.execCommand("Copy"); //执行浏览器复制命令
       			layer.msg('链接复制成功!',{icon:6,time:1500}); 
    		 }
		
		</script>
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
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">我的团队</strong> / <small>My&nbsp;Team</small></div>
						</div>
						<hr/>
						
						<div class="pointlist am-tabs" data-am-tabs>
							<ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
								<!--<li class="am-active"><a href="#tab1">全部</a></li>
								 
								<li><a href="#tab2">获得</a></li>
								<li><a href="#tab3">支出</a></li>
								 --> 
							</ul>
							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									<table style="border-top-color: #fff">
										
										<thead>
											<tr>												
												<th class="th1" style="" colspan="2">			
														<input type="text" id="content"  value="${basePath}jsp/register.jsp?userPid=${user.id}" class="input_url" />
														<input type="button" name="" onclick="jsCopy()" id="btn_copy" value="复制链接" class="am-btn am-btn-primary am-btn-sm am-fl" style="float:right;margin-right:80px" />																	
												</th>
											</tr>
											<tr>
												<th class="th1" style="color:red" colspan="2">
														通过以上分享链接注册的用户，充值/消费成功后成为您的团队成员!	
												</th>
											</tr>
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
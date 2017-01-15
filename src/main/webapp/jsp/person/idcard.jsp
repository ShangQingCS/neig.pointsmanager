<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

	<head>
	<jsp:include page="/common/include.jsp"></jsp:include>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
		<title>实名认证</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/stepstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
		<script>
			function submit(){
				var truename=$('#true-name').val();
				var IDcard=$('#IDcard').val();
				var issuing=$('#issuing').val();
				var IDCardValidity=$('#issuing').val();
				if(truename!=''&&IDcard!=''&&issuing!=''&&IDCardValidity!=''){
					$.post(
							_basePath+"/user_web/user/idcardjson.do",
							{ 
								"truename":truename,
								"IDcard" :IDcard,
								"issuing":issuing	
							},function(data){
								if(data.msg>0){
								layer.msg('申请验证成功!',{icon:5,time:1500},function(){
                            				window.location.reload();});
											
								}else{
									layer.msg('申请失败!',{icon:5,time:1500});
									
								}
							},"json"
					);
			
				}else{
					layer.msg('输入信息不能为空!',{icon:5,time:1500});
				}
				
				
			
			}	
			
			
			
		
		</script>
		<style type="text/css">
		.birth-select {
    		width: 38%;
    		margin-right: 0;
    		position: relative;
    		height: 32px;
    		line-height: 32px;
    		float: left;
}
		}
		</style>

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

					<div class="am-cf am-padding">
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">实名认证</strong> / <small>Real&nbsp;authentication</small></div>
					</div>
					<hr/>
					<div class="authentication">
						<p class="tip">请填写您身份证上的真实信息，以用于报关审核</p>
						<div class="authenticationInfo">
							<p class="title">填写个人信息</p>

							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">真实姓名</label>
								<div class="am-form-content">
									<input type="text" id="true-name" placeholder="请输入您的真实姓名" value="${user.trueName }">
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-IDcard" class="am-form-label" style="width:110px" >身份证号</label>
								<div class="am-form-content">
									<input type="tel" id="IDcard" placeholder="请输入您的身份证信息" value="${user.identityCard }">
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-IDcard" class="am-form-label" style="width:110px" >发证机关</label>
								<div class="am-form-content">
									<input type="tel" id="issuing" placeholder="请输入身份证发证机关" value="${user.identityIssuing }">
								</div>
							</div>
							<div class="am-form-group">
								<label for="user-IDcard" class="am-form-label" style="width:110px">身份证有效期</label>
								<div class="am-form-content">
									<input type="tel" id="issuing" placeholder="请输入身份证发证有效日期" value="<fmt:formatDate value="${user.identityCardValidity }" type="date"/>">
								</div>
							</div>
							
							
							
						</div>
						
						<div class="info-btn">
							<c:choose>
								<c:when test="${user.identityStatus ==2}">
									
									<div class="am-btn am-btn-danger" id="btn-submit">已认证</div>
								</c:when>
								<c:when test="${user.identityStatus ==1}">
									<div class="am-btn am-btn-danger" id="btn-submit">认证中</div>
								</c:when>
								<c:otherwise>
									<div class="am-btn am-btn-danger" id="btn-submit" onclick="submit()">提交</div>
								</c:otherwise>
							</c:choose>
							
						</div>
					</div>
				</div>
				<jsp:include page="/common/footer.jsp"></jsp:include>
		</div>
				<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
				<script>
			$(document).ready(function() {
							loadCategory();	
			});
		</script>
	</body>

</html>
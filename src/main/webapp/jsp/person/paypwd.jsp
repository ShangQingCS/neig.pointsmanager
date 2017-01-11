<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">

		<title>支付密码</title>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/infstyle.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/stepstyle.css" rel="stylesheet" type="text/css">
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
		<script type="text/javascript" src="${path }/js/jquery-1.7.2.min.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		<script src="${path }/js/mobilecode.js" type="text/javascript"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<script>
			function pwdsubmit(){
			
				var mobilecode=$('#mobile_code').val();
 				var payPwd=$('#user-password').val();
	        	if(mobilecode==""){
	        		$('#msg').text('*验证码不能为空');
	        	}else {
	        		$.post(
							_basePath+"/user_web/user/paypwdjson.do",
							{ 
								"mobilecode":mobilecode,
								"payPwd"	:payPwd	
							},function(data){
								if(data.msg==1){
									layer.msg('修改密码成功',{icon:5,time:1500},function(){
                            				window.location.reload();}); 
								}else{
									layer.msg('修改密码失败',{icon:5,time:1500});
										
								}
							},"json"
					);
	        	}
			
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

					<div class="am-cf am-padding">
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">支付密码</strong> / <small>Set&nbsp;pay&nbsp;Password</small></div>
					</div>
					<hr/>
					<!--进度条-->
					<div class="m-progress">
						<div class="m-progress-list">
							<span class="step-1 step">
                                <em class="u-progress-stage-bg"></em>
                                <i class="u-stage-icon-inner">1<em class="bg"></em></i>
                                <p class="stage-name">支付密码</p>
                            </span>
							<span class="step-2 step">
                                <em class="u-progress-stage-bg"></em>
                                <i class="u-stage-icon-inner">2<em class="bg"></em></i>
                                <p class="stage-name">完成</p>
                            </span>
							<span class="u-progress-placeholder"></span>
						</div>
						<div class="u-progress-bar total-steps-2">
							<div class="u-progress-bar-inner"></div>
						</div>
					</div>
					<form class="am-form am-form-horizontal">
						<div class="am-form-group bind">
							<label for="user-phone" class="am-form-label">验证手机</label>
							<div class="am-form-content" style="margin-top:2px">
								<span id="user-phone" >${user.userPhone}</span>
							</div>
						</div>
						<div class="" style="height:30px;padding-left: 85px">
							<span id="msg" style="color:red"></span>
						</div>
						
						<div class="am-form-group code">
							
							<label for="user-code" class="am-form-label">验证码</label>
							<div class="am-form-content">
								<input type="tel" id="mobile_code" placeholder="短信验证码">
								
							</div>
							<a class="btn" href="javascript:void(0);"  id="sendMobileCode">
								<div class="am-btn am-btn-danger">获取</div>
							</a>
						</div>
						<div class="am-form-group">
							<label for="user-password" class="am-form-label">新密码</label>
							<div class="am-form-content">
								<input type="tel" id="user-password" placeholder="请输入新密码">
							</div>
						</div>
						<div class="am-form-group">
							<label for="user-confirm-password" class="am-form-label">确认密码</label>
							<div class="am-form-content">
								<input type="tel" id="user-confirm-password" placeholder="请再次输入上面的密码">
							</div>
						</div>
						<div class="info-btn">
							<div class="am-btn am-btn-danger" onclick="pwdsubmit()"><a style="color:white">保存修改</a></div>
						</div>

					</form>

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
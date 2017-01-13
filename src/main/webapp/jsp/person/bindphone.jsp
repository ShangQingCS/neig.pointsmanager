<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">

		<title>更改绑定</title>
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
		$(function(){
			
			$('#phone').focus(function(){
                    $('#msg').text('');
                }).blur(function(){
                	var phone=$('#phone').val();
                	if(phone!=''){
                		$.post(
							_basePath+"/user_web/user/checkusername.do",
							{ 
								"tel":phone,
								"username":"",	
							},function(data){
								if(data.msg){										
										
										$('#form_submit').attr('onclick','formsubmit()');
											
								}else{
									$('#msg').text('*手机已注册');
									
								}
							},"json"
						);
                	}else{
                		$('#msg').text('*手机号不能为空');
                	}
                       
				});
			});	
		</script>
		<script>
			function formsubmit(){
				var phone=$('#phone').val();
				var mobileCode=$('#mobile_code').val();
				$.post(
							_basePath+"/user_web/bindphonejson.do",
							{ 
								"tel":phone,
								"mobileCode":mobileCode	
							},function(data){
								if(data.msg>0){										
									layer.msg('修改成功!',{icon:5,time:1500},function(){
                            				window.location.reload();});	
											
								}else{
									layer.msg('修改失败!',{icon:5,time:1500});
									
								}
							},"json"
						);
				
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
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">更改绑定</strong> / <small>Bind&nbsp;Phone</small></div>
					</div>
					<hr/>
					<!--进度条-->
					<div class="m-progress">
						<div class="m-progress-list">
							<span class="step-1 step">
                                <em class="u-progress-stage-bg"></em>
                                <i class="u-stage-icon-inner">1<em class="bg"></em></i>
                                <p class="stage-name">更改绑定</p>
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
							<div class="am-form-content">
								<span id="user-phone">${user.userPhone}</span>
								<input type="hidden" name="tel" id="tel" value="${user.userPhone}"/>
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
								<div class="am-btn am-btn-danger">验证码</div>
							</a>
						</div>
						
						
						<div class="am-form-group">
							<label for="user-new-phone" class="am-form-label">更绑手机</label>
							<div class="am-form-content">
								<input type="tel" id="phone" placeholder="绑定新手机号">
							</div>
						</div>
						
						<div class="info-btn">
							<div class="am-btn am-btn-danger" id="form_submit">保存修改</div>
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta charset="utf-8">
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0"> -->
		<title>个人资料</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/infstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js" type="text/javascript"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
		<script>
			$(function(){
				$('#btn_submit').click(function(){
					var nickName=$('#nick_name').val();
					var userSex=$("input[name='radio_sex']:checked").val();
					
					var userMail=$('#user_mail').val();
					var birthday=$('#user_birthday').val();
					if(nickName !=""&&userMail !=""){
						$.post(
							_basePath+"/user_web/information.do",
							{ 
								"nickName":nickName,
								"userSex":userSex,
								"userMail":userMail,
								"birthday" :birthday	
							},function(data){
								if(data.msg>0){
									layer.msg('修改信息成功',{icon:5,time:1500},function(){
                            				window.location.reload();});		
								}else{
									layer.msg('修改信息失败',{icon:5,time:1500});
								}
							},"json"
						);
					}
				});
			});
		</script>
		<style type="text/css">
			.user_div{
				height:30px;
				margin-top:10px;
				margin-left:85px
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

					<div class="user-info">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">个人资料</strong><!--  / <small>Personal&nbsp;information</small> --></div>
						</div>
						<hr/>

	
						<!--个人信息 -->
						<div class="info-main">
							<form class="am-form am-form-horizontal">
								
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">用户名</label>
									<div class="user_div">
										<span>${user.userName } </span>

									</div>
								</div>
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">真实姓名</label>
									<div  style="height:30px;margin-top:5px;margin-left:85px">
										<span>${user.trueName } </span>
										<a href="${path }/user_web/user/idcard.do" style="margin-left:15px">
											<div class="am-btn am-btn-secondary">认证</div>
										</a>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">手机号</label>
									<div  style="height:30px;margin-top:5px;margin-left:85px">
										<span>${user.userPhone } </span>
										<a href="${path }/user_web/user/bindphone.do" style="margin-left:15px">
											<div class="am-btn am-btn-secondary">绑定/更换</div>
										</a>
									</div>
								</div>
								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">昵称</label>
									<div class="am-form-content">
										<input type="text" id="nick_name" placeholder="nickname" value="${user.nickName }">

									</div>
								</div>

								<div class="am-form-group">
									<label class="am-form-label">性别</label>
									<div class="am-form-content sex">
										
										
										<c:if test="${user.userSex ==1}">
										<label class="am-radio-inline">
										    <input type="radio" name="radio_sex" value="1" checked>  男
										    </label>	
										    <label class="am-radio-inline">
											<input type="radio" name="radio_sex" value="0" >  女
											</label>	
										</c:if>
										
										<c:if test="${user.userSex==0 }">
										<label class="am-radio-inline">
										    <input type="radio" name="radio_sex" value="1" >  男
										    </label>	
										    <label class="am-radio-inline">
											<input type="radio" name="radio_sex" value="0" checked>  女
											</label>	
										</c:if>
										
										</label>										
									</div>
								</div>

								
								
								<div class="am-form-group">
									<label for="user-email" class="am-form-label">电子邮件</label>
									<div class="am-form-content">
										<input id="user_mail" placeholder="Email" type="email" value="${user.userMail }">

									</div>
								</div>
								
								<div class="info-btn">
									<div class="am-btn am-btn-danger" id="btn_submit"><a style="color:white">保存修改</a></div>
								</div>

							</form>
						</div>

					</div>

				</div>
				<!--底部-->
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>

			<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
		</div>
		<script>
			$(document).ready(function() {
							loadCategory();	
			});
		</script>
	</body>

</html>
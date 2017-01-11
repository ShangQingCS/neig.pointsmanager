<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
									layer.msg('信息修改成功!',{icon:5,time:1500});		
								}else{
									
								}
							},"json"
						);
					}
				});
			});
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

					<div class="user-info">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">个人资料</strong><!--  / <small>Personal&nbsp;information</small> --></div>
						</div>
						<hr/>

						<!--头像 -->
						<div class="user-infoPic">

							<div class="filePic">
								<input type="file" class="inputPic" allowexts="gif,jpeg,jpg,png,bmp" accept="image/*">
								<img class="am-circle am-img-thumbnail" src="${path }/images/getAvatar.do.jpg" alt="" />
							</div>

							<p class="am-form-help">头像</p>

							<div class="info-m">
								<div><b>用户名：<i>小叮当</i></b></div>
								<div class="u-level">
									<span class="rank r2">
							             <s class="vip1"></s><a class="classes" href="#">铜牌会员</a>
						            </span>
								</div>
								<div class="u-safety">
									<a href="safety.html">
									 账户安全
									<span class="u-profile"><i class="bc_ee0000" style="width: 60px;" width="0">60分</i></span>
									</a>
								</div>
							</div>
						</div>

						<!--个人信息 -->
						<div class="info-main">
							<form class="am-form am-form-horizontal">

								<div class="am-form-group">
									<label for="user-name2" class="am-form-label">昵称</label>
									<div class="am-form-content">
										<input type="text" id="nick_name" placeholder="nickname">

									</div>
								</div>

								<div class="am-form-group">
									<label class="am-form-label">性别</label>
									<div class="am-form-content sex">
										<label class="am-radio-inline">
											<input type="radio" name="radio_sex" value="1" checked="checked"> 男
										</label>
										<label class="am-radio-inline">
											<input type="radio" name="radio_sex" value="0" data-am-ucheck> 女
										</label>										
									</div>
								</div>

								<div class="am-form-group">
									<label for="user-birth" class="am-form-label">生日</label>
									<div class="am-form-content birth">
										<div class="birth-select">
											<select data-am-selected>
												<option value="a">2015</option>
												<option value="b">1987</option>
											</select>
											<em>年</em>
										</div>
										<div class="birth-select2">
											<select data-am-selected>
												<option value="a">12</option>
												<option value="b">8</option>
											</select>
											<em>月</em></div>
										<div class="birth-select2">
											<select data-am-selected>
												<option value="a">21</option>
												<option value="b">23</option>
											</select>
											<em>日</em></div>
									</div>
							
								</div>
								
								<div class="am-form-group">
									<label for="user-email" class="am-form-label">电子邮件</label>
									<div class="am-form-content">
										<input id="user_mail" placeholder="Email" type="email">

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
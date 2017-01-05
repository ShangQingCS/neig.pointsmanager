<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head lang="en">
	<jsp:include page="/common/include.jsp"></jsp:include>
		<meta charset="UTF-8">
		<title>注册</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />

		<link rel="stylesheet" href="${path }/AmazeUI-2.4.2/assets/css/amazeui.min.css" />
		<link href="${path }/css/dlstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.min.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<script src="${path }/js/mobilecode.js" type="text/javascript"></script>
		<script>
			 $(function(){
			 	var username;
				var tel;
				var loginPwd
				var isEnable1=false;
				var isEnable2=false;
				var checkpwd=false;
				
				$('#username').focus(function(){
                    $('#msg').text('');
                }).blur(function(){
                	username=$('#username').val();
                	if(username!=''){
                		$.post(
							_basePath+"/user_web/user/checkusername.do",
							{ 
								"username":username,
								"tel" :""
									
							},function(data){
								if(data.msg){
									isEnable1=true;
											
								}else{
									$('#msg').text('*用户名已存在');
									isEnable1=false;
								}
							},"json"
						);
                	}else{
                		$('#msg').text('*用户名不能为空');
                	}
                       
				});
				
				$('#tel').focus(function(){
                    $('#msg').text('');
                }).blur(function(){
                	tel=$('#tel').val();
                	if(tel!=''){
                		$.post(
							_basePath+"/user_web/user/checkusername.do",
							{ 
								"tel":tel,
								"username":"",	
							},function(data){
								if(data.msg){
									isEnable2=true;
											
									if(isEnable1 && isEnable2){
										$('#sendMobileCode').attr('href','javascript:;');
										$('#sendMobileCode').attr('onclick','sendMobileCode()');
									} 		
								}else{
									$('#msg').text('*手机已注册');
									isEnable2=false;
								}
							},"json"
						);
                	}else{
                		$('#msg').text('*手机号不能为空');
                	}
                       
				});
				
				
				$('#passwordRepeat').focus(function(){
                    $('#msg').text('');
                }).blur(function(){
                	var passwordRepeat=$('#passwordRepeat').val();
                	loginPwd=$('#loginPwd').val();
                	if(passwordRepeat !="" && loginPwd !="" && loginPwd==passwordRepeat){
						checkpwd =true;		
					}else {
						$('#msg').text('*密码为空或两次密码输入不一致');
						checkpwd = false;
					}
                       
				});
				
				
				$("#btn_submit").click(function(){
                   	loginPwd=$('#loginPwd').val();
					username=$('#username').val();
					tel=$('#tel').val();
					if(isEnable1 && isEnable2&&checkpwd){
							$.post(
									_basePath+"/user_web/user/registerjson.do?pid=<%=request.getParameter("pid")%>",
									{ 
										"tel":tel,
										"username":username,
										"loginPwd":loginPwd,
									},function(data){
										if(data.msg >0){
										layer.msg('注册成功!',{icon:5,time:1500},function(){
                            				window.location.href="${path}/login_web/login.do"});
											
										}else{
										layer.msg('手机或用户名已注册!',{icon:5,time:1500});
											
										}
									},"json"
							);

					}else{
						layer.msg('请按规则填写信息!',{icon:5,time:1500});
					}
					
                }); 
                
			 });
				
	</script>
	</head>

	<body>

		<div class="login-boxtitle">
			<a href="home/demo.html"><img alt="" src="${path }/images/logobig.png" /></a>
		</div>

		<div class="res-banner">
			<div class="res-main">
				<div class="login-banner-bg"><span></span><img src="${path }/images/big.jpg" /></div>
				<div class="login-box">
					<div class="am-tabs" id="doc-my-tabs">
						<ul class="am-tabs-nav am-nav am-nav-tabs am-nav-justify">
							<li><a href="">手机号注册</a></li>
							</ul>
							<div class="am-tabs-bd">
							<!-- 手机注册 -->
								<div class="am-tab-panel">
									<div><span id="msg" style="margin-left:15px;color: red;font-size: 13px"><span></div>
																
									<form id="form-register" action="" method="post">
									<div class="user-name" style="margin-top:-15px">
                 						
								    	<label for="user"><i class="am-icon-user"></i></label>
								    	<input type="tel" name="username" id="username" placeholder="请输入用户名"></div>
								    	
                 					<div class="user-phone">
                 						
								    	<label for="phone"><i class="am-icon-mobile-phone am-icon-md"></i></label>
								    	<input type="tel" name="tel" id="tel" placeholder="请输入手机号"></div>
								    																			
										<div class="verification">
											<label for="code"><i class="am-icon-code-fork"></i></label>
											<input type="tel" name="" id="mobile_code" placeholder="请输入验证码" />
											<a class="btn"  onclick="return false" id="sendMobileCode">
												<span id="dyMobileButton">获取</span></a>
										</div>  
										
                 					<div class="user-pass">
								    	<label for="password"><i class="am-icon-lock"></i></label>
								    	<input type="password" name="loginPwd" id="loginPwd" placeholder="设置密码">
                 					</div>										
                 					<div class="user-pass">
								    	<label for="passwordRepeat"><i class="am-icon-lock"></i></label>
								    	<input type="password" name="passwordRepeat" id="passwordRepeat" placeholder="确认密码">
                 					</div>	
									</form>
								 	<div class="login-links">
										<label for="reader-me">
											<input id="reader-me" type="checkbox"> 点击表示您同意商城《服务协议》
										</label>
							  		</div>
									<div class="am-cf">
										<input type="button" name="" id="btn_submit" value="注册" class="am-btn am-btn-primary am-btn-sm am-fl"  />
									</div>									
									<hr>
									
								</div>
								
								<script>
									$(function() {
									    $('#doc-my-tabs').tabs();
									  })
								</script>

							</div>
						</div>

				</div>
			</div>
			
					<div class="footer ">
						<div class="footer-hd ">
							<p>
								<a href="# ">恒望科技</a>
								<b>|</b>
								<a href="# ">商城首页</a>
								<b>|</b>
								<a href="# ">支付宝</a>
								<b>|</b>
								<a href="# ">物流</a>
							</p>
						</div>
						<div class="footer-bd ">
							<p>
								<a href="# ">关于恒望</a>
								<a href="# ">合作伙伴</a>
								<a href="# ">联系我们</a>
								<a href="# ">网站地图</a>
								<em>© 2015-2025 Hengwang.com 版权所有</em>
							</p>
						</div>
					</div>
	</body>

</html>
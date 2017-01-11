<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>




	<head lang="en">
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta charset="UTF-8">
		<title>登录</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta name="renderer" content="webkit">
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<script type="text/javascript" src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<link rel="stylesheet" href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" />
		<link href="${path }/css/dlstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
	</head>

	<body>

		<div class="login-boxtitle">
			<a href="home.jsp"><img alt="logo" src="${path }/images/logobig.png" /></a>
		</div>

		<div class="login-banner">
			<div class="login-main">
				
				<div class="login-banner-bg"><span></span><img src="${path }/images/big.jpg" /></div>
				<div class="login-box">
					<h3 class="title">登录商城</h3>
					<div class="clear"></div>
					<div class="login-form">
						<form action="${path }/login_web/login.do" method="post" id="loginform">
							<div class="user-name">
								<label for="user"><i class="am-icon-user"></i></label>
								    <input type="text" name="" id="username" placeholder="手机/用户名">
                 			</div>
                 			<div class="user-pass">
								    <label for="password"><i class="am-icon-lock"></i></label>
								    <input type="password" name="" id="loginPwd" placeholder="请输入密码">
                 			</div>
                 			
                 			
                 			
              			</form>
           			</div>  
           			<!--           
            		<div class="login-links">
                		<label for="remember-me"><input id="remember-me" type="checkbox">记住密码</label>
								<a href="#" class="am-fr">忘记密码</a>
								<a href="register.jsp" class="zcnext am-fr am-btn-default">注册</a>
								<br />
            		</div>--> 
					<div class="am-cf">
						<input type="button" name="login" value="登 录" onclick="login()" class="am-btn am-btn-primary am-btn-sm" ></div>
						<!-- 
						<div class="partner">		
								<h3>合作账号</h3>
							<div class="am-btn-group">
								<li><a href="#"><i class="am-icon-qq am-icon-sm"></i><span>QQ登录</span></a></li>
								<li><a href="#"><i class="am-icon-weibo am-icon-sm"></i><span>微博登录</span> </a></li>
								<li><a href="#"><i class="am-icon-weixin am-icon-sm"></i><span>微信登录</span> </a></li>
							</div>
						</div>	-->
						
				</div>
			</div>
		</div>
<script type="text/javascript">
	function login(){
				var username=$('#username').val();
				var loginPwd=$('#loginPwd').val();
				$.post(
									_basePath+"/login_web/loginjson.do",
									{ 
										"username":username,
										"loginPwd":loginPwd,
										
									},function(data){
										if(data.msg){
											
                            				window.location.href="${path}/login_web/share.do";
										}else{
											layer.msg('账户密码有误!',{icon:5,time:1500});
										}
									},"json"
								);
			
			}
			
      
</script>

					
				<jsp:include page="/common/footer.jsp"></jsp:include>
	</body>

</html>
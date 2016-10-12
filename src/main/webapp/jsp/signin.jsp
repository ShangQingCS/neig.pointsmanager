<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html class="login-bg">
<head>
	<title>Sign in</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="../bootstrap/css/bootstrap.css" rel="stylesheet" />
    <link href="../bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
    <link href="../bootstrap/css/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="../css/layout.css" />
    <link rel="stylesheet" type="text/css" href="../css/elements.css" />
    <link rel="stylesheet" type="text/css" href="../css/icons.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="../font-awesome/css/font-awesome.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="../css/signin.css" type="text/css" media="screen" />
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
    <div class="row-fluid login-wrapper">
        <a href="index.html">
        </a>

        <div class="span4 box">
            <div class="content-wrap">
                <h6>Log in</h6>
                <input class="span12" type="text" placeholder="E-mail address" />
                <input class="span12" type="password" placeholder="Your password" />
                <a href="#" class="forgot">Forgot password?</a>
                <div class="remember">
                    <input id="remember-me" type="checkbox" />
                    <label for="remember-me">Remember me</label>
                </div>
                <a class="btn-glow primary login" href="index.html">Log in</a>
            </div>
        </div>

        <div class="span4 no-account">
            <p>Don't have an account?</p>
            <a href="signup.html">Contact Us</a>
        </div>
    </div>

</body>
</html>
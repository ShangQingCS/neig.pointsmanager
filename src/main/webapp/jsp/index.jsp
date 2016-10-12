<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<title>管理后台</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1, user-scalable=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="stylesheet" type="text/css" href="../css/cloud-admin.css" >
	<link rel="stylesheet" type="text/css"  href="../css/themes/night.css" id="skin-switcher" >
	<!-- STYLESHEETS --><!--[if lt IE 9]><script src="js/flot/excanvas.min.js"></script><script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script><script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script><![endif]-->
	<link href="../font-awesome/css/font-awesome.css" rel="stylesheet">
</head>
<body>
	<!-- HEADER -->
	<header class="navbar clearfix" id="header">
		<div class="container">
				<div class="navbar-brand">
					<!-- SIDEBAR COLLAPSE -->
					<div id="sidebar-collapse" class="sidebar-collapse btn">
						<i class="fa fa-bars" 
							data-icon1="fa fa-bars" 
							data-icon2="fa fa-bars" ></i>
					</div>
					<!-- /SIDEBAR COLLAPSE -->
				</div>
				<!-- BEGIN TOP NAVIGATION MENU -->					
				<ul class="nav navbar-nav pull-right">
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown user" id="header-user" style="float: right;">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<img alt="" src="img/avatars/avatar3.jpg">
							<span class="username">管理员账户</span>
							<i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a style="text-align: right;" href="#"> 密码修改 <i class="fa fa-cog"></i></a></li>
							<li><a style="text-align: right;" href="login.html">退 出 <i class="fa fa-power-off"></i></a></li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
				<!-- END TOP NAVIGATION MENU -->
		</div>
	</header>
	<!--/HEADER -->
	<div class="copyrights"></div>
	<!-- PAGE -->
	<section id="page">
				<!-- SIDEBAR -->
				<div id="sidebar" class="sidebar">
					<div class="sidebar-menu nav-collapse">
						<div class="divide-20"></div>
						<div class="divide-20" style="text-align:center;color:#FFFFFF;"> 欢迎您使用后台管理系统 </div>
						<!-- SIDEBAR MENU -->
						<ul>
							<li class="has-sub">
								<a ref="javascript:;" class="">
								<i class="fa fa-tablet fa-fw"></i> <span class="menu-text">系统管理</span>
								<span class="arrow"></span>
								</a>	
								<ul class="sub">
									<li><a class="" href="elements.html"><span class="sub-menu-text">操作人员管理</span></a></li>
									<li><a class="" href="elements.html"><span class="sub-menu-text">操作人员角色管理</span></a></li>
									<li><a class="" href="elements.html"><span class="sub-menu-text">操作权限管理</span></a></li>
									<li><a class="" href="elements.html"><span class="sub-menu-text">操作人员权限分配</span></a></li>
								</ul>
							</li>
							<li class="has-sub">
								<a ref="javascript:;" class="">
								<i class="fa fa-book fa-fw"></i> <span class="menu-text">商品管理</span>
								<span class="arrow"></span>
								</a>		
								<ul class="sub">
									<li><a class="" href="elements.html"><span class="sub-menu-text">商品录入</span></a></li>
									<li><a class="" href="elements.html"><span class="sub-menu-text">商品维护</span></a></li>
								</ul>
							</li>
							<li class="has-sub">
								<a href="javascript:;" class="">
								<i class="fa fa-exchange fa-fw"></i> <span class="menu-text">订单管理</span>
								<span class="arrow"></span>
								</a>
								<ul class="sub">
									<li><a class="" href="elements.html"><span class="sub-menu-text">订单查询</span></a></li>
									<li><a class="" href="elements.html"><span class="sub-menu-text">物流信息录入</span></a></li>
									<li><a class="" href="elements.html"><span class="sub-menu-text">订单状态变更</span></a></li>
								</ul>
							</li>
							<li class="has-sub">
								<a href="javascript:;" class="">
								<i class="fa fa-gear fa-fw"></i> <span class="menu-text">基础设置</span>
								<span class="arrow"></span>
								</a>
								<ul class="sub">
									<li><a class="" href="elements.html"><span class="sub-menu-text">字典管理</span></a></li>
									<li><a class="" href="elements.html"><span class="sub-menu-text">菜单管理</span></a></li>
								</ul>
							</li>
						</ul>
						<!-- /SIDEBAR MENU -->
					</div>
				</div>
				<!-- /SIDEBAR -->
				<div id="main-content">
					<div class="container">
						<div class="row">
							<div id="content" class="col-lg-12">
								<div class="row">
									<div id="content" class="col-sm-12">
										<div class="page-header">
									<!-- STYLER -->
									
									<!-- /STYLER -->
									<!-- BREADCRUMBS -->
									<ul class="breadcrumb">
										<li>
											<i class="fa fa-home"></i>
											<a href="index.html">Home</a>
										</li>
										<li>
											<a href="#">UI Features</a>
										</li>
										<li>Elements</li>
									</ul>
									<!-- /BREADCRUMBS -->
									<div class="clearfix">
										<h3 class="content-title pull-left">Elements</h3>
									</div>
									<div class="description">Common UI Elements and Features</div>
								</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
	</section>
	<!--/PAGE -->
	<!-- JAVASCRIPTS -->
	<!-- Placed at the end of the document so the pages load faster -->
	<!-- JQUERY -->
	<script src="../js/jquery/jquery-2.0.3.min.js"></script>
	<!-- BOOTSTRAP -->
	<script src="../bootstrap/js/bootstrap.js"></script>
	<!-- CUSTOM SCRIPT -->
	<script src="../js/script.js"></script>
	<script>
		$(document).ready(function() {	
			App.setPage("index");  //Set current page
			App.init(); //Initialise plugins and elements
		});
	</script>
	<!-- /JAVASCRIPTS -->
</body>
</html>
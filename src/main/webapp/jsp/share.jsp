<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<script>
			 function jsCopy(){
        		var e=document.getElementById("content");//对象是content
       			e.select(); //选择对象
        		document.execCommand("Copy"); //执行浏览器复制命令
       			layer.msg('链接复制成功!',{icon:5,time:1500}); 
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

					<div class="user-info">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">分享链接</strong><!--  / <small>Personal&nbsp;information</small> --></div>
						</div>
						<hr/>

						<!--头像 -->
						<div class="user-infoPic" style="height:180px" >
						<p>扫描以下二维码下载商城APP版</p>
							<div>							
							<img class="am-circle am-img-thumbnail" src="${path }/images/test.png" alt="" />
							<img class="am-circle am-img-thumbnail" src="${path }/images/test2.png" alt="" />
							</div>
						</div>

						<!--个人信息 -->
						<div class="info-main">
							<form class="am-form am-form-horizontal">
								<div class="am-form-group">
									<input type="text" id="content"  value="http://localhost:8080/pointsmanager/jsp/register.jsp?userPid=${user.id}" style="float:left" >
										<input type="button" name="" onclick="jsCopy()" id="btn_copy" value="复制链接" class="am-btn am-btn-primary am-btn-sm am-fl" style="width=50px;margin-top:10px" />			
								</div>
								</form>
								</div>
							
					</div>
				</div>
				<!--底部-->
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>
		</div>	
	</body>
</html>
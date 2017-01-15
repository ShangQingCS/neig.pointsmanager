<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!--顶部导航条 -->
<div class="am-container header" >
	<ul class="message-l">
		<div class="topMessage">
			<div class="menu-hd">
				<a href="javascript:;" class="h"><c:if  test="${user.userName !=null}"><span style="font-weight:600;color:#F69954">${user.userName}</span>，欢迎您</c:if></a>
				<!--<a href="#" target="_top">免费注册</a>-->
			</div>
		</div>
	</ul>
	<ul class="message-r" >
		<!-- <div class="topMessage home">
			<div class="menu-hd"><a href="${path }/jsp/home.jsp" target="_top" class="h">商城首页</a></div>
		</div> -->
		<div class="topMessage home"><a href="${path }/asset_web/usercenter.do" target="_top"><i class="am-icon-user am-icon-fw"></i>个人中心</a></div>
		<div class="topMessage my-shangcheng">
			<div class="menu-hd MyShangcheng"><a href="${path }/login_web/loginout.do" target="_top"><i class="am-icon-user am-icon-fw"></i>退出</a></div>
		</div>
		<div class="topMessage mini-cart">
			<div class="menu-hd"><a id="mc-menu-hd" href="${path }/shopcar_web/search.do" target="_top"><i class="am-icon-shopping-cart  am-icon-fw"></i><span>购物车</span><strong id="J_MiniCartNum" class="h"></strong></a></div>
		</div>
		<!--<div class="topMessage favorite">
			<div class="menu-hd"><a href="#" target="_top"><i class="am-icon-heart am-icon-fw"></i><span>收藏夹</span></a></div>
		</div>-->
	</ul>
</div>
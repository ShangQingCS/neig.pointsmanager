<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="footer ">
	<div class="footer-hd ">
		<p>
			<a href="#">贝翔电子</a>
			<b>|</b>
			<a href="${path }/index_web/home.do">商城首页</a>
			<b>|</b>
			<a href="# ">支付宝</a>
			<b>|</b>
			<a href="# ">物流</a>
		</p>
	</div>
	<div class="footer-bd ">
		<p>
			<a href="# ">关于贝翔</a>
			<a href="# ">合作伙伴</a>
			<a href="# ">联系我们</a>
			<a href="# ">网站地图</a>
			<em>© 2015-2025 Hengwang.com 版权所有</em>
		</p>
	</div>
</div>

<style>
.toolbar-item,.toolbar-layer{background:url(${path }/images/toolbar.png) no-repeat;}
.toolbar{position:fixed;right:0px;bottom:50px;}/*假设网页宽度为1200px，导航条在右侧悬浮*/
.toolbar-item{display:block;width:52px;height:52px;margin-top:1px;position:relative;-moz-transition:background-position 1s;-ms-transition:background-position 1s;-o-moz-transition:background-position 1s;-webkit-moz-transition:background-position 1s;transition:background-position 1s;}
.toolbar-item:hover .toolbar-layer{opacity:1;filter:alpha(opacity=100);transform:scale(1);-webkit-transform:scale(1);-moz-transform:scale(1);-ms-transform:scale(1);-o-transform:scale(1);}
.toolbar-item-weixin{background-position:0 -798px;}
.toolbar-item-weixin:hover{background-position:0 -860px;}
.toolbar-item-weixin .toolbar-layer{height:212px;background-position:0 0;}
.toolbar-item-feedback{background-position:0 -426px;}
.toolbar-item-feedback:hover{background-position:0 -488px;}
.toolbar-item-app{background-position:0 -550px;}
.toolbar-item-app:hover{background-position:0 -612px;}
.toolbar-item-app .toolbar-layer{height:194px;background-position:0 -222px;}
.toolbar-item-top{background-position:0 -674px;}
.toolbar-item-top:hover{background-position:0 -736px;}
.toolbar-layer{position:absolute;right:46px;bottom:-10px;width:172px;opacity:0;filter:alpha(opacity=0);
transform-origin: 95% 95%;-moz-transform-origin: 95% 95%;-ms-transform-origin: 95% 95%;-o-transform-origin: 95% 95%;-webkit-transform-origin: 95% 95%;transform:scale(0.01);
-moz-transform:scale(0.01);-ms-transform:scale(0.01);-o-transform:scale(0.01);-webkit-transform:scale(0.01);transition:all 1s;-moz-transition:all 1s;-ms-transition:all 1s;-o-transition:all 1s;-webkit-transition:all 1s;}
</style>

<!-- 代码部分begin -->
<div class="toolbar">
   <a href="javascript:;" class="toolbar-item toolbar-item-weixin"><span class="toolbar-layer"></span></a>
   <a href="javascript:;" class="toolbar-item toolbar-item-feedback" id="J_light"></a>
   <a href="javascript:;" class="toolbar-item toolbar-item-app"><span class="toolbar-layer"></span></a>
   <a href="javascript:scroll(0,0)" id="top" class="toolbar-item toolbar-item-top"></a>
</div>
<!-- 代码部分end -->

<script>
    document.getElementById('J_light').onclick = function(){
        window.open('${path}/index_web/kefu.do', '贝翔商城-在线客服', 'toolbar=no, status=no,scrollbars=0,resizable=no,menubar＝0,location=0,width=716,height=516');
    };
</script>


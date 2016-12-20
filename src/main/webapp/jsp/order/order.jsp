<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

	<head>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta charset="utf-8">
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0"> -->
		<title>订单管理</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/orstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
		<script type="text/javascript" src="${path }/js/jsrender.js"></script>
		<script src="${path }/js/jquery.js" type="text/javascript"></script>
	</head>

	<body>
		<!--头 -->
		<header>
			<article>
				<div class="mt-logo">
					<!--顶部导航条 -->
					<jsp:include page="/common/header.jsp"></jsp:include>
					<!--悬浮搜索框-->
					<jsp:include page="/common/searchbar.jsp"></jsp:include>
				</div>
			</article>
		</header>
		<jsp:include page="/common/shopNav.jsp"></jsp:include>
		<div class="center">
			<div class="col-main">
				<div class="main-wrap">

					<div class="user-order">

						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">订单管理</strong></div>
						</div>
						<hr/>

						<div class="am-tabs am-tabs-d2 am-margin" data-am-tabs>
							<div class="am-tabs-bd">
								<div class="am-tab-panel am-fade am-in am-active" id="tab1">
									<div class="order-top">
										<div class="th th-item">
											<td class="td-inner">商品</td>
										</div>
										<div class="th th-price">
											<td class="td-inner">单价</td>
										</div>
										<div class="th th-number">
											<td class="td-inner">数量</td>
										</div>
										<div class="th th-operation">
											<td class="td-inner">商品操作</td>
										</div>
										<div class="th th-amount">
											<td class="td-inner">合计</td>
										</div>
										<div class="th th-status">
											<td class="td-inner">交易状态</td>
										</div>
										<div class="th th-change">
											<td class="td-inner">交易操作</td>
										</div>
									</div>

									<div class="order-main">
										<div class="order-list">

										</div>

									</div>

								</div>

							</div>

						</div>
					</div>
				</div>
				<!--底部-->
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>
			<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
		</div>

	</body>
	<script id="order" type="text/x-jsrender">
		<div class="order-status5">
			<div class="order-title">
				<div class="dd-num">订单编号：
					<a href="javascript:;">{{:id}}</a>
				</div>
				<span>成交时间：{{:createTime}}</span>
			</div>
			<div class="order-content">
				<div class="order-left orderdetail_{{:id}}">

					{{for orderdetail}}
					<ul class="item-list">
						<li class="td td-item">
							<div class="item-pic">
								<a href="${path }/goods_web/goods/{{:id}}/search.do" class="J_MakePoint">
									<img src="${baseimg }{{> goodsimg}}" class="itempic J_ItemImg">
								</a>
							</div>
							<div class="item-info">
								<div class="item-basic-info">
									<a href="${path }/goods_web/goods/{{:id}}/search.do">
										<p>{{> goodsname}}</p>
									</a>
								</div>
							</div>
						</li>
						<li class="td td-price">
							<div class="item-price">
								{{toFloat:price}}
							</div>
						</li>
						<li class="td td-number">
							<div class="item-number">
								<span>×</span>{{> count}}
							</div>
						</li>
						<li class="td td-operation">
							<div class="item-operation">

							</div>
						</li>
					</ul>

					{{/for}}
				</div>
				<div class="order-right">
					<li class="td td-amount">
						<div class="item-amount">
							合计：
							{{if total == 0 }}
							0.00
							{{else }}
							{{toFloat:total}}
							{{/if}}
						</div>
					</li>
					<div class="move-right">
						<li class="td td-status">
							<div class="item-status">
								<p class="Mystatus">
									{{if orderstatus == 1}} 待付款 {{else orderstatus == 2}} 买家已付款 {{else orderstatus == 3}} 卖家已发货 {{else orderstatus == 0}} 订单已取消 {{else orderstatus == 4}} 订单已完成 {{else}} {{/if}}
								</p>
								<p class="order-info">
									<a href="orderinfo.html">订单详情</a>
								</p>
								{{if orderstatus == 3}}
								<p class="order-info">
									<a href="logistics.html">查看物流</a>
								</p>
								{{/if}}
							</div>
						</li>
						<li class="td td-change">
							<div class="am-btn am-btn-danger anniu">
								{{if orderstatus == 0}} 删除订单 {{else orderstatus == 3}} 确认收货 {{else orderstatus == 1}} 立即付款 {{else orderstatus == 4}} 评价 {{else}} {{/if}}
							</div>
						</li>
					</div>
				</div>
			</div>
		</div>
	</script>
	<script>
		var currentPage=0;
		var pageLength=1;
 		$.views.converters({
		    "toFloat":function(name){
		        if(name){
		            return parseFloat(name).toFixed(2);
		        }
		    }
		})
		
		function setPersonMenu(this_){
								$(this_).addClass("active");
								var lilist=$(".person").find("li");
								$.each(lilist,function(e){
									if(e != this_ ){
										$(e).removeClass("active");
									}
								});
							}
		
		function constructOrder(data) {
			var tmpl = $.templates("#order"); // Get compiled template       // Define data
			var html = tmpl.render(data); // Render template using data - as HTML string
			$(".order-list").append(html); // Insert HTML string into DOM
		}
		
		function getorder(now_, pagesize_) {
			$.post(
				_basePath + "/order_web/order/search.do", {
					nowpage: now_,
					pagesize: pagesize_
				},
				function(data) {
					if(data.result == 0) {
						constructOrder(data.data);
						currentPage=data.page.currentPage;
					}
				}, "json"
			);
		}

		$(document).ready(function() {
			
			setPersonMenu($("li[name='objorder']"));
			getorder(currentPage, pageLength);
			$(window).scroll(function(){
			    if($(window).scrollTop() == $(document).height() - $(window).height()){
			       	getorder(currentPage, pageLength);
			    }
			});
		});
	</script>

</html>
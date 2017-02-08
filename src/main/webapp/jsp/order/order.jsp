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
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
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
											<input type="hidden" id="orde_type" value="${ordetype}">
											<td class="td-inner">商品</td>
										</div>
										<div class="th th-price">
											<td class="td-inner">单价</td>
										</div>
										<div class="th th-number">
											<td class="td-inner">数量</td>
										</div>
										
										<div class="th th-amount" >
											<td class="td-inner" style="padding-left:0px">合计</td>
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
					<a href="javascript:;">{{:name}}</a>
				</div>
				<span>成交时间：{{:createTime}}</span>
				{{if orderstatus == 3}}<span style="margin-left:18px">物流单号： {{> postcode}}</span>{{/if}}
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
						
					</ul>

					{{/for}}
				</div>
				<div class="order-right">
					<li class="td td-amount">
						<div class="item-amount">
							<span >合计： {{if total == 0 }} 0.00 {{else }} {{toFloat:total}} {{/if}}</span>
						</div>
					</li>
					<div class="move-right">
						<li class="td td-status" style="width:80px">
							<div class="item-status" style="width:80px">
								<p class="Mystatus">
									{{if orderstatus == 1}} 待付款 <a>删除</a>{{else orderstatus == 2}} 买家已付款 {{else orderstatus == 3}} 卖家已发货 {{else orderstatus == 0}} 订单已取消 {{else orderstatus == 4}} 订单已完成 {{else}} {{/if}}
								</p>
								
								{{if orderstatus == 3}}
								<p class="order-info">
									<!--<a href="logistics.html">查看物流</a>
                                   	   快递物流号：{{> postcoder}}-->
								</p>
								{{/if}}
							</div>
						</li>
						<li class="td td-change" style="width:60px">
							
							{{if orderstatus == 2}}
							<div class="am-btn am-btn-danger anniu" style="margin-left:25px">
								 等待发货  </div> 
							</div>
							{{else orderstatus == 1}} 
							<div oncick="">
								<div class="am-btn am-btn-danger anniu" style="margin-left:25px">
									<a href="${path }/shopcar_web/search.do" style="color:#fff"> 立即付款</a>  </div> 
								</div>
							</div>
							{{else orderstatus == 3}}<div onclick="receipt()" style="width:100%">
							<div onclick="receipt({{> id}})">
								
								<div class="am-btn am-btn-danger anniu" style="margin-left:25px">
									确认收货  </div> 
								</div>
							</div>

							{{else orderstatus == 4}} 
<!--
							<div class="am-btn am-btn-danger anniu" style="margin-left:25px">
								 
							</div> -->
							 {{else}} {{/if}}
						</li>
					</div>
				</div>
			</div>
		</div>
	</script>
	<script>
		var currentPage = 0;
		var pageLength = 1;
		$.views.converters({
			"toFloat": function(name) {
				if(name) {
					return parseFloat(name).toFixed(2);
				}
			}
		})

		function setPersonMenu(this_) {
			$(this_).addClass("active");
			var lilist = $(".person").find("li");
			$.each(lilist, function(e) {
				if(e != this_) {
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
			var ordetype=$("#orde_type").val();
			$.post(
				_basePath + "/order_web/order/search.do", {
					nowpage: now_,
					pagesize: pagesize_,
					type:ordetype
				},
				function(data) {
					if(data.result == 0) {
						constructOrder(data.data);
						currentPage = data.page.currentPage;
					}
				}, "json"
			);
		}
		
		function receipt(id){
			$.post(
									_basePath+"/order_web/order/receipt.do",
									{ 
										"orderid":id	
									},function(data){
										if(data.msg>0){
											layer.msg('操作成功!',{icon:6,time:1500},function(){
                            				window.location.reload()});	
										}else{
											layer.msg('操作失败!',{icon:5,time:1500});
										}
									},"json"
							);
				
		}
		
		$(document).ready(function() {
			loadCategory();
			setPersonMenu($("li[name='objorder']"));
			getorder(currentPage, pageLength);
			$(window).scroll(function() {
				if($(window).scrollTop() == $(document).height() - $(window).height()) {
					getorder(currentPage, pageLength);
				}
			});
		});
	</script>

</html>
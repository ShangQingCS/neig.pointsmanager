<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0 ,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> -->
		<title>结算页面</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="${path }/basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="${path }/css/cartstyle.css" rel="stylesheet" type="text/css" />
		<link href="${path }/css/jsstyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${path }/js/address.js"></script>
		<link type="text/css" href="${path }/css/headnav.css" rel="stylesheet" />
		
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/addstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		<script>
			$(document).ready(function() {
				$(".new-option-r").click(function() {
								$(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
								$(this).html("<i class='am-icon-check-circle'></i>默认地址").parent('.user-addresslist').siblings().find(".new-option-r").html("<i class='am-icon-check-circle'></i>设为默认")
								alert(id);
								$.post(
									_basePath+"address/manager.do",
									{ 
										addressid:id,
										isuse:"0",
										type:"2",
										uerid:${user.id}
									},function(data){
										if(data.result == 0){
											$("").html("");
										}else{
											
										}
									},"json"
								);
							});
			
			})
		</script>
	</head>

	<body>

		<!--顶部导航条 -->
			<!--顶部导航条 -->
		<jsp:include page="/common/header.jsp"></jsp:include>
		<!--悬浮搜索框-->
		<jsp:include page="/common/searchbar.jsp"></jsp:include>
		<!-- catagroy -->
		<jsp:include page="/common/shopNav.jsp"></jsp:include>
			
			<div class="concent">
				<!--地址 -->
				<div class="paycont">
					<div class="address">
						<h3>确认收货地址 </h3>
						<div class="control">
							<a href="${path }/user_web/user/address.do" target="_self"><div class="tc-btn createAddr theme-login am-btn am-btn-danger">添加新地址</div></a>
						</div>
						<div class="clear"></div>
						
						<ul>
							<div class="per-border"></div>
							<c:forEach items="${ads }" var="address">
							<li class="user-addresslist <c:if test="${address.isuse == 0 }">defaultAddr</c:if>">
							    <input name="addressid" type="hidden" value="${address.id }">
								<span class="new-option-r">
								<i class="am-icon-check-circle"></i>
								<c:if test="${address.isuse == 0 }">默认地址</c:if>
								<c:if test="${address.isuse == 1 }">设为默认</c:if>
								</span>
								<p class="new-tit new-p-re">
									<span class="new-txt" name="name">${address.name }</span>
									<span class="new-txt-rd2" name="phonenumb">${address.phonenumb }</span>
									
								</p>
								<div class="new-mu_l2a new-p-re">
									<p class="new-mu_l2cw">
										<span class="title">地址：</span>
										<span class="street" name="address">${address.address }</span></p>
								</div>
								<div class="new-addr-btn">
									<a href="${path }/user_web/user/address.do" class="edit" ><i class="am-icon-edit"></i>编辑</a>
								<!--  	<span class="new-addr-bar">|</span>
									<a href="javascript:void(0);" name="${address.id }" class="delete"><i class="am-icon-trash"></i>删除</a> -->
								</div>
							</li>
							</c:forEach>
						</ul>

						<div class="clear"></div>
					</div>
					<!--物流 -->
					<!--<div class="logistics">
						<h3>选择物流方式</h3>
						<ul class="op_express_delivery_hot">
							<li data-value="yuantong" class="OP_LOG_BTN  "><i class="c-gap-right" style="background-position:0px -468px"></i>圆通<span></span></li>
							<li data-value="shentong" class="OP_LOG_BTN  "><i class="c-gap-right" style="background-position:0px -1008px"></i>申通<span></span></li>
							<li data-value="yunda" class="OP_LOG_BTN  "><i class="c-gap-right" style="background-position:0px -576px"></i>韵达<span></span></li>
							<li data-value="zhongtong" class="OP_LOG_BTN op_express_delivery_hot_last "><i class="c-gap-right" style="background-position:0px -324px"></i>中通<span></span></li>
							<li data-value="shunfeng" class="OP_LOG_BTN  op_express_delivery_hot_bottom"><i class="c-gap-right" style="background-position:0px -180px"></i>顺丰<span></span></li>
						</ul>
					</div>
					<div class="clear"></div>-->

					

					<!--订单 -->
					<div class="concent">
						<div id="payTable">
							<h3>确认订单信息</h3>
							<div class="cart-table-th">
								<div class="wp">

									<div class="th th-item">
										<div class="td-inner">商品信息</div>
									</div>
									<div class="th th-price">
										<div class="td-inner">单价</div>
									</div>
									<div class="th th-amount">
										<div class="td-inner">数量</div>
									</div>
									<div class="th th-sum">
										<div class="td-inner">金额</div>
									</div>
									<div class="th th-oplist">
										<div class="td-inner">配送方式</div>
									</div>

								</div>
							</div>
							<div class="clear"></div>

                            <form id="gotoPay" name="gotoPay" action="${path }/shopcar_web/gotopay.do" method="post">
							<tr class="item-list">
								<div class="bundle  bundle-last">

									<div class="bundle-main">
									
									<c:forEach items="${goodList}" var="goodlist">
									    <input type="hidden" name="goodsid" values="${goodlist.goods.id }" />
									
										<ul class="item-content clearfix">
											<div class="pay-phone">
												<li class="td td-item">
													<div class="item-pic">
														<a href="#" class="J_MakePoint">
															<img src="${baseimg }${goodlist.goods.goodimg }" class="itempic J_ItemImg" width="80px" height="80px"></a>
													</div>
													<div class="item-info">
														<div class="item-basic-info">
															<a href="#" class="item-title J_MakePoint" data-point="tbcart.8.11">${goodlist.goods.gname }</a>
														</div>
													</div>
												</li>
												<li class="td td-info">
													<!-- <div class="item-props">
														<span class="sku-line">颜色：12#川南玛瑙</span>
														<span class="sku-line">包装：裸装</span>
													</div> -->
													&nbsp;
												</li>
												<li class="td td-price">
													<div class="item-price price-promo-promo">
														<div class="price-content">
															<em class="J_Price price-now">${goodlist.goods.price }</em>
														</div>
													</div>
												</li>
											</div>
											<li class="td td-amount">
												<div class="amount-wrapper ">
													<div class="item-amount ">
														<span class="phone-title">购买数量</span>
														<div class="sl">
															<input class="min am-btn" name="" type="button" value="-" />
															<input class="text_box" name="paynumb" type="text" value="${goodlist.shuliang }" style="width:30px;" />
															<input class="add am-btn" name="" type="button" value="+" />
														</div>
													</div>
												</div>
											</li>
											<li class="td td-sum">
												<div class="td-inner">
													<em tabindex="0" class="J_ItemSum number">${goodlist.shuliang* goodlist.goods.price}</em>
												</div>
											</li>
											<li class="td td-oplist">
												<div class="td-inner">
													<span class="phone-title">配送方式</span>
													<div class="pay-logis">
														包邮
													</div>
												</div>
											</li>

										</ul>
										<div class="clear"></div>
										</c:forEach>
										
										
									</div>
							</tr>
							<div class="clear"></div>
							</div>

							
							</div>
							<div class="clear"></div>
							<div class="pay-total">
						<!--留言-->
							<div class="order-extra">
								<div class="order-user-info">
									<div id="holyshit257" class="memo">
										<label>买家留言：</label>
										<input type="text" name="remark" title="选填,对本次交易的说明（建议填写已经和卖家达成一致的说明）" placeholder="选填,建议填写和卖家达成一致的说明" class="memo-input J_MakePoint c2c-text-default memo-close">
										<div class="msg hidden J-msg">
											<p class="error">最多输入500个字符</p>
										</div>
									</div>
								</div>

							</div>
							<!--优惠券 -->
							<div class="buy-agio">
								<li class="td td-coupon">

									<span class="coupon-title">优惠券</span>
									<select data-am-selected>
									
									    <option value="0">
											
											<div class="c-limit">
												【选择使用优惠卷】
											</div>
										</option>
									
										<option value="a">
											<div class="c-price">
												<strong>￥8</strong>
											</div>
											<div class="c-limit">
												【消费满95元可用】
											</div>
										</option>
										<option value="b" selected>
											<div class="c-price">
												<strong>￥3</strong>
											</div>
											<div class="c-limit">
												【无使用门槛】
											</div>
										</option>
									</select>
								</li>
							</div>
							<div class="clear"></div>
							</div>
							<!--含运费小计 -->
							<div class="buy-point-discharge ">
								<p class="price g_price ">
									<span style="padding-right: 50px;">&nbsp;</span>
									合计（含运费） <span>¥</span><em class="pay-sum">244.00</em>
								</p>
							</div>
							
							<!--支付方式-->
					<div class="logistics">
						<h3>选择支付方式</h3>
						<ul class="pay-list">
							<li class="pay qq selected" style="margin-top:10px"> <img src="${path }/images/profit.png" /> 钱包(10000.00元)<span></span></li>
							<li class="pay taobao" style="margin-top:10px"> <img src="${path }/images/profit.png"/> 分红(500.00元)<span></span></li>
						</ul>
					</div>
					<div class="clear"></div>
					</form>
							<!--信息 -->
							<div class="order-go clearfix">
								<div class="pay-confirm clearfix">
									
										<div tabindex="0" id="holyshit267" class="realPay"><em class="t">实付款：</em>
											<span class="price g_price ">
                                    <span>¥</span> <em class="style-large-bold-red " id="J_ActualFee">244.00</em>
											</span>
										</div>

									<div id="holyshit269" class="submitOrder">
										<div class="go-btn-wrap">
											<a id="J_Go" href="${path }/shopcar_web/gotopay.do" class="btn-go" tabindex="0" title="点击此按钮，提交订单">确认提交</a>
										</div>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>

						<div class="clear"></div>
					</div>
				</div>
				
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>
			<!--  
			<div class="theme-popover-mask"></div>
			<div class="theme-popover">

				
				<div class="am-cf am-padding">
					<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">新增地址</strong></div>
				</div>
				<hr/>

				<div class="am-form-group">
							<label for="user-name" class="am-form-label">收货人</label>
							<div class="am-form-content">
								<input type="text" id="user-name" placeholder="收货人">
							</div>
						</div>
								

						<div class="am-form-group">
							<label for="user-phone" class="am-form-label">手机号码</label>
							<div class="am-form-content">
								<input id="user-phone" placeholder="手机号必填" type="tel" name="phonenumb">
							</div>
						</div>


						<div class="am-form-group">
							<label for="user-phone" class="am-form-label">所在地</label>
							<div class="am-form-content address">
								<select data-am-selected>
									<option value="a">浙江省</option>
									<option value="b">湖北省</option>
								</select>
								<select data-am-selected>
									<option value="a">温州市</option>
									<option value="b">武汉市</option>
								</select>
								<select data-am-selected>
									<option value="a">瑞安区</option>
									<option value="b">洪山区</option>
								</select>
							</div>
						</div>
						



						<div class="am-form-group">
							<label for="user-intro" class="am-form-label">详细地址</label>
							<div class="am-form-content">
								<textarea class="" rows="3" id="user-intro" placeholder="输入详细地址" name="address"></textarea>
								<small>100字以内写出你的详细地址...</small>
							</div>
						</div>

					

						<div class="am-form-group theme-poptit">
							<div class="am-u-sm-9 am-u-sm-push-3">
								<input type="hidden" id="address_id" name="addressid">
								<input type="hidden" id="type" name="type">
								<a class="am-btn am-btn-danger" name="submit">保存</a>
								<a href="javascript: void(0)" class="am-close am-btn am-btn-danger cancel" >取消</a>
							</div>
						</div>
					</form>
				</div>

			</div>
-->
			<div class="clear"></div>
	</body>

</html>
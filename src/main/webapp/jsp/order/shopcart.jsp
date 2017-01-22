<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> -->
		<title>贝翔商城--我的购物车</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="${path }/basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link href="${path }/css/cartstyle.css" rel="stylesheet" type="text/css" />
		<link href="${path }/css/optstyle.css" rel="stylesheet" type="text/css" />
		<link type="text/css" href="${path }/css/headnav.css" rel="stylesheet" />
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/jsstyle.css" rel="stylesheet" type="text/css" />
		<link href="${path }/css/addstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<script type="text/javascript" src="${path }/js/address.js"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
	</head>

	<body>
		<!--顶部导航条 -->
		<jsp:include page="/common/header.jsp"></jsp:include>
		<!--悬浮搜索框-->
		<jsp:include page="/common/searchbar.jsp"></jsp:include>
		<!-- catagroy -->
		<jsp:include page="/common/shopNav.jsp"></jsp:include>

			<!--购物车 -->
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
                   </div>
<!--订单 -->
					<div id="payTable">
							<h3>确认订单信息</h3>
							
							<div class="cart-table-th">
						<div class="wp">
							<div class="th th-chk">
								<div id="J_SelectAll1" class="select-all J_SelectAll">

								</div>
							</div>
							<div class="th th-item" style="width:350px">
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
							<div class="th th-op">
								<div class="td-inner">操作</div>
							</div>
						</div>
					</div>
					<div class="clear"></div>
                    <form class="theme-signin" name="buyform" id="buyform" action="${path }/shopcar_web/paycar.do" method="post">
					<tr class="item-list">
						<div class="bundle  bundle-last ">
							<div class="bundle-hd">
							</div>
							<div class="clear"></div>
							<div class="bundle-main cartlist">
								<c:forEach items="${cartlist}" var="cart">
								<ul class="item-content clearfix cartrow">
									<li class="td td-chk">
										<div class="cart-checkbox ">
											<input class="check itemcheckbox" id="J_CheckBox_170037950254" name="cartid" value="${cart.id}" type="checkbox">
											<label for="J_CheckBox_170037950254"></label>
										</div>
									</li>
									<li class="td td-item" style="width:400px">
										<div class="item-pic">
											<a href="${path }/goods_web/goods/${cart.goodsid}/search.do" target="_blank" data-title="${cart.goodsname }" class="J_MakePoint" data-point="tbcart.8.12">
												<img src="${baseimg }${cart.img}" class="itempic J_ItemImg" width="80px" height="80px"></a>
										</div>
										<div class="item-info">
											<div class="item-basic-info">
												<a href="${path }/goods_web/goods/${cart.goodsid}/search.do" name="${cart.goodsid}" target="_blank" title="${cart.goodsname }" class="item-title J_MakePoint goodsid" data-point="tbcart.8.11">${cart.goodsname }</a>
											</div>
										</div>
									</li>
									<li class="td td-info">
										&nbsp;
									</li>
									<li class="td td-price">
										<div class="item-price price-promo-promo">
											<div class="price-content">
												<div class="price-line">
													<em class="J_Price price-now" tabindex="0" name="${cart.price }"><fmt:formatNumber value="${cart.price }" pattern="#,#00.00#"/></em>
												</div>
											</div>
										</div>
									</li>
									<li class="td td-amount">
										<div class="amount-wrapper " name="count">
											<div class="item-amount ">
												<div class="sl">
													<input class="am-btn" name="minus" type="button" value="-" />
													<input class="text_box count" name="" type="text" value="${cart.count }" style="width:30px;" />
													<input class="am-btn" name="add" type="button" value="+" />
												</div>
											</div>
										</div>
									</li>
									<li class="td td-sum">
										<div class="td-inner">
											<em tabindex="0" class="J_ItemSum number totalprice" ><fmt:formatNumber value="${cart.count * cart.price }" pattern="#,#00.00#"/></em>
										</div>
									</li>
									<li class="td td-op">
										<div class="td-inner">
											<a href="javascript:;" data-point-url="#" class="delete" name="${cart.id }"> 删除</a>
										</div>
									</li>
								</ul>
								</c:forEach>
								
								
							</div>
						</div>
					</tr>
					<div class="clear"></div>
                 </form>
                 
                 
                 	
                 
                 
				<div class="float-bar-wrapper">
					<div id="J_SelectAll2" class="select-all J_SelectAll">
						<div class="cart-checkbox">
							<input class="check-all check" id="J_SelectAllCbx2" name="select-all" value="true" type="checkbox">
							<label for="J_SelectAllCbx2"></label>
						</div>
						<span>全选</span>
					</div>
					<div class="operations">
						<a href="#" hidefocus="true" class="deleteAll">删除</a>
					</div>
					<div class="float-bar-right">
						<div class="amount-sum">
							<span class="txt"></span>
							<em id="J_SelectedItemsCount"></em><span class="txt"></span>
							<div class="arrow-box">
								<span class="selected-items-arrow"></span>
								<span class="arrow"></span>
							</div>
						</div>
						<div class="price-sum">
							<span class="txt">合计:</span>
							<strong class="price"><em id="J_Total">0.00</em></strong>
						</div><!-- 
						<div class="btn-area">
							<a href="#" onclick="submitBuy(); return false;" id="J_Go" class="submit-btn submit-btn-disabled" aria-label="请注意如果没有选择宝贝，将无法结算">
								<span>结&nbsp;算</span>
							</a>
						</div> -->
					</div>
				</div>
				
				<!--留言-->
				<h3>订单其它信息</h3>
							<div class="order-extra">
								<div class="order-user-info">
									<div id="holyshit257" class="memo">
										<label>买家留言：</label>
										<input type="text" name="remark" title="选填,对本次交易的说明（建议填写已经和卖家达成一致的说明）" placeholder="选填,建议填写和卖家达成一致的说明" class="memo-input J_MakePoint c2c-text-default memo-close" style="font-size:15px;height:20px">
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
										<c:forEach items="${myCouponList}" var="mycoupon">
										<option value="${mycoupon.couponBlance}">
											<div class="c-price">
												<strong>￥${mycoupon.couponBlance}</strong>
											</div>
											<div class="c-limit">
												【消费满${mycoupon.couponXfBalance}元可用】
											</div>
										</option>
										</c:forEach>
									</select>
								</li>
							</div>
							<div class="clear"></div>
						<!--支付方式-->
					<div class="logistics">
						<h3>选择支付方式</h3>
						<ul class="pay-list">
							<li class="pay qq selected" style="margin-top:10px"> <img src="${path }/images/profit.png" /> 钱包(${user.userKyBalance }元)<span></span></li>
							<!--  <li class="pay taobao" style="margin-top:10px"> <img src="${path }/images/profit.png"/> 分红(500.00元)<span></span></li>-->
						</ul>
					</div>
					<div class="clear"></div>

							<!--信息 -->
							<div class="order-go clearfix">
								<div class="pay-confirm clearfix">
									
										<div tabindex="0" id="holyshit267" class="realPay"><em class="t">实付款：</em>
											<span class="price g_price ">
                                    <span>¥</span> <em class="style-large-bold-red " id="J_ActualFee">0.00</em>
											</span>
										</div>

									<div id="holyshit269" class="submitOrder">
										<div class="go-btn-wrap">
											<a id="J_Go" href="javascript:void(0);" onclick="submitBuy();" class="btn-go" tabindex="0" title="点击此按钮，提交订单">确认提交</a>
										</div>
									</div>
									<div class="clear"></div>
								</div>
							</div>
						</div>

						<div class="clear"></div>
					</div>
				
				
				<jsp:include page="/common/footer.jsp"></jsp:include>
		
<script >
	
	
	    function submitBuy(){
	    	
	    	$('input[name="cartid"]:checked').each(function(){
                $('#buyform').submit();
                return false;
            });
            
	    	layer.msg("请选择支付的商品",{icon:5,time:1500},function(){

		    });
	    	
	    }
	
		var totalprice;
		function toThousands(num) {
		     var num = (num || 0).toString(), result = '';
		     while (num.length > 3) {
		         result = ',' + num.slice(-3) + result;
		         num = num.slice(0, num.length - 3);
		     }
		     if (num) { result = num + result; }
		     return result;
		}
		function totalall(){
			var list=$(".itemcheckbox:checked");
			totalprice=0.00;
				for(var i=0;i<list.length;i++){
					var count=$(list[i]).parents("ul.item-content").find("input.count").val();
					var price=$(list[i]).parents("ul.item-content").find("em.price-now").attr("name");
					totalprice=parseFloat(totalprice)+parseFloat(count)*parseFloat(price);
				}
				totalprice=parseFloat(totalprice).toFixed(2);
				var result=totalprice.split(".");
				$("#J_Total").html(toThousands(result[0])+"."+result[1]);
				$("#J_ActualFee").html(toThousands(result[0])+"."+result[1]);
				
				
		}
		function total(this_){
			var ul=$(this_).parents("ul.cartrow");
			var count=$(ul).find("input.count").val();
			var price=$(ul).find("em.price-now").attr("name");
			var total=parseFloat(count)*parseFloat(price);
			total=parseFloat(total).toFixed(2);
			var result=total.split(".");
			$(ul).find("em.totalprice").html(toThousands(result[0])+"."+result[1]);
		}
		
		function updatenumb(goodsid_,count_,id_){
			$.post(
					_basePath+"shopcar/manager.do",
					{ 
						id:id_,
						count:count_,
						goodsid:goodsid_,
						userid:${user.id},
						type:"2",
					},function(data){
						if(data.result != 0){
							//请重新修改数量
						}
					},"json"
				);
		}
		
		$(document).ready(function(){
			
			loadCategory();
		
			$("div").on("click","input.itemcheckbox",function(){
				totalall();
			});
			
			$("div").on("click","input[name='select-all']",function(){
				if($(this).attr("checked")=="checked"){
					$(".itemcheckbox").attr("checked","checked");
				}else{
					$(".itemcheckbox").removeAttr("checked");
				}
				totalall();
			});
			var spCodesTemp;
			$(".deleteAll").click(function(){
				$(".itemcheckbox:checked").each(function(i){
				   if(0==i){
			        spCodesTemp = $(this).val();
			       }else{
			        spCodesTemp += (","+$(this).val());
			       }
				});
				$.post(
					_basePath+"shopcar/manager.do",
					{ 
						goodslist:spCodesTemp,
						type:"1",
					},function(data){
						if(data.result == 0){
							$("div.cartlist").html("");
						}
					},"json"
				);
			});
			$("a.delete").click(function(){
				var _this=$(this);
				$.post(
					_basePath+"shopcar/manager.do",
					{ 
						id:$(_this).attr("name"),
						type:"1",
					},function(data){
					
						
						if(data.result == 0){
							
							layer.msg(data.message,{icon:6,time:1500},function(){
								$(_this).parents("ul.cartrow").remove();
						    });	
							
						}
					},"json"
				);
			});
			$("input[name='minus']").click(function(){
				var countobj=$(this).parents("div[name='count']").find("input.count");
				var count=$(countobj).val();
				var updatecount=1;
				if((parseInt(count)-1) <= 1){
					updatecount=1;
					$(countobj).val(1);					
				}else{
					$(countobj).val(parseInt(count)-1);
					updatecount=parseInt(count)-1;
				}
				total(this);
				totalall();
				updatenumb($(this).parents("ul.cartrow").find("a.goodsid").attr("name"),updatecount,$(this).parents("ul.cartrow").find("a.delete").attr("name"));
			});
			$("input[name='add']").click(function(){
				var countobj=$(this).parents("div[name='count']").find("input.count");
				var count=$(countobj).val();
				$(countobj).val(parseInt(count)+1);
				total(this);
				totalall();
				updatenumb($(this).parents("ul.cartrow").find("a.goodsid").attr("name"),parseInt(count)+1,$(this).parents("ul.cartrow").find("a.delete").attr("name"));
			});
			$("input.count").keyup(function(){
				var value=$(this).val();
				if((new RegExp("^[0-9]*[1-9][0-9]*$")).test(value)){
					$(this).val(value);
				}else{
					value=1;
					$(this).val(1);
				}
				total(this);
				totalall();
				updatenumb($(this).parents("ul.cartrow").find("a.goodsid").attr("name"),value,$(this).parents("ul.cartrow").find("a.delete").attr("name"));
			});
		});
		
	</script>
	</body>
	

</html>
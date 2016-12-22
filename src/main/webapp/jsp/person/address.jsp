<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

	<head>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta charset="utf-8">
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0"> -->
		<title>地址管理</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/addstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
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

					<div class="user-address">
						<!--标题 -->
						<div class="am-cf am-padding">
							<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">地址管理</strong></div>
						</div>
						<hr/>
						<ul class="am-avg-sm-1 am-avg-md-3 am-thumbnails">
							<c:forEach items="${ads }" var="address">
							<li class="user-addresslist <c:if test="${address.isuse == 0 }">defaultAddr</c:if>">
								<span class="new-option-r"><i class="am-icon-check-circle"></i><c:if test="${address.isuse == 0 }">默认地址</c:if><c:if test="${address.isuse == 1 }">设为默认</c:if></span>
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
									<a href="javascript:void(0);" class="edit" ><i class="am-icon-edit"></i>编辑</a>
									<span class="new-addr-bar">|</span>
									<a href="javascript:void(0);" name="${address.id }" class="delete"><i class="am-icon-trash"></i>删除</a>
								</div>
							</li>
							</c:forEach>
						</ul>
						<div class="clear"></div>
						<!--例子-->
						<div class="am-modal am-modal-no-btn" id="doc-modal-1">

							<div class="add-dress">

								<!--标题 -->
								<div class="am-cf am-padding">
									<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg address_title" >新增地址</strong></div>
								</div>
								<hr/>

								<div class="am-u-md-12 am-u-lg-8" style="margin-top: 20px;">
									<form class="am-form am-form-horizontal" name="editform" action="" method="post" >

										<div class="am-form-group">
											<label for="user-name" class="am-form-label">收货人</label>
											<div class="am-form-content">
												<input type="text" id="user-name" name="name" placeholder="收货人">
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-phone" class="am-form-label">手机号码</label>
											<div class="am-form-content">
												<input id="user-phone" placeholder="手机号必填" name="phonenumb" type="tel">
											</div>
										</div>

										<div class="am-form-group">
											<label for="user-intro" class="am-form-label">详细地址</label>
											<div class="am-form-content">
												<textarea class="" rows="3" id="user-intro" name="address" placeholder="输入详细地址"></textarea>
												<small>100字以内写出你的详细地址...</small>
											</div>
										</div>

										<div class="am-form-group">
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

						</div>

					</div>

					<script type="text/javascript">
						$(document).ready(function() {
							loadCategory();	
							function setPersonMenu(this_){
								$(this_).addClass("active");
								var lilist=$(".person").find("li");
								$.each(lilist,function(e){
									if(e != this_ ){
										$(e).removeClass("active");
									}
								});
							}
						
							setPersonMenu($("li[name='objaddress']"));
												
							$(".new-option-r").click(function() {
								$(this).parent('.user-addresslist').addClass("defaultAddr").siblings().removeClass("defaultAddr");
								$(this).html("<i class='am-icon-check-circle'></i>默认地址").parent('.user-addresslist').siblings().find(".new-option-r").html("<i class='am-icon-check-circle'></i>设为默认")
								$.post(
									_basePath+"address/manager.do",
									{ 
										addressid:id,
										isuse:"0",
										type:"2",
									},function(data){
										if(data.result == 0){
											$("").html("");
										}else{
											
										}
									},"json"
								);
							});
							
							var $ww = $(window).width();
							if($ww>640) {
								$("#doc-modal-1").removeClass("am-modal am-modal-no-btn")
							}
							
							$("div").on("click","a.delete",function(){
								var id=$(this).attr("name");
								$.post(
									_basePath+"address/manager.do",
									{ 
										addressid:id,
										type:"1",
									},function(data){
										if(data.result == 0){
											$("").html("");
										}else{
											$(this).parents(".user-addresslist").remove();
										}
									},"json"
								);
							});
							
							$("div").on("click","a.edit",function(){
								var address=$(this).parents(".user-addresslist");
								var name=$(address).find("span[name='name']").html();
								var phonenumb=$(address).find("span[name='phonenumb']").html();
								var address=$(address).find("span[name='address']").html();
								var addressid=$(address).find("a.delete").attr("name");
								
								$(".add-dress").find("#user-name").val(name);
								$(".add-dress").find("#user-phone").val(phonenumb);
								$(".add-dress").find("#user-intro").val(address);
								$(".add-dress").find("#address_id").val(addressid);
								$(".add-dress").find("#type").val("2");
								
								$("strong.address_title").html("地址修改");
							});
							
							$("div").on("click","a.cancel",function(){
								$(".add-dress").find("#user-name").val("");
								$(".add-dress").find("#user-phone").val("");
								$(".add-dress").find("#user-intro").val("");
								$(".add-dress").find("#address_id").val("");
								$(".add-dress").find("#type").val("0");
								
								$("strong.address_title").html("新建地址");
							});
							
							$("a[name='submit']").click(function(){
								$("form[name='editform']").attr("action","/user_web/user/edit/address.do");
								$("form[name='editform']").submit();
							});
							
						});
					</script>

					<div class="clear"></div>

				</div>
				<!--底部-->
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>

			<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
		</div>

	</body>

</html>
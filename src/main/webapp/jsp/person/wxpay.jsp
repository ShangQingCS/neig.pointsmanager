<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

	<head>
	<jsp:include page="/common/include.jsp"></jsp:include>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
		<title>微信扫码支付</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css">
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/personal.css" rel="stylesheet" type="text/css">
		<link href="${path }/css/stepstyle.css" rel="stylesheet" type="text/css">
		<script src="${path }/AmazeUI-2.4.2/assets/js/jquery.min.js" type="text/javascript"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
		<script type="text/javascript" src="${path }/js/jsrender0.9.83.js"></script>
		<script src="${path }/AmazeUI-2.4.2/assets/js/layer/2.1/layer.js"></script>
		<link type="text/css" href="${path }/css/headnavperson.css" rel="stylesheet" />
		<script>
			function submit(){
				var trade_amount=$('#trade_amount').val();
				var pay_account=$('#pay_account').val();
				var paypwd=$('#paypwd').val();
				var gradeTxBalance = $('#gradeTxBalance').val();
				var pay_open_bank = $('#pay_open_bank').val();
				var trade_type = $('#wrap input[name="trade_type"]:checked ').val();
				$.post(
							_basePath+"/user_web/user/tixianjson.do",
							{ 
								"trade_amount":trade_amount,
								"pay_account" :pay_account,
								"paypwd":paypwd,
								"gradeTxBalance":gradeTxBalance,
								"pay_open_bank":pay_open_bank,
								"trade_type":trade_type	
							},function(data){
								if(data!=null && data!=''){
									layer.msg(data.msg,{icon:5,time:1500},function(){
										 if(data.msg=='申请成功'){
											 loaction.href=_basePath+"/user_web/user/tixian.do"; //刷新当前页面
										 }
									});	
								}else{
									layer.msg('申请失败!',{icon:5,time:1500});
								}
							},"json"
						);
			
			
			}	
			
			
			
		
		</script>
		<style type="text/css">
		.birth-select {
    		width: 38%;
    		margin-right: 0;
    		position: relative;
    		height: 32px;
    		line-height: 32px;
    		float: left;
}
		}
		</style>

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

					<div class="am-cf am-padding">
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">申请提现</strong> / <small>Present application</small></div>
					</div>
					<hr/>
					<div class="authentication">
						<p class="tip">可用余额：<font color="red">${user.userKyBalance }元</font> 分红（可用提现）金额：<font color="red">${user.userFxBalance }元</font> 提现冻结金额：<font color="red">${user.userDjBalance }元</font></p>
						<p class="tip">实名认证状态： <c:if test="${user.identityStatus==1 }"><font color="red">申请中</font></c:if><c:if test="${user.identityStatus==2 }"><font color="red">已认证</font></c:if><c:if test="${user.identityStatus==0 }">未认证  </c:if> 当前用户等级：<font color="red">${userGrade.gradeName }</font></p>
						<p class="tip">当前用户等级：<font color="red">${userGrade.gradeName }</font> 单笔最高提现金额：<font color="red">${userGrade.gradeTxBalance }元</font></p>
						<div class="authenticationInfo">
							<p class="title">提现申请信息</p>

 <c:if test="${user.tixianStatus==1 }">
	<p class="tip">您已有一笔提现申请处理中，提现金额：<font color="red">${user.userDjBalance }元</font> 当前状态：<font color="red">处理中</font>，请等待商城管理员处理后再次申请。</p>
 </c:if>

<c:if test="${user.identityStatus!=2 }">

	<p class="tip">请先 <a href="${path }/user_web/user/idcard.do"><font color="red">去实名认证</font></a>，才可以进行提现操作。</p>
	
</c:if>

<c:if test="${user.payPwd==''}">

	<p class="tip">请先 <a href="${path }/user_web/user/paypwd.do"><font color="red">设置支付密码</font></a>，才可以进行提现操作。</p>
	
</c:if>

<c:if test="${user.tixianStatus==0 && user.identityStatus==2 && user.payPwd!=''}">

                           <form action="/user_web/user/sqtx.do" method="post">

							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">真实姓名</label>
								<div class="am-form-content">
									<input type="text" id="true-name" value="${user.trueName}" disabled="disabled">
								</div>
							</div>
							
							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">提现金额</label>
								<div class="am-form-content">
									<input type="text" id="trade_amount" value="${userGrade.gradeTxBalance }" placeholder="请输入提现金额" onkeyup="value=value.replace(/[^\d]/g,'')">
									<input type="hidden" id="gradeTxBalance" value="${userGrade.gradeTxBalance }">
								</div>
							</div>
							
							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">支付类型</label>
								<div class="am-form-content" id="wrap">
									<input name="trade_type" type="radio" checked="checked" value="1" style="width:40px;height: 15px">支付宝
									<input name="trade_type" type="radio" value="2" style="width:40px;height: 15px">微信
									<input name="trade_type" type="radio" value="3" style="width:40px;height: 15px">银联
								</div>
							</div>
							
							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">提现账号</label>
								<div class="am-form-content">
									<input type="text" id="pay_account" value="" placeholder="请输入提现的账号" onkeyup="value=value.replace(/[\W]/g,'') ">
								</div>
							</div>
							
							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">开户行</label>
								<div class="am-form-content">
									<input type="text" id="pay_open_bank" value="" placeholder="银联必填，其它类型可不填" onkeyup="value=value.replace(/[\W]/g,'') ">
								</div>
							</div>
							
							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">支付密码</label>
								<div class="am-form-content">
									<input type="text" id="paypwd" name="paypwd" value="" placeholder="支付密码" onkeyup="value=value.replace(/[\W]/g,'') ">
								</div>
							</div>
							
							<div class="info-btn">
								<div class="am-btn am-btn-danger" id="btn-submit" onclick="submit()">提交</div>
							</div>
						</form>
</c:if>
			</div>
					</div>
				</div>
				<jsp:include page="/common/footer.jsp"></jsp:include>
		</div>
				<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
				<script>
			$(document).ready(function() {
							loadCategory();	
			});
		</script>
	</body>

</html>
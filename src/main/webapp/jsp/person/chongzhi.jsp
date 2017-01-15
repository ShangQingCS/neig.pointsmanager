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
		<title>账户充值</title>
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
				var trade_amount=$('#wrap1 input[name="trade_amount"]:checked ').val();
				var trade_type = $('#wrap2 input[name="trade_type"]:checked ').val();
				$.post(
							_basePath+"/user_web/user/chongzhijson.do",
							{ 
								"trade_amount":trade_amount,
								"trade_type":trade_type	
							},function(data){
								if(data!=null && data!=''){
									
									if(trade_type==1){
										
										layer.open({
									        type: 1
									        ,title: false //不显示标题栏
									        ,closeBtn: false
									        ,area: '300px;'
									        ,shade: 0.8
									        ,id: 'LAY_layuipro1' //设定一个id，防止重复弹出
									        ,btn: ['我已支款', '其它方式']
									        ,moveType: 1 //拖拽模式，0或者1
									        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">如有提示，请允许弹出窗口，然后在新窗口中完成支付。'+data.msg+'</div>'
									        ,success: function(layero){
									          var btn = layero.find('.layui-layer-btn');
									          btn.css('text-align', 'center');
									          btn.find('.layui-layer-btn0').attr({
									            href: '/user_web/user/chongzhi.do'
									          });
									        }
									      });
										
										
									}else{
										layer.open({
									        type: 1
									        ,title: false //不显示标题栏
									        ,closeBtn: false
									        ,area: '260px;'
									        ,shade: 0.8
									        ,id: 'LAY_layuipro2' //设定一个id，防止重复弹出
									        ,btn: ['我已支款', '其它方式']
									        ,moveType: 1 //拖拽模式，0或者1
									        ,content: '<div style="padding: 2px 2px 0 2px; background-color: #393D49; color: #fff;"><img src = '+data.msg+' /></div>'
									        ,success: function(layero){
									          var btn = layero.find('.layui-layer-btn');
									          btn.css('text-align', 'center');
									          btn.find('.layui-layer-btn0').attr({
									            href: '/user_web/user/chongzhi.do'
									          });
									        }
									      });
									}
									
								}else{
									layer.msg('提交失败!',{icon:5,time:1500});
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
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">账户充值</strong> / <small>Prepaid account </small></div>
					</div>
					<hr/>
					<div class="authentication">

					    <p class="tip">可用余额：<font color="red">${user.userKyBalance }元</font> 分红（可用提现）金额：<font color="red">${user.userFxBalance }元</font> 提现冻结金额：<font color="red">${user.userDjBalance }元</font></p>
						<p class="tip">实名认证状态： <c:if test="${user.identityStatus==1 }"><font color="red">申请中</font></c:if><c:if test="${user.identityStatus==2 }"><font color="red">已认证</font></c:if><c:if test="${user.identityStatus==0 }">未认证  </c:if> 当前用户等级：<font color="red">${userGrade.gradeName }</font></p>
						<p class="tip">当前用户等级：<font color="red">${userGrade.gradeName }</font> 单笔最高提现金额：<font color="red">${userGrade.gradeTxBalance }元</font></p>
						<div class="authenticationInfo">
							<p class="title">账户充值信息</p>

 <c:if test="${user.tixianStatus==1 }">
	<p class="tip">您已有一笔提现申请处理中，提现金额：<font color="red">${user.userDjBalance }元</font> 当前状态：<font color="red">处理中</font>，请等待商城管理员处理后再次申请。</p>
 </c:if>

<c:if test="${user.identityStatus!=2 }">

	<p class="tip">请先 <a href="${path }/user_web/user/idcard.do"><font color="red">去实名认证</font></a>，才可以进行提现操作。</p>
	
</c:if>

<c:if test="${user.payPwd==''}">

	<p class="tip">请先 <a href="${path }/user_web/user/paypwd.do"><font color="red">设置支付密码</font></a>，才可以进行提现操作。</p>
	
</c:if>

<c:if test="${user.identityStatus==2 && user.payPwd!=''}">

                           <form action="/user_web/user/sqtx.do" method="post">

							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">真实姓名</label>
								<div class="am-form-content">
									<input type="text" id="true-name" value="${user.trueName}" disabled="disabled">
								</div>
							</div>
							
							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">充值金额</label>
								<div class="am-form-content" id="wrap1">
								<input name="trade_amount" type="radio" checked="" value="0.01" style="width:40px;height: 15px">0.01
								<input name="trade_amount" type="radio" checked="checked" value="100" style="width:40px;height: 15px">100
								<input name="trade_amount" type="radio" value="200" style="width:40px;height: 15px">200
								<input name="trade_amount" type="radio" value="300" style="width:40px;height: 15px">300
								<input name="trade_amount" type="radio" value="500" style="width:40px;height: 15px">500	
								<input name="trade_amount" type="radio" value="800" style="width:40px;height: 15px">800	
								<input name="trade_amount" type="radio" value="1000" style="width:40px;height: 15px">1000	
								</div>
							</div>
							
							<div class="am-form-group">
								<label for="user-name" class="am-form-label" style="width:110px">支付类型</label>
								<div class="am-form-content" id="wrap2">
									<input name="trade_type" type="radio" checked="checked" value="1" style="width:40px;height: 15px">支付宝
									<input name="trade_type" type="radio" value="2" style="width:40px;height: 15px">微信
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
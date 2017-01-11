<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=0">
		<jsp:include page="/common/include.jsp"></jsp:include>
		<title>我的积分</title>
		<jsp:include page="/common/meta.jsp"></jsp:include>
		<link href="${path}/css/wallet.css" rel="stylesheet" type="text/css">
		<script>
			$(function(){
				$("#data-am-selected").change(function(){
					var dateCode=$("#data-am-selected").val();
					if(dateCode==0){
						window.location.href="${path }/asset_web/walletlist.do?dateCode=0&type=1";
					}else if(dateCode==1){
						window.location.href="${path }/asset_web/walletlist.do?dateCode=1&type=1";
					}else if(dateCode==2){
						window.location.href="${path }/asset_web/walletlist.do?dateCode=2&type=1";
					}else{
						window.location.href="${path }/asset_web/walletlist.do?dateCode=3&type=1";
					}
 				});
			});
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
					<div class="am-cf am-padding">
						<div class="am-fl am-cf"><strong class="am-text-danger am-text-lg">积分明细</strong> / <small>Electronic&nbsp;bill</small></div>
					</div>
					<hr>
					<div class="finance">
						<div class="financeText">
							<p>可用余额:<span>${user.userKyBalance }</span></p>
							<p>分红:<span>${user.userFxBalance }</span></p>
							<p>账户状态:<span>
							<c:choose>
								<c:when test="${user.userStatus=='0'}">注销</c:when>
								<c:when test="${user.userStatus=='1'}">正常</c:when>
								<c:otherwise>冻结</c:otherwise>
							</c:choose>
							</span></p>
							<p>可用积分:<span>${user.userJfBalance }</span></p>
						</div>
					</div>

					<!--交易时间	-->

					<div class="order-time">
						<label class="form-label">交易时间：</label>
						<div class="show-input">
							<select class="am-selected" id="data-am-selected">
								<option value="0">今天</option>
								<option value="1" <c:if test="${dateCode==1}">selected="selected"</c:if>>最近一周</option>
								<option value="2" <c:if test="${dateCode==2}">selected="selected"</c:if>>最近一个月</option>
								<option value="3" <c:if test="${dateCode==3}">selected="selected"</c:if>>最近三个月</option>
								
							</select>
						</div>
						<div class="clear"></div>
					</div>

					<div class="walletList">

						<div class="walletTitle">
							<li class="time">创建时间</th>
								<li class="time">钱包类型</th>
								<li class="time">来源/去向</th>
								<li class="time">详情</th>
									<li class="amount">金额</th>
										<li class="balance">余额</th>
						</div>
						<div class="clear"></div>
						<div class="walletCont">
						<!-- 循环开始 -->
						<c:forEach items="${walletList}" var="mywallet">
							
							<c:if test="${mywallet.purseType=='1'}">							
<!-- 类型为积分 -->
								<li class="cost">
								<!-- 创建时间 -->
								<div class="time">
									<p> <fmt:formatDate value="${mywallet.createTime}" type="both"/></p>	
								</div>
								<!-- 钱包类型 -->
								<div class="time"><p>积分</p></div>
								<!-- 来源/去向 -->
								<div class="title name"><p class="content">
									<c:choose>
										<c:when test="${mywallet.tradeType=='0'}">支付宝0</c:when>
										<c:when test="${mywallet.tradeType=='1'}">微信1</c:when>
										<c:otherwise>其他234</c:otherwise>
									</c:choose></p>
								</div>
								<!--详情 -->
									<c:choose>
										<c:when test="${mywallet.optionType=='0'}">
										<div class="title name"><span>增加为0</span></div>
											<!--金额 -->
											<div class="amount">
											<span class="amount-pay" style="color:green">+${mywallet.tradeAmount}</span>
										</div>
										</c:when>
										<c:otherwise>
											<div class="title name"><span>消耗为1</span></div>
											<!--金额 -->
											<div class="amount">
											<span class="amount-pay" >-${mywallet.tradeAmount}</span>
										</div>
										</c:otherwise>
									</c:choose>
								
								<!--余额 -->
								<div class="balance">
									<span class="amount_green">${mywallet.userAmount}</span>
								</div>
							</li>		
						
					</c:if>
					<!-- 判断END -->
				</c:forEach>
				</div>
						
						<!--分页-->
						<ul class="am-pagination am-pagination-right">
							<li class="am-disabled"><a href="#">&laquo;</a></li>
							<li class="am-active"><a href="#">1</a></li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#">5</a></li>
							<li><a href="#">&raquo;</a></li>
						</ul>

					</div>

				</div>
				
		<!--底部-->
				<jsp:include page="/common/footer.jsp"></jsp:include>
			</div>
			<!-- 菜单栏-->
			<jsp:include page="/jsp/person/menuleft.jsp"></jsp:include>
		
		<script>
			$(document).ready(function() {
							loadCategory();	
			});
		</script>

	</body>

</html>
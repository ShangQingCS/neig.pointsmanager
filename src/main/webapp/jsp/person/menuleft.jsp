<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
			<aside class="menu">
				<ul>
					<li class="person">
						<a href="#">个人资料</a>
						<ul>
							<li class="active"> <a href="${path }/user_web/user/main.do">个人信息</a></li>
							<%-- <li> <a href="${path }/jsp/person/safety.jsp">安全设置</a></li> --%>
							<li> <a href="${path }/user_web/user/address.do">收货地址</a></li>
						</ul>
					</li>
					<li class="person">
						<a href="#">我的交易</a>
						<ul>
							<li><a href="${path }/order_web/search.do">订单管理</a></li>
							<!-- <li> <a href="change.html">退款售后</a></li> -->
						</ul>
					</li>
					<!-- <li class="person">
						<a href="#">我的资产</a>
						<ul>
							<li> <a href="coupon.html">优惠券 </a></li>
							<li> <a href="bonus.html">红包</a></li>
							<li> <a href="bill.html">账单明细</a></li>
						</ul>
					</li>

					<li class="person">
						<a href="#">我的小窝</a>
						<ul>
							<li> <a href="collection.html">收藏</a></li>
							<li> <a href="foot.html">足迹</a></li>
							<li> <a href="comment.html">评价</a></li>
							<li> <a href="news.html">消息</a></li>
						</ul>
					</li> -->

				</ul>

			</aside>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

			<aside class="menu">
				<ul>
					<li class="person active">
						<a href="${path }/asset_web/usercenter.do"><i class="am-icon-user"></i>个人中心</a>
					</li>
					
					<li class="person">
						<!--  <p><i class="am-icon-newspaper-o"></i>我的资料</p>-->
						<ul>
							<li class="" name="objperson"> <a href="${path }/user_web/user/main.do">我的信息</a></li>
							<li> <a href="${path }/user_web/user/safety.do">安全设置</a></li> 
							<li name="objaddress"> <a href="${path }/user_web/user/address.do">收货地址</a></li>
						</ul>
					</li>
				</ul>
				 
				<ul style="margin-top:10px">
					<li class="person active ">
						<a href="${path }/asset_web/usercenter.do"><i class="am-icon-balance-scale"></i> 我的交易</a>
					</li>
					
					<li class="person">
						<!--<p><i class="am-icon-balance-scale"></i>我的交易</p>-->
						<ul>
							<li name="objorder"><a href="${path }/order_web/search.do">未付款订单</a></li>
							<li name="objorder"><a href="${path }/order_web/search.do">已付款订单</a></li>
							<li name="objorder"><a href="${path }/order_web/search.do">已发货订单</a></li>
							<li name="objorder"><a href="${path }/order_web/search.do">已收货订单</a></li>
							<!-- <li> <a href="change.html">退款售后</a></li> -->
						</ul>
					</li>
				</ul>
				
				<ul style="margin-top:10px">	
					<li class="person active">
						<a href="${path }/asset_web/usercenter.do"><i class="am-icon-dollar"></i> 我的资产</a>
					</li>
					
					<li class="person">
						<!--<p>我的资产</p>-->
						<ul>
							<li> <a href="${path }/asset_web/walletlist.do?dateCode=1&type=0">我的钱包</a></li>
							<li> <a href="${path }/asset_web/walletlist.do?dateCode=1&type=1">我的积分</a></li>
							<li> <a href="${path }/asset_web/walletlist.do?dateCode=1&type=2">我的分红</a></li>
							<li> <a href="${path }/asset_web/myteam.do">我的团队</a></li>
							<li> <a href="${path }/asset_web/coupon.do">优&nbsp; 惠 &nbsp;券 </a></li>
							
						</ul>
					</li>
					<!-- 
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
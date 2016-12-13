<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<jsp:include page="/common/include.jsp"></jsp:include>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"> -->
		<title>商品页面</title>
		<link href="${path }/AmazeUI-2.4.2/assets/css/admin.css" rel="stylesheet" type="text/css" />
		<link href="${path }/AmazeUI-2.4.2/assets/css/amazeui.css" rel="stylesheet" type="text/css" />
		<link href="${path }/basic/css/demo.css" rel="stylesheet" type="text/css" />
		<link type="text/css" href="${path }/css/optstyle.css" rel="stylesheet" />
		<link type="text/css" href="${path }/css/style.css" rel="stylesheet" />
		<script type="text/javascript" src="${path }/basic/js/jquery-1.7.min.js"></script>
		<script type="text/javascript" src="${path }/basic/js/quick_links.js"></script>
		<script type="text/javascript" src="${path }/AmazeUI-2.4.2/assets/js/amazeui.js"></script>
		<script type="text/javascript" src="${path }/js/jquery.imagezoom.min.js"></script>
		<script type="text/javascript" src="${path }/js/jquery.flexslider.js"></script>
		<script type="text/javascript" src="${path }/js/jquery.tmpl.js"></script>
		<script type="text/javascript" src="${path }/js/jquery.tmpl.forjsp.js"></script>
		<script type="text/javascript" src="${path }/js/list.js"></script>
	</head>

	<body>
		<!--顶部导航条 -->
			<jsp:include page="/common/header.jsp"></jsp:include>

			<!--悬浮搜索框-->
			<jsp:include page="/common/searchbar.jsp"></jsp:include>
			
            <b class="line"></b>
			<div class="listMain">

				<!--分类-->
			<div class="nav-table">
					   <div class="long-title"><span class="all-goods">商品信息</span></div>
					   <div class="nav-cont">
							<ul>
								<li class="index"><a href="${path }/index_web/home.do">首页</a></li>
                               
							</ul>
						    
						</div>
			</div>
				<ol class="am-breadcrumb am-breadcrumb-slash">
					<li><a href="#">首页</a></li>
					<li><a href="#">分类</a></li>
					<li class="am-active">内容</li>
				</ol>
				<script type="text/javascript">
					$(function() {});
					$(window).load(function() {
						$('.flexslider').flexslider({
							animation: "slide",
							start: function(slider) {
								$('body').removeClass('loading');
							}
						});
					});
				</script>
				<div class="scoll">
					<section class="slider">
						<div class="flexslider">
							<ul class="slides">
								<li>
									<img src="${path }/images/01.jpg" title="pic" />
								</li>
								<li>
									<img src="${path }/images/02.jpg" />
								</li>
								<li>
									<img src="${path }/images/03.jpg" />
								</li>
							</ul>
						</div>
					</section>
				</div>

				<!--放大镜-->

				<div class="item-inform">
					<div class="clearfixLeft" id="clearcontent">

						<div class="box">
							<script type="text/javascript">
								$(document).ready(function() {
									$(".jqzoom").imagezoom();
									$("#thumblist li a").click(function() {
										$(this).parents("li").addClass("tb-selected").siblings().removeClass("tb-selected");
										$(".jqzoom").attr('src', $(this).find("img").attr("mid"));
										$(".jqzoom").attr('rel', $(this).find("img").attr("big"));
									});
								});
							</script>

							<div class="tb-booth tb-pic tb-s310">
								<a href="${baseimg }${goods.img1}"><img src="${baseimg }${goods.img1}" alt="细节展示放大镜特效" rel="${baseimg }${goods.img1}" class="jqzoom" /></a>
							</div>
							<ul class="tb-thumb" id="thumblist">
								<c:if test="${goods.img1!=null && goods.img1 !='' }">
								<li>
									<div class="tb-pic tb-s40">
										<a href="#"><img src="${baseimg }${goods.img1}" mid="${baseimg }${goods.img1}" big="${baseimg }${goods.img1}"></a>
									</div>
								</li>
								</c:if>
								<c:if test="${goods.img2!=null && goods.img2 !='' }">
								<li>
									<div class="tb-pic tb-s40">
										<a href="#"><img src="${baseimg }${goods.img2}" mid="${baseimg }${goods.img2}" big="${baseimg }${goods.img2}"></a>
									</div>
								</li>
								</c:if><c:if test="${goods.img3!=null && goods.img3 !='' }">
								<li>
									<div class="tb-pic tb-s40">
										<a href="#"><img src="${baseimg }${goods.img3}" mid="${baseimg }${goods.img3}" big="${baseimg }${goods.img3}"></a>
									</div>
								</li>
								</c:if><c:if test="${goods.img4!=null && goods.img4 !='' }">
								<li>
									<div class="tb-pic tb-s40">
										<a href="#"><img src="${baseimg }${goods.img4}" mid="${baseimg }${goods.img4}" big="${baseimg }${goods.img4}"></a>
									</div>
								</li>
								</c:if><c:if test="${goods.img5!=null && goods.img5 !='' }">
								<li>
									<div class="tb-pic tb-s40">
										<a href="#"><img src="${baseimg }${goods.img5}" mid="${baseimg }${goods.img5}" big="${baseimg }${goods.img5}"></a>
									</div>
								</li>
								</c:if>
							</ul>
						</div>

						<div class="clear"></div>
					</div>

					<div class="clearfixRight">

						<!--规格属性-->
						<!--名称-->
						<div class="tb-detail-hd">
							<h1>	
				 ${goods.gname }
	          </h1>
						</div>
						<div class="tb-detail-list">
							<dl class="iteminfo_parameter freight">
								<dt>价格</dt>
								<div class="iteminfo_freprice">
									<div class="pay-logis iteminfo_price">
										<em>¥</em><b class="sys_item_price">${goods.price }</b> 
									</div>
								</div>
							</dl>
							<div class="clear"></div>
							<dl class="iteminfo_parameter freight">
								<dt>运费</dt>
								<div class="iteminfo_freprice">
									<div class="pay-logis">
										包邮
									</div>
								</div>
							</dl>
							<div class="clear"></div>
							
							<!--各种规格-->
							<dl class="iteminfo_parameter sys_item_specpara">
								<dt class="theme-login"><div class="cart-title">可选规格<span class="am-icon-angle-right"></span></div></dt>
								<dd>
									<!--操作页面-->

									<div class="theme-popover-mask"></div>

									<div class="theme-popover">
										<div class="theme-span"></div>
										<div class="theme-poptit">
											<a href="javascript:;" title="关闭" class="close">×</a>
										</div>
										<div class="theme-popbod dform">
											<form class="theme-signin" name="loginform" action="" method="post">

												<div class="theme-signin-left">

													<div class="theme-options">
														<div class="cart-title number">数量</div>
														<dd>
															<input id="min" class="am-btn am-btn-default" name="" type="button" value="-" />
															<input id="text_box" name="" type="text" value="1" style="width:30px;" />
															<input id="add" class="am-btn am-btn-default" name="" type="button" value="+" />
															<span id="Stock" class="tb-hidden">库存<span class="stock">${goods.storenumb }</span>件</span>
														</dd>

													</div>
													<div class="clear"></div>

													<div class="btn-op">
														<div class="btn am-btn am-btn-warning">确认</div>
														<div class="btn close am-btn am-btn-warning">取消</div>
													</div>
												</div>
												<div class="theme-signin-right">
													<div class="img-info">
														<img src="${path }/images/songzi.jpg" />
													</div>
													<div class="text-info">
														<span class="J_Price price-now">¥39.00</span>
														<span id="Stock" class="tb-hidden">库存<span class="stock">${goods.storenumb }</span>件</span>
													</div>
												</div>

											</form>
										</div>
									</div>

								</dd>
							</dl>
							<div class="clear"></div>
							
						</div>

						<div class="pay">
							<div class="pay-opt">
							<a href="home.html"><span class="am-icon-home am-icon-fw">首页</span></a>
							<a><span class="am-icon-heart am-icon-fw">收藏</span></a>
							
							</div>
							<li>
								<div class="clearfix tb-btn tb-btn-buy theme-login">
									<a id="LikBuy" title="点此按钮到下一步确认购买信息" href="${path }/jsp/pay.jsp">立即购买</a>
								</div>
							</li>
							<li>
								<div class="clearfix tb-btn tb-btn-basket theme-login">
									<a id="LikBasket" title="加入购物车" href="${path }/jsp/shopcart.jsp"><i></i>加入购物车</a>
								</div>
							</li>
						</div>

					</div>

					<div class="clear"></div>

				</div>

				<!--优惠套装-->
				<div class="match" style="display:none;">
					
				</div>
				<div class="clear"></div>
				
							
				<!-- introduce-->

				<div class="introduce">
					
					<div class="introduceMain">
						<div class="am-tabs" data-am-tabs>
							<ul class="am-avg-sm-3 am-tabs-nav am-nav am-nav-tabs">
								<li class="am-active">
									<a href="#">

										<span class="index-needs-dt-txt">宝贝详情</span></a>

								</li>

								<li>
									<a href="#">

										<span class="index-needs-dt-txt comment" >全部评价</span></a>

								</li>

								
							</ul>

							<div class="am-tabs-bd">

								<div class="am-tab-panel am-fade am-in am-active">
									<div class="J_Brand">

									</div>

									<div class="details">
										<div class="attr-list-hd after-market-hd">
											<h4>商品细节</h4>
										</div>
										<div class="twlistNews">
											${goods.detail }
										</div>
									</div>
									<div class="clear"></div>

								</div>

								<div class="am-tab-panel am-fade">
									
                                    <div class="actor-new">
                                    	<div class="rate">                
                                    		<strong class="avrage">100<span>%</span></strong><br> <span>好评度</span>            
                                    	</div>
                                        
                                    </div>	
                                    <div class="clear"></div>
									<div class="tb-r-filter-bar">
										<ul class=" tb-taglist am-avg-sm-4">
											<li class="tb-taglist-li tb-taglist-li-current">
												<div class="comment-info">
													<span>全部评价</span>
													<span class="tb-tbcr-num allnumb">(32)</span>
												</div>
											</li>

										</ul>
									</div>
									<div class="clear"></div>
									<ul class="am-comments-list am-comments-list-flip commentsinfo">
										
									</ul>

									<div class="clear"></div>

									<!--分页 -->
									<ul class="am-pagination am-pagination-right pagination">
										<li class="am-disabled"><a href="#" name="prev">&laquo;</a></li>
										<li class="am-active" name="pages"><a href="#" name="1">1</a></li>
										<li name="pages"><a href="#" name="2">2</a></li>
										<li name="pages"><a href="#" name="3">3</a></li>
										<li name="pages"><a href="#" name="4">4</a></li>
										<li name="pages"><a href="#" name="5">5</a></li>
										<li><a href="#" name="next">&raquo;</a></li>
									</ul>
									<div class="clear"></div>


								</div>


								</div>

							</div>

						</div>

						<div class="clear"></div>

						<jsp:include page="/common/footer.jsp"></jsp:include>
					</div>

				</div>
			</div>
	</body>
<script id="comment" type="text/x-jquery-tmpl">
										<li class="am-comment">
											<!-- 评论容器 -->
											<a href="">
												<img class="am-comment-avatar" src="${path }/images/hwbn40x40.jpg" />
												<!-- 评论者头像 -->
											</a>

											<div class="am-comment-main">
												<!-- 评论内容容器 -->
												<header class="am-comment-hd">
													
													<div class="am-comment-meta">
														<!-- 评论元数据 -->
														
														{{if ishidden == 0 }}
														<a href="#link-to-user" class="am-comment-author">
 														@{@item.sbtr(@data.username,0,3)}***@{@item.sbtr(@data.username,@data.username.length()-3,3)}(匿名)
														</a> 
														{{else}}
														<a href="#link-to-user" class="am-comment-author">
														@{@data.username}
														</a> 
														{{/if}}
														<!-- 评论者 -->
														评论于
														<time datetime="@{@data.createTime}">@{@data.createTime}</time>
													</div>
												</header>

												<div class="am-comment-bd">
													<div class="tb-rev-item " data-id="@{@data.id}">
														<div class="J_TbcRate_ReviewContent tb-tbcr-content ">
															@{@data.comment}
														</div>
													</div>

												</div>
												<!-- 评论内容 -->
											</div>
										</li>
</script>
<script type="text/javascript">

$(document).ready(function(){
	var pgsize="10";
	var nowpage=("${page.currentPage}"==""?"0":"${page.currentPage}");
	var gid=${goods.id};
	var reg=new RegExp("^[0-9]*$");

	var substring={
		sbtr: function (value_,start_,end_){
			return value_.substring(start_,end_);
		}
	}

	function loadcomment(goodsid,pagesize,nowpage){
		$.post(
			"${path}/comment_web/search.do",
			{
				goodsid:goodsid,
				pagesize:pagesize,
				nowpage:nowpage
			},function(data){
				$(".commentsinfo").empty();
				$(".pagination").empty();
				$("#comment").tpl(data.data,substring).appendTo($(".commentsinfo"));
				$(".avrage").html((data.data[0].avgscore*20)+"<span>%</span>");
				$(".allnumb").html("("+data.page.totalRows+")");
				
				$(".pagination").find("li").removeAttr("class");
				var htmlstring="";
				$(".pagination").append('<li><a href="#" name="prev">&laquo;</a></li>');
				for(var k=1;k <= data.page.totalPage ; k++ ){
					$(".pagination").append('<li name="pages"><a href="#" name="'+k+'">'+k+'</a></li>');
				}
				$(".pagination").append('<li><a href="#" name="next">&raquo;</a></li>');
					
					if(data.page.currentPage == 1){
						$("a[name='prev']").parent("li").attr("class","am-disabled");
					}else if(data.page.currentPage == data.page.totalPage){
						$("a[name='next']").parent("li").attr("class","am-disabled");
					}
					$("a[name='"+data.page.currentPage+"']").parent("li").attr("class","am-active");
				
				$(".pagination").on("click","li,a",function(this_){
					var name_;
					
					if($(this).is("li")){
						name_=$(this).find("a").attr("name");
					}else{
						name_=$(this).attr("name");
					}
					nowpage=$(".am-active[name='pages']").find("a").attr("name");
					if(name_ == "prev"){
						nowpage=nowpage-2;
					}else if(name_ == "next"){}
					else{
						nowpage=name_-1;
					}
					loadcomment(gid,pgsize,nowpage);
					});
				
				
			},"json"
			
		);
	
	}
	
	$("li").on("click",".comment",function(){
		loadcomment(gid,pgsize,nowpage);
	});
	
});
</script>
</html>
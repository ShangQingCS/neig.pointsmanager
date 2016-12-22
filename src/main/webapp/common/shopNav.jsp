<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

	<div class="shopNav">
		<div class="slideall">
			<div class="long-title"><span class="all-goods">全部分类</span></div>
			<div class="nav-cont">
				<ul>
					<li class="index">
						<a href="${path }/index_web/home.do">首页</a>
					</li>
				</ul>
			</div>

			<!--侧边导航 -->
			<div id="nav" class="navfull">
				<div class="area clearfix">
					<div class="category-content" id="guide_2">
						<div class="category">
							<ul class="category-list" id="js_climit_li">

							</ul>
						</div>
					</div>
				</div>
			</div>
			<script id="category" type="text/x-jsrender">
				{{for tree}}

				<li class="appliance js_toggle relative">
					<div class="category-info">
						<h3 class="category-name b-category-name"><%-- <i><img src="${path }/images/cake.png"></i> --%><a title="{{: leve1.cateName}}">{{: leve1.cateName}}</a></h3>
						<em>&gt;</em>
					</div>
					<div class="menu-item menu-in top">
						<div class="area-in">
							<div class="area-bg">
								<div class="menu-srot">
									<div class="sort-side">
										{{for item}}
										<dl class="dl-sort">
											<dt><span title="{{: leve2.cateName }}">{{: leve2.cateName }}</span></dt> {{for item}}
											<dd>
												<a title="{{: leve3.cateName }}" href="${path }/index_web/{{:leve3.id }}/catagroygoodslist.do"><span>{{: leve3.cateName }}</span></a>
											</dd>
											
											{{/for}}
										</dl>
										
										{{/for}}

									</div>

								</div>
							</div>
						</div>
					</div>
					<b class="arrow"></b>
				</li>

				{{/for}}
			</script>
			<!--轮播-->
			<script type="text/javascript">
				function constructCategory(data) {
					var tmpl = $.templates("#category"); // Get compiled template       // Define data
					var html = tmpl.render(data); // Render template using data - as HTML string
					$(".category-list").append(html); // Insert HTML string into DOM
				}
				function loadCategory() {
					$.post(
						_basePath + "index_web/getcategoryall.do", {},
						function(data) {
							if(data.result == 0) {
								constructCategory(data.data);
								$("div#guide_2").css("display", "none");
								$("div.slideall").hover(function(e){
									var classname=$(e.target).attr("class");
									if(classname == 'long-title' || classname == 'all-goods'){
										$("div#guide_2").css("display", "block");
									}
								},function(){
									$("div#guide_2").css("display", "none");
								});
								
								$("li").hover(function() {
									$(".category-content .category-list li.first .menu-in").css("display", "none");
									$(".category-content .category-list li.first").removeClass("hover");
									$(this).addClass("hover");
									$(this).children("div.menu-in").css("display", "block")
								}, function() {
									$(this).removeClass("hover")
									$(this).children("div.menu-in").css("display", "none")
								});
							}
						}, "json"
					);
				}
			</script>
		</div>
	</div>

<b class="line"></b>
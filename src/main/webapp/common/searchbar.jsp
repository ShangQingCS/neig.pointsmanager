<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--悬浮搜索框-->
<div class="nav white">
	
	<div class="logoBig">
		<li><img src="${path }/images/logobig.png" /></li>
	</div>
	<div class="search-bar pr">
		<a name="index_none_header_sysc" href="#"></a>
		<form action="" method="post" id="searchbar">
			<input id="searchInput" name="index_none_header_sysc" value="${index_none_header_sysc}" type="text" placeholder="搜索" autocomplete="off">
			<input id="ai-topsearch" class="submit am-btn" value="搜索" index="1" type="button">
		</form>
	</div>
	<script>
		$("#ai-topsearch").click(function (){
			$("#searchbar").attr("action","${path }/index_web/search.do");
			$("#searchbar").submit();
		});
	</script>
</div>
<div class="clear"></div>

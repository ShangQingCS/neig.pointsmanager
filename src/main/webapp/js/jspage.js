function loadpage(page) {
	$(".pagination").find("li").removeAttr("class");
	var htmlstring = "";
	$(".pagination").append('<li><a href="#" name="prev">&laquo;</a></li>');
	for(var k = 1; k <= page.totalPage; k++) {
		$(".pagination").append('<li name="pages"><a href="#" name="' + k + '">' + k + '</a></li>');
	}
	$(".pagination").append('<li><a href="#" name="next">&raquo;</a></li>');

	if(page.currentPage == 1) {
		$("a[name='prev']").parent("li").attr("class", "am-disabled");
	} else if(page.currentPage == page.totalPage) {
		$("a[name='next']").parent("li").attr("class", "am-disabled");
	}
	$("a[name='" + page.currentPage + "']").parent("li").attr("class", "am-active");

	
}
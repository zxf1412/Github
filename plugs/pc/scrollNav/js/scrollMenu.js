var scrollMenu = function(menuId, contentId){
	$(window).scroll(function(event) {
		var top = $(window).scrollTop();
		//console.log(top);
		var menu = $("#"+menuId);
		var items = $("#"+contentId).find(".item");
		var currentId = "";//当前楼层的ID；
		items.each(function() {
			var item = $(this);
			var itemTop = item.offset().top;
			//console.log(itemTop);

			if(top > itemTop - 200){
				currentId = "#"+item.attr("id");
			}else{
				return false;
			}
		});

		//$("#menu a[href="+currentId+"]").addClass('current');
	 	var currentLink = menu.find(".current");
	 	if(currentId && currentLink.attr("href") != currentId){
	 		currentLink.removeClass('current');
	 		menu.find('[href='+currentId+']').addClass('current');
	 	}
	});
}



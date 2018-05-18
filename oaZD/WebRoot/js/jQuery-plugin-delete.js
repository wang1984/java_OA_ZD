/**
 * 该插件完成的任务：给所有的系统页面上的超级链接添加插件
 */
(function(jQuery){
	jQuery.oaConfirm = function(){
		//利用jQuery的选择器获取到当前页面所有的超级链接
		$("a").each(function(){
			//遍历每一个超级链接，判断哪个超级链接是"删除"
			if($(this).text()=="删除"){//如果是删除的超级链接
			    //给删除的超级链接添加click事件
				$(this).unbind("click");
				$(this).bind("click",function(){
					if (window.confirm("您确认要删除吗?")) {
						return true;
					}else{
						return false;
					}
				});
			}
		});
	}
})(jQuery);

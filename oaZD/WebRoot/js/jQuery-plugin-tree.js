(function($){
    $.fn.TreePanel = {
        defaults: {
			url:'',
			data:null,
			id:'',
            setting: {
                isSimpleData: true,
                treeNodeKey: "id",
                treeNodeParentKey: "pid",
                showLine: true,
                root: {
                    isRoot: true,
                    nodes: []
                }
            }
        },
      
        createTree: function(config){
        	  /**
	             * config = {
	             * 	  url:
	             *    data:
	             *    id：
	             *    setting:{
	             *    	treeNodeKey: "rid"
	             *    }
	             * }
	             */
        	var con = {};
			/**
			 * 用户传递过来的配置覆盖掉默认的配置
			 *    true为深度覆盖
			 */
			con = $.extend(true,$.fn.TreePanel.defaults,config);
			 
            $.post(config.url, config.data, function(data){
                $("#" + config.id).zTree(con.setting, data);
            })
        }
    };
})($);

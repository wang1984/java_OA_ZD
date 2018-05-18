(function($){
	/**
	 * @param {Object} namespace
	 *     "cn.wang.oa.TreePanel"
	 */
    $.nameSpace = function(namespace){
		/**
		 * ss[]={"cn","wang","oa","TreePanel"}
		 */
        var ss = namespace.split(".");
        var tempNs = [];
		/**
		 * 当第一遍循环
		 *    window.cn
		 * 当第二遍循环
		 *    window.cn.itcast
		 *    ......
		 */
        for (var i = 0; i < ss.length; i++) {
            tempNs.push(ss[i]);
            var n = tempNs.join(".");
            /* 例： 如果 window.cn 不是一个对象那么就创建该对象 */
            if (typeof window[n] != "object") {  
                eval("window." + n + "={}");  //eval将符合js语法的字符串变成js执行
            }
        }
    }
})($);

/*$().ready(function(){
	$.nameSpace("cn.wang.oa.TreePanel");
	alert(window.cn.wang.oa.TreePanel);
});*/


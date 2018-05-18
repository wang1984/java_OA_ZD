$().ready(function(){
	//1、创建命名空间
	$.nameSpace("cn.wang.oa.MenuItemTree");
	//2、启动继承把TreePanel实现继承
	$.extend(cn.wang.oa.MenuItemTree,$.fn.TreePanel);
	//3、调用createTree方法创建树
	cn.wang.oa.MenuItemTree.createTree({
		url:'privilegeAction_showMenuitemTreeByUid.action',
		data:null,
		id:'menuTree'
	});
	 
});

var privilege = {
	data:{
		role:{
			rid:'',
			name:''
		}
	},
	init:{
		/**
		 * 初始化数据
		 */
		initData:function(){
			privilege.data.role.name = $(this).parent().siblings("td:first").text();
			privilege.data.role.rid = $(this).parent().siblings("input[type='hidden']").val();
		},
		/**
		 * 初始化事件
		 */
		initEvent:function(){
			/**
			 * 给设置权限添加click事件
			 */
			$("a").each(function(){
				if($(this).text()=="设置权限"){
					$(this).unbind("click");
					$(this).bind("click",function(){
						//1、显示div
						privilege.option.divOpt.showDiv();
						//2、初始化数据
						privilege.init.initData.call(this);
						//3、动态显示角色的名称
						privilege.option.roleOpt.showRoleName();
						//设置全选复选框初始化的状态值
						privilege.option.privilegeTree.isAllChecked(false);
						//4、加载权限树
						privilege.option.privilegeTree.loadPrivilegeTree();
						return false;//阻止a连接的默认行为
					});
				}
			});
			
			/**
			 * 给全选复选框添加change事件
			 */
			$("#allchecked").unbind("change");
			$("#allchecked").bind("change",function(){
				privilege.option.privilegeTree.allChecked.call(this);
			});
			
			/**
			 * 给保存(建立角色和权限之间的关系)添加click事件
			 */
			$("#savePrivilege").unbind("click");
			$("#savePrivilege").bind("click",function(){
				privilege.option.privilegeTree.savePrivilege();
			});
		}
	},
	option:{
		roleOpt:{
			showRoleName:function(){
				$("#roleImage").text("角色:"+privilege.data.role.name);
			}
		},
		divOpt:{
			showDiv:function(){
				$("div:hidden").show();
			}
		},
		privilegeTree:{
			zTreeplugin:'',
			setting: {
				isSimpleData: true,
				treeNodeKey: "id",
				treeNodeParentKey: "pid",
				showLine: true,
				root: {
					isRoot: true,
					nodes: []
				},
				checkable:true
			},
			loadPrivilegeTree:function(){
				$.post("privilegeAction_showPrivilegeByRid.action",{
					rid:privilege.data.role.rid
				},function(data){
					privilege.option.privilegeTree.zTreeplugin = $("#privilegeTree").zTree(privilege.option.privilegeTree.setting,data);
					//设置全选复选框的初始化的默认值
					privilege.option.privilegeTree.setAllCheckedValue();
				});
			},
			/**
			 * 设置全选复选框的状态
			 */
			isAllChecked:function(checked){
				$("#allchecked").attr("disabled",checked);
			},
			/**
			 *设置全选复选框的默认值 
			 */
			setAllCheckedValue:function(){
				var uncheckedNodes = privilege.option.privilegeTree.zTreeplugin.getCheckedNodes(false);
				if (uncheckedNodes.length == 0) {//全部被选中
					$("#allchecked").attr("checked", true);
				}else{
					$("#allchecked").attr("checked", false);
				}
			},
			/**
			 * 全选复选框的功能
			 */
			allChecked:function(){
				if($(this).attr("checked")){//全选复选框被选中
					privilege.option.privilegeTree.zTreeplugin.checkAllNodes(true);
				}else{
					privilege.option.privilegeTree.zTreeplugin.checkAllNodes(false);
				}
			},
			/**
			 * 保存
			 */
			savePrivilege:function(){
				var checkedNodes = privilege.option.privilegeTree.zTreeplugin.getCheckedNodes(true);//所有被选中的权限值
				console.log(checkedNodes);
				var checkedStr = "";
				for(var i=0;i<checkedNodes.length;i++){
					if(i==checkedNodes.length-1){
						checkedStr = checkedStr + checkedNodes[i].id;
					}else{
						checkedStr = checkedStr + checkedNodes[i].id+",";
					}
				}
			
				var parameter = {
					rid:privilege.data.role.rid,
					checkedStr:checkedStr
				};
				$.post("privilegeAction_savePrivilege.action",parameter,function(data){
					alert("操作成功");
				});
			}
		}
	}
};

$().ready(function(){
	//初始化事件
	privilege.init.initEvent();
});

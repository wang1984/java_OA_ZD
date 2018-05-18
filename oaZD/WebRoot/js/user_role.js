var role = {
    //专门用来存放数据的
    data: {
        user: {
            username: '',
            uid: ''
        }
    },
    init: {
        //初始化数据
        initData: function(){
            /**
             * 要求this为设置角色的超级链接
             */
            role.data.user.username = $(this).parent().siblings("td:first").text();
            role.data.user.uid = $(this).parent().siblings("input[type='hidden']").val();
        },
        //初始化事件
        initEvent: function(){
            /**
             * 给设置角色声明click事件
             */
            $("a").each(function(){
                if ($(this).text() == "设置角色") {
                    $(this).unbind("click");
                    $(this).bind("click", function(){
                        /** 1、显示div*/
                        role.option.divOpt.showDiv();
                        /** 2、给user中的username和uid赋值*/
                        role.init.initData.call(this);//相当于this调用initData方法
                        /**3、显示用户的名称 */
                        role.option.userOpt.showUsername();
						
						/**
						 * 经过分析可以得出当角色树没有加载出来的情况下，全选复选框是不能点击的，
						 * 只有当角色树加载出来的情况下，全选复选框才能点击
						 */
						/**
						 * 设置全选复选框的初始化状态值为不可用
						 */
						role.option.roleTree.changeCheckBoxStatus("disabled");
						/**
						 * 显示loading，隐藏roleTree
						 */
						role.option.roleTree.changeLoadingAndRoleTree({
							roleTree:false
						});
                        /**
                         * 4、加载角色树
                         */
						role.option.roleTree.loadRoleTree();
                        return false;//阻止a连接的默认行为
                    });
                }
           });
            
            /** 给全选复选框增加change事件*/
            $("#allchecked").unbind("change");
            $("#allchecked").bind("change", function(){
                role.option.roleTree.allChecked.call(this);
            });
            
            /** 给保存按钮增加click事件*/
            $("#InputDetailBar img").unbind("click");
            $("#InputDetailBar img").bind("click", function(){
                role.option.roleTree.saveRole();
            });
        }
    },
    //页面的控制操作
    option: {
        //涉及到div的操作
        divOpt: {
            showDiv: function(){
                /**
                 * 把所有的隐藏的div显示出来
                 */
                $("div:hidden").show();
            }
        },
        //涉及到的用户的操作
        userOpt: {
            /**
             * 显示用户名称
             */
            showUsername: function(){
                $("#userImage").text("用户:" + role.data.user.username);
            }
        },
        //涉及到角色树的操作
        roleTree: {
			
        	zTreePlugin:'',//接受zTree函数的返回值
            
			setting: {
                isSimpleData: true,
                treeNodeKey: "rid",
                treeNodeParentKey: "pid",
                showLine: true,
                root: {
                    isRoot: true,
                    nodes: []
                },
				/**  显示树上的复选框  */
				checkable:true,
				/**  给角色树添加事件  */
				callback:{
					change:function(){
						/**  调用setAllChecked方法设置全选复选框的状态 */
						role.option.roleTree.setAllChecked();
					}
				}
            },
            /**
             * 加载角色树
             *     加载角色树的时候，应该对角色树进行回显
             *     如果角色树的某一个节点的复选框被选中，实际上就是该节点的属性checked为true
             */
            loadRoleTree: function(){
                $.post("roleJSONAction_showRoleTree.action", {
					uid:role.data.user.uid
				}, function(data){
					 
                	role.option.roleTree.zTreePlugin = $("#roleTree").zTree(role.option.roleTree.setting,data);
					/** 必须等角色树加载出来以后，才能使全选复选框恢复可用状态 */
					role.option.roleTree.changeCheckBoxStatus("");
					/** 当整个角色树被加载出来以后，显示角色树，隐藏loading */
					role.option.roleTree.changeLoadingAndRoleTree({
						roleTree:true
					});
					/** 设置全选复选框初始化状态的值 */
					role.option.roleTree.setAllChecked();
                });
            },
			
            /**
			 * 变换全选复选框的选中状态
			 */
			changeCheckBoxStatus:function(status){
				$("#allchecked").attr("disabled",status);
			},
			/**
			 * 从loading.gif到roleTree的转化
			 */
			changeLoadingAndRoleTree:function(json){
				if(json.roleTree){
					$("#roleTree").show();
					$("#loading").hide();
				}else{
					$("#roleTree").hide();
					$("#loading").show();
				}
			},
			/** 全选复选框的功能 */
			allChecked:function(){
				if($(this).attr("checked")){
					role.option.roleTree.zTreePlugin.checkAllNodes(true);	
				}else{
					role.option.roleTree.zTreePlugin.checkAllNodes(false);	
				}
			},
			/**  设置全选复选框被选中的状态 */
			setAllChecked:function(){
				var uncheckedNodes = role.option.roleTree.zTreePlugin.getCheckedNodes(false);
				if(uncheckedNodes.length==0){//说明全部被选中
					$("#allchecked").attr("checked",true);
				}else{//没有被全部选中
					$("#allchecked").attr("checked",false);
				}
			},
			/**
			 * 保存
			 *    建立用户和角色之间的关系
			 */
			saveRole:function(){
				/**
				 * 1、获取uid和被选中的所有的角色的rid
				 *      rid选中了1,2,3    "1,2,3"
				 * 2、后台只要对用户进行update操作
				 */
				//被选中的所有的角色
				var checkedNodes = role.option.roleTree.zTreePlugin.getCheckedNodes(true);
				var checkedStr = "";
				for(var i=0;i<checkedNodes.length;i++){
					if(i==checkedNodes.length-1){//最后一个
						checkedStr = checkedStr + checkedNodes[i].rid;
					}else{
						checkedStr = checkedStr + checkedNodes[i].rid+",";
					}
				}
				/**
				 * ajax端向后台传递的参数
				 */
				var parameter = {
					uid:role.data.user.uid,
					checkedStr:checkedStr
				};
				$.post("roleAction_saveRole.action",parameter,function(data){
					 
					alert("操作成功");
				});
			}
        }
    }
};

$().ready(function(){
    //初始化页面上的事件
    role.init.initEvent();
});

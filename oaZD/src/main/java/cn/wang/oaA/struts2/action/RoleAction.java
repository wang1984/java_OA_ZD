package cn.wang.oaA.struts2.action;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.domain.system.Role;
import cn.wang.oaA.domain.system.User;
import cn.wang.oaA.service.DepartmentService;
import cn.wang.oaA.service.RoleService;
import cn.wang.oaA.service.UserService;
import cn.wang.oaA.struts2.action.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

 
@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="departmentService")
	private DepartmentService departmentService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	private Long uid;
	private String checkedStr;
	
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}
	public String showAllRole(){
		Collection<Role> roleList = this.roleService.getAllRole();
		ActionContext.getContext().put("roleList",roleList);
		return listAction;
	}
	
	public String addUI(){
		return addUI;
	}
	
	public String add(){
		Role role = new Role();
		BeanUtils.copyProperties(this.getModel(), role);
		this.roleService.saveRole(role);
		return action2action;
	}
	
	public String update(){
		Role role = this.roleService.getRoleById(this.getModel().getRid());
		BeanUtils.copyProperties(this.getModel(), role);
		this.roleService.updateRole(role);
		return action2action;
	}
	
	public String updateUI(){
		Role role = this.roleService.getRoleById(this.getModel().getRid());
		ActionContext.getContext().getValueStack().push(role);
		return updateUI;
	}
	
	public String delete(){
		this.roleService.deleteRole(this.getModel().getRid());
		return action2action;
	}
	
	/**
	 * 加载角色树
	 * @throws Exception 
	 */
	public String showRoleTree() throws Exception{
		Collection<Role> roles = this.roleService.getRolesByUid(uid);
		ActionContext.getContext().getValueStack().push(roles);
		Thread.sleep(3001);	
		//Thread.sleep(3001);	
		return SUCCESS;
	}
	
	/**
	 * 保存动作
	 * 建立用户和角色之间的关系
	 */
	public String saveRole(){
		User user = this.userService.getUserById(this.uid);
		Set<Role> roles = this.roleService.getRolesByIds(this.checkedStr);
		if(roles.isEmpty()){
			user.setRoles(null);
		}else
		{
			user.setRoles(roles);	
		}
		 
		this.userService.updateUser(user);
		return SUCCESS;
	}
}

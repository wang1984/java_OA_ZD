package cn.wang.oaA.struts2.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.domain.system.Privilege;
import cn.wang.oaA.domain.system.User;
import cn.wang.oaA.service.PrivilegeService;
import cn.wang.oaA.service.RoleService;
import cn.wang.oaA.struts2.action.base.BaseAction;
import cn.wang.oaA.util.OAUtils;

import com.opensymphony.xwork2.ActionContext;



@Controller("privilegeAction")
@Scope("prototype")
public class PrivilegeAction extends BaseAction<Privilege>{
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	
	@Resource(name="roleService")
	private RoleService roleService;
	
	private Long rid;
	private String checkedStr;
	
	public String getCheckedStr() {
		return checkedStr;
	}

	public void setCheckedStr(String checkedStr) {
		this.checkedStr = checkedStr;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public String showPrivilegeByRid(){
		Collection<Privilege> privileges = this.privilegeService.getPrivilegesByRid(rid);
		ActionContext.getContext().getValueStack().push(privileges);
		return SUCCESS;
	}
	
	public String savePrivilege(){
		this.privilegeService.savePrivilege(rid, checkedStr);
		return SUCCESS;
	}
	//根据用户的id 查找它的权限
	public String showMenuitemTreeByUid(){
		//从session中获取当前的用户
		User user = OAUtils.getUserFromSession();
		Collection<Privilege> privileges = this.privilegeService.getPrivilegesByUid(user.getUid(),user.getUsername());
		ActionContext.getContext().getValueStack().push(privileges);
		return SUCCESS;
	}
 
}

package cn.wang.oaA.struts2.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.domain.system.Privilege;
import cn.wang.oaA.domain.system.User;
import cn.wang.oaA.service.LoginService;
import cn.wang.oaA.service.PrivilegeService;
import cn.wang.oaA.struts2.action.base.BaseAction;
import cn.wang.oaA.util.OAUtils;



@Controller("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction<User>{
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource(name="privilegeService")
	private PrivilegeService privilegeService;
	
	public String login(){
		User user = this.loginService.login(this.getModel().getUsername(), this.getModel().getPassword());
		if(user==null){//用户名或者密码不正确
			this.addActionMessage("用户名或者密码错误");//这个方法就是将错误信息放到集合中然后放到当前的对象栈中
			//用户名或者密码错误是个表单级别的错误不需要定位到具体的文本框
			return "input";
		}else{//用户名和密码正确
			OAUtils.putUserToSession(user);//把 user放入到session中    
			
			Collection<Privilege> privileges = this.privilegeService.getFunctionsByUid(user.getUid());
			//该用户能够访问到的功能权限放入到了session中
			OAUtils.putFunctionsToSession(privileges);
			return "index";
		}
	}
}

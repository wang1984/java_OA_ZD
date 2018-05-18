package cn.wang.oaA.util;

import java.util.Collection;

import org.apache.struts2.ServletActionContext;

import cn.wang.oaA.domain.system.Privilege;
import cn.wang.oaA.domain.system.User;

 

public class OAUtils {
	//把当前用户放到session中
	public static void putUserToSession(User user){
		ServletActionContext.getRequest().getSession().setAttribute("user", user);
	}
	//从session中获取当前用户
	public static User getUserFromSession(){
		return (User)ServletActionContext.getRequest().getSession().getAttribute("user");
	}
	//将功能权限放到sesssion中
	public static void putFunctionsToSession(Collection<Privilege> privileges){
		ServletActionContext.getRequest().getSession().setAttribute("privileges", privileges);
	}
 
}

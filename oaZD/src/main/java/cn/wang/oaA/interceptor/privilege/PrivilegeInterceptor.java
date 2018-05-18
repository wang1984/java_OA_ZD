package cn.wang.oaA.interceptor.privilege;

import java.util.Collection;

import org.apache.struts2.ServletActionContext;

import cn.wang.oaA.annotation.privilege.PrivilegeInfoParse;
import cn.wang.oaA.domain.system.Privilege;
import cn.wang.oaA.domain.system.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 解决权限的拦截器
 * 
 * @author zd
 * 
 */
public class PrivilegeInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		/**
		 * 1、获取session中的用户能够访问的权限
		 * 2、获取当前访问action中的方法的权限
		 * 3、检查该用户是否具有action中的方法的访问权限 前提条件：
		 * 如果用户名称为"admin",则放行 
		 * 如果有 则访问action 
		 * 如果没有则跳转到错误处理页面
		 */
		// 从session中把用户提取出来
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		boolean flag = false;
		if ("admin".equals(user.getUsername())) {
			flag = true;
		} else {
			// 用户能够访问到的权限
			Collection<Privilege> privileges = (Collection<Privilege>) ServletActionContext
					.getRequest().getSession().getAttribute("privileges");

			// 获取当前访问到的action的class形式
			Class classAction = invocation.getAction().getClass();
			// 获取当期访问到的action的方法的名称
			String methodName = invocation.getProxy().getMethod();
			// 获取到当前要访问的action中的方法应该具备的权限
			String privilegeName = PrivilegeInfoParse.parse(classAction,methodName);

			for (Privilege privilege : privileges) {
				if (privilege.getName().equals(privilegeName)) {// 用户所拥有的权限能够访问该方法
					flag = true;
					break;
				}
			}
		}
		if (flag) {// 用户能够访问该方法
			return invocation.invoke();
		} else {
			ActionContext.getContext().getValueStack().push("权限不足，没有办法访问");
			return "privilegeError";
		}
	}

}

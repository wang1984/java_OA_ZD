package cn.wang.oaA.struts2.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.domain.menuitem.Menuitem;
import cn.wang.oaA.service.MenuitemService;
import cn.wang.oaA.struts2.action.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;
 
@Controller("menuitemAction")
@Scope("prototype")
public class MenuitemAction extends BaseAction<Menuitem>{
	@Resource(name="menuitemService")
	private MenuitemService menuitemService;
	
	public String showAllMenuitem(){
		Collection<Menuitem> menuitems = this.menuitemService.getAllMenuitem();
		ActionContext.getContext().getValueStack().push(menuitems);
		return SUCCESS;
	}
}

package cn.wang.oaA.struts2.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.domain.system.Department;
import cn.wang.oaA.domain.system.User;
import cn.wang.oaA.service.DepartmentService;
import cn.wang.oaA.struts2.action.base.BaseAction;

import com.opensymphony.xwork2.ActionContext;

@Controller("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{
	@Resource(name="departmentService")
	private DepartmentService departmentService;	
	
	public String showAllDepartment(){
		Collection<Department> departmentList=this.departmentService.getAllDepartment();
		ActionContext.getContext().put("departmentList", departmentList);//放到了map栈中
		return listAction;
	}
/*演示 list map 迭代 开始*/
	public String showList(){
		Department department  = new Department();
		department.setName("王二麻子");
		List<Department> dList = new ArrayList<Department>();
		dList.add(department);
		ActionContext.getContext().put("dList", dList);
		return listAction;
	}

	public String showMap(){
		Department department  = new Department();
		department.setName("王二麻子");
		Department department2  = new Department();
		department2.setName("王二麻子2");
		Map<String, Department> map = new HashMap<String, Department>();
		map.put("d1",department);
		map.put("d2",department2);
		ActionContext.getContext().put("map", map);
		return listAction;
	}
	
	public String showListMap(){
		List<Map<String, Department>> list = new ArrayList<Map<String,Department>>();
		Map<String, Department> map = new HashMap<String, Department>();
		Department department  = new Department();
		department.setName("王二麻子");
		map.put("d1", department);
		list.add(map);
		ActionContext.getContext().put("listMap", list);
		return listAction;
	}
	
	public String showMapList(){
		Map<String, List<Department>> map = new HashMap<String, List<Department>>();
		List<Department> list = new ArrayList<Department>();
		Department department  = new Department();
		department.setName("王二麻子");
		list.add(department);
		map.put("list1", list);
		ActionContext.getContext().put("mapList", map);
		return listAction;
	}
	
	public String showListMapList(){
		List<Map<String, List<Department>>> list = new ArrayList<Map<String,List<Department>>>();
		
		Department department  = new Department();
		department.setName("王二麻子");
		List<Department> dList = new ArrayList<Department>();
		dList.add(department);
		
		Map<String, List<Department>> map = new HashMap<String, List<Department>>();
		map.put("map1", dList);
		list.add(map);
		ActionContext.getContext().put("listMapList", list);
		return listAction;
	}
/*演示 list map 迭代 结束*/	
	
	public String addUI(){
		return addUI;
	}

	public String add(){
		
		// this.departmentService.saveDepartment(this.getModel());
		/*
		 * 上面这种方式不妥
		 * 一般情况下不要把模型驱动传递到dao层，不要让模型驱动和dao交互
		 */
		
		Department department = new Department();
		/**
		 * 把model的值赋值给了department
		 */
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.saveDepartment(department);
		return action2action;
	}
	
	public String delete(){
		/**
		 * 1、提取department对象
		 * 2、获取该部门的所有的用户
		 */
		Department department = this.departmentService.getDepartmentById(this.getModel().getDid());
		
		//<!-- inverse="true" 表示不维护关系 --> 在这里我们手动维护关系
		//提取该部门的用户
		Set<User> users = department.getUsers();
		for(User user:users){
			user.setDepartment(null);//解除user和department之间的关系
		}
		this.departmentService.deleteDepartment(this.getModel().getDid());
		return action2action;
	}
	
	public String updateUI(){
		Department department = this.departmentService.getDepartmentById(this.getModel().getDid());
		//回显 这里利用对象栈回显
		ActionContext.getContext().getValueStack().push(department);
		//ActionContext.getContext().getValueStack().setParameter("name", "bbbb");
		return updateUI;
	}
	
	public String update(){
		Department department =  this.departmentService.getDepartmentById(this.getModel().getDid());
		BeanUtils.copyProperties(this.getModel(), department);
		this.departmentService.updateDepartment(department);
		return action2action;
	}
}

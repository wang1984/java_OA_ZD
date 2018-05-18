package cn.wang.oaA.service.impl;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wang.oaA.dao.FormDao;
import cn.wang.oaA.dao.FormTemplateDao;
import cn.wang.oaA.domain.system.User;
import cn.wang.oaA.domain.workflow.Form;
import cn.wang.oaA.domain.workflow.FormTemplate;
import cn.wang.oaA.service.WorkFlowService;
import cn.wang.oaA.util.OAUtils;
import cn.wang.oaA.util.UploadUtils;

@Service("workFlowService")
public class WorkFlowServiceImpl implements WorkFlowService{
	@Resource(name="formTemplateDao")
	private FormTemplateDao formTemplateDao;
	
	@Resource(name="processEngine")
	private ProcessEngine processEngine;
	
	@Resource(name="formDao")
	private FormDao formDao;
	
	@Transactional(readOnly=false)
	public void submit(File resource,Long ftid,String processKey) {
		/**
		 * 参数的作用
		 * File resource  上传的模板
		 * Long ftid      模板的id   
		 * String processKey  流程名称 pdkey 用于启动流程实例
		 * 
		 * 1、上传form表单
		 * 2、保存form表的数据
		 * 3、启动流程实例
		 * 4、完成任务
		 * 自己的api和jbpm的api要在同一个事务中，所以利用了事务的传播性
		 */
		//完成文件的上传
		String url = UploadUtils.saveUploadFile(resource);
		
		//form对象填充值
		Form form = new Form();
		User user = OAUtils.getUserFromSession();
		form.setApplicator(user.getUsername());
		Date time = new Date();
		form.setApplictetime(time);
		FormTemplate formTemplate = this.formTemplateDao.getEntryById(ftid);
		form.setFormTemplate(formTemplate);
		form.setState("申请中");
		form.setTitle(formTemplate.getName()+"_"+user.getUsername()+"_"+new SimpleDateFormat("yyyy-MM-dd").format(time));
		form.setUrl(url);
		/*
		 * 如果formDao.saveEntry(form)保存这一步放在最后执行会报错 
		 * 启动流程实例的时候将form对象放到了流程变量中（jbpm会认为form是一个持久化类）
		 * 但是此时form只是一个临时状态对象
		 * 放在流程实例中的流程变量form必须是持久化数据
		 * 如何让它成为持久化对象？
		 * 在放入流程变量之前保存
		 */
		this.formDao.saveEntry(form);
		
		//启动流程实例
		//放在流程实例中的流程变量form必须是持久化数据
		Map<String, Form> variables = new HashMap<String, Form>();
		variables.put("form", form);
		ProcessInstance pi = this.processEngine.getExecutionService()
		.startProcessInstanceByKey(processKey, variables);
		
		form.setPiid(pi.getId());//从返回的流程实例中获取piid,将此piid放入 form流程变量中
		
		//完成请假申请任务
		//根据piid获取该流程实例当前正在执行的任务
		Task task = this.processEngine.getTaskService()
		.createTaskQuery()
		.executionId(pi.getId())
		.uniqueResult();
		this.processEngine.getTaskService()
		.completeTask(task.getId());
	 
	}

	/**
	 * 如果用该方案,页面显示数据
	 *    <s:property value="title"/>
	 */
	@Override
	public Collection<Form> myTaskList() {
		/**
		 * 根据当前的登录人查询出正在执行的任务
		 */
		String username = OAUtils.getUserFromSession().getUsername();
		List<Task> taskList = this.processEngine
		.getTaskService()
		.createTaskQuery()
		.assignee("张三")//测试 请假单jbpm 经理审批是张三 写死的  这里应该用  username
		.list();
		//根据当前正在执行的任务查询出form表单
		Collection<Form> list = new ArrayList<Form>();
		for(Task task:taskList){
			Form form = (Form)this.processEngine.getExecutionService()
			.getVariable(task.getExecutionId(), "form");
			list.add(form);
		}
		return list;
	}

	/**
	 * 页面上显示数据
	 * 	<s:prototype value="form.title"/>
	 *  <s:prototype value="task.id"/>
	 *//*
	@Override
	public Collection<TaskView> myTaskList2() {
		String username = OAUtils.getUserFromSession().getUsername();
		List<Task> taskList = this.processEngine
		.getTaskService()
		.createTaskQuery()
		.assignee(username)
		.list();
		List<TaskView> taskViews = new ArrayList<TaskView>();
		for(Task task:taskList){
			//从当前的流程实例中把流程变量"form"提取出来
			Form form = (Form)this.processEngine.getExecutionService()
					.getVariable(task.getExecutionId(), "form");
			TaskView taskView = new TaskView();
			taskView.setForm(form);
			taskView.setTask(task);
			taskViews.add(taskView);
		}
		return taskViews;
	}*/

	@Override
	public void approve() {
		/**
		 * 如果在页面上点击的是不同意，则流程实例直接结束
		 *    改变相应的form表中state字段的值为"未通过"
		 * 如果页面上点击的是同意
		 * 	  1、完成任务
		 * 	  2、判断当前的流程实例是否已经结束
		 *        如果没有结束，则什么都不做
		 *        如果结束，改变form表中相应的state字段的值"已通过"
		 * 
		 * 在approve表中插入一行数据
		 */
		
		
		/**
		 * 如果值传递一个fid （针对第一种展示待审批的form表单的方式）
		 *    1、根据fid把form对象提取出来
		 *    2、根据form对象获取到piid
		 *    3、根据piid把当前流程实例正在执行的任务提取出来
		 *    4、根据任务可以得到taskId
		 *    5、完成任务
		 * 如果值传递两个参数:fid,taskid （针对第二种展示待审批的form表单的方式）
		 *    这样可以根据taskId直接完成任务
		 */
		
		/**
		 * 页面上传递过来一个字符串：是否同意
		 */
		String isapprove = "";
		if(isapprove.equals("同意")){
			//完成任务
			//判断流程实例是否结束
			    //如果结束，则改变form表中相应的行的state状态的值
		}else{
			//直接结束流程实例
			//改变form表的状态
		}
		
		//往approve表中插入一行数据
		
		
		
		/**
		 * 在审批处理的页面有两个button,分别是同意和不同意
		 * 	<input type="image" src="../style/blue/images/button/agree.png"/>
			<input type="image" src="../style/blue/images/button/disagree.png"/>
			1、在页面上创建一个隐藏域
			2、在页面上分别对这两个button添加click事件
			3、根据不同的click事件，对隐藏域赋值不同的值
			4、action端可以根据其值来判断是同意还是不同意
		 */
	}
}

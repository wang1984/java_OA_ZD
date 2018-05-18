package cn.wang.oaA.struts2.action;


import java.io.File;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.domain.workflow.Form;
import cn.wang.oaA.domain.workflow.FormTemplate;
import cn.wang.oaA.service.FormTemplateService;
import cn.wang.oaA.service.WorkFlowService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("workFlowAction")
@Scope("prototype")
public class WorkFlowAction extends ActionSupport{
	@Resource(name="formTemplateService")
	private FormTemplateService formTemplateService;
	
	private File resource;
	
	private Long ftid;
	
	private String processKey;
	
	
	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public Long getFtid() {
		return ftid;
	}

	public void setFtid(Long ftid) {
		this.ftid = ftid;
	}

	

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	@Resource(name="workFlowService")
	private WorkFlowService workFlowService;
	/**
	 * 跳转到表单模板的列表页面
	 */
	public String showFormTemplate(){
		Collection<FormTemplate> ftList = this.formTemplateService.getAllFormTemplate();
		ActionContext.getContext().put("ftList", ftList);
		return "formTemplateList";
	}
	
	/**
	 * 跳转到提交申请的页面
	 */
	public String submitUI(){
		return "submitUI";
	}
	
	/**
	 * 提交申请的动作
	 */
	public String submit(){
		this.workFlowService.submit(resource, ftid, processKey);
		return null;
	}
	
	/**
	 * 跳转到审批申请页面
	 */
	public String showMyTaskList(){
		Collection<Form> taskList=this.workFlowService.myTaskList();
		ActionContext.getContext().put("taskList", taskList);
		return "taskList";
	}
	
	/**
	 * 跳转到审批处理页面
	 */
	
	/**
	 * 审批处理动作
	 */
	 
}

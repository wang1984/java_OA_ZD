package cn.wang.oaA.struts2.action;


import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.domain.workflow.FormTemplate;
import cn.wang.oaA.service.FormTemplateService;
import cn.wang.oaA.service.PDManager;
import cn.wang.oaA.struts2.action.base.BaseAction;
import cn.wang.oaA.util.UploadUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller("formTemplateAction")
@Scope("prototype")
public class FormTemplateAction extends BaseAction<FormTemplate>{
	
	private File resource;
	
	private InputStream inputStream;
	
	
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	@Resource(name="formTemplateService")
	private FormTemplateService formTemplateService;
	
	@Resource(name="pdManager")
	private PDManager pdManager;
	
	public String showAllFormTemplate(){
		Collection<FormTemplate> ftList = this.formTemplateService.getAllFormTemplate();
		ActionContext.getContext().put("ftList", ftList);
		return "listAction";
	}
	
	/**
	 * 跳转到表单模板的添加页面
	 * 添加页面需要选择该表单模板所属的流程定义所以要把所有的流程定义查出来
	 */
	public String addUI(){
		Collection<ProcessDefinition> pdList = this.pdManager.getLasterVersion();
		ActionContext.getContext().put("pdList", pdList);
		return ADD_UI;
	}
	
	public String add(){
		/**
		 * 1、把表单模板上传到服务器上
		 * 2、在表单模板中插入一行数据
		 */
		String url = UploadUtils.saveUploadFile(this.resource);//将文件保存到服务器并返回文件在服务器上的绝对路径
		FormTemplate formTemplate = new FormTemplate();
		formTemplate.setName(this.getModel().getName());
		formTemplate.setProcessKey(this.getModel().getProcessKey());
		formTemplate.setUrl(url);
		this.formTemplateService.saveFormTemplate(formTemplate);
		return action2action;
	}
	
	/**
	 * 下载
	 */
	public String download() throws Exception{
	
		this.inputStream = this.formTemplateService.download(this.getModel().getFtid());
		return "download";
	}
}

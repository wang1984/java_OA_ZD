package cn.wang.oaA.struts2.action;


import java.io.File;
import java.io.InputStream;
import java.util.Collection;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.service.PDManager;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("pdManagerAction")
@Scope("prototype")
public class PDManagerAction extends ActionSupport{
	@Resource(name="pdManager")
	private PDManager pdManager;
	private File resource;
	private String key;	
	private String deploymentId;
	
	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	private InputStream inputStream;
	
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public File getResource() {
		return resource;
	}

	public void setResource(File resource) {
		this.resource = resource;
	}

	public String showLasterVersions(){
		Collection<ProcessDefinition> pdList = this.pdManager.getLasterVersion();
		ActionContext.getContext().put("pdList", pdList);
		return "pdList";
	}
	
	/**
	 * 跳转到流程定义的部署的页面
	 */
	public String deployUI(){
		return "deployUI";
	}
	
	/**
	 * 部署
	 */
	public String deploy() throws Exception{
		this.pdManager.deploy(this.resource);
		return "action2action";
	}
	/**
	 * 删除
	 */
	public String deletePD(){
		this.pdManager.deletePD(key);
		return "action2action";
	}
	/**
	 * 展示流程图
	 */
	public String showImage(){
		this.inputStream = this.pdManager.showImage(deploymentId);
		return SUCCESS;
	}
}

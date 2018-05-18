package cn.wang.oaA.domain.workflow;

import java.io.Serializable;
import java.util.Set;

public class FormTemplate implements Serializable{
	private Long ftid;
	private String name;
	private String processKey;//流程定义的key
	private String url;//模板保存的路径
	
	private Set<Form> forms;

	public Long getFtid() {
		return ftid;
	}

	public void setFtid(Long ftid) {
		this.ftid = ftid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<Form> getForms() {
		return forms;
	}

	public void setForms(Set<Form> forms) {
		this.forms = forms;
	}
}

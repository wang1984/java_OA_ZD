package cn.wang.oaA.domain.workflow;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Form implements Serializable{
	private Long fid;
	private String title;
	private String applicator;//申请人
	private Date applictetime;//申请日期
	private String state;//状态
	private String url;
	
	private Set<Approve> approves;
	/**
	 * 该字段是系统内部的表和jbpm的表连接的一个桥梁
	 */
	private String piid;//流程实例ID
	
	private FormTemplate formTemplate;
	
	
	public Set<Approve> getApproves() {
		return approves;
	}

	public void setApproves(Set<Approve> approves) {
		this.approves = approves;
	}

	public String getPiid() {
		return piid;
	}

	public void setPiid(String piid) {
		this.piid = piid;
	}

	

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getApplicator() {
		return applicator;
	}

	public void setApplicator(String applicator) {
		this.applicator = applicator;
	}

	public Date getApplictetime() {
		return applictetime;
	}

	public void setApplictetime(Date applictetime) {
		this.applictetime = applictetime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public FormTemplate getFormTemplate() {
		return formTemplate;
	}

	public void setFormTemplate(FormTemplate formTemplate) {
		this.formTemplate = formTemplate;
	}
}

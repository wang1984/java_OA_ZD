package cn.wang.oaA.domain.workflow;



import java.io.Serializable;
import java.util.Date;

public class Approve implements Serializable{
	private Long aid;
	private String approvename;//审批人
	private Date approvetime;//审批时间
	private Boolean isapprove;//是否通过
	
	private Form form;

	public Long getAid() {
		return aid;
	}

	public void setAid(Long aid) {
		this.aid = aid;
	}

	public String getApprovename() {
		return approvename;
	}

	public void setApprovename(String approvename) {
		this.approvename = approvename;
	}

	public Date getApprovetime() {
		return approvetime;
	}

	public void setApprovetime(Date approvetime) {
		this.approvetime = approvetime;
	}

	public Boolean getIsapprove() {
		return isapprove;
	}

	public void setIsapprove(Boolean isapprove) {
		this.isapprove = isapprove;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}
}

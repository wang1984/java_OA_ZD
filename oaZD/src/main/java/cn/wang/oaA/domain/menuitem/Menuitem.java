package cn.wang.oaA.domain.menuitem;

import java.io.Serializable;

public class Menuitem implements Serializable{
	private Long mid;//主键，树的唯一标示
	private Long pid;//父节点
	private String name;//树的名称
	private Boolean isParent;//是否为父节点
	private String icon;//图标的路径
	
	public Long getMid() {
		return mid;
	}
	public void setMid(Long mid) {
		this.mid = mid;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
}

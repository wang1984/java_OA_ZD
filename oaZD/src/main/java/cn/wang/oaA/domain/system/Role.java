package cn.wang.oaA.domain.system;

import java.io.Serializable;
import java.util.Set;

 
public class Role implements Serializable{
	private Long rid;
	private String name;
	private String description;
	private Set<User> users;//懒加载
	private Long pid;//父节点的id
	
	private Boolean checked; //是否选中
	private Set<Privilege> privileges;
	 
    
	
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public Long getPid() {
		return pid;
	}
	 
	public void setPid(Long pid) {
		this.pid = pid;
	}
	 
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	 
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	
	
	public Long getRid() {
		return rid;
	}
	public void setRid(Long rid) {
		this.rid = rid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}

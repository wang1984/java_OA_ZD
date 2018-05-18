package cn.wang.oaA.domain.system;

import java.io.Serializable;
import java.util.Set;
 
public class Department implements Serializable{
	private Long did;//标示符
	private String name;//部门名称
	private String description;//职能说明

	private Set<User> users;//懒加载

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
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

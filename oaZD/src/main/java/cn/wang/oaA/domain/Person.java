package cn.wang.oaA.domain;

import java.io.Serializable;

public class Person implements Serializable{
	private Long pid;//标识符属性 便于在对象缓存中根据标识符属性识别缓存中的对象
	/*
	 * 对象缓存     一级缓存  二级缓存
	 * 数据缓存    查询缓存 （属性缓存）
	 */
	private String name;
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
	
}

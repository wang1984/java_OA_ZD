package cn.wang.oaA.service;

import cn.wang.oaA.domain.system.User;
 
public interface LoginService {
	public User login(String username,String password);
}

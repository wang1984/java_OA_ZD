package cn.wang.oaA.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.wang.oaA.dao.LoginDao;
import cn.wang.oaA.domain.system.User;
import cn.wang.oaA.service.LoginService;
 
@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Resource(name="loginDao")
	private LoginDao loginDao;
	@Override
	public User login(String username, String password) {
	 
		return this.loginDao.getEntryByCondition("from User where username=? and password=?", username,password);
	}
}

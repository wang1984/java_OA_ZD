package cn.wang.oaA.test;

import org.junit.Test;

import cn.wang.oaA.dao.LoginDao;
import cn.wang.oaA.domain.system.User;


public class LoginTest extends SpringUtils{
	@Test
	public void testLogin(){
		LoginDao loginDao = (LoginDao)context.getBean("loginDao");
		User user = loginDao.getEntryByCondition("from User where username=? and password=?", "admin","admin");
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
	}
}

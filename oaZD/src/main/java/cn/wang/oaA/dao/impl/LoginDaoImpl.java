package cn.wang.oaA.dao.impl;

import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.LoginDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.system.User;


@Repository("loginDao")
public class LoginDaoImpl extends BaseDaoImpl<User> implements LoginDao{
	
}

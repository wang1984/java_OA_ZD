package cn.wang.oaA.dao.impl;

import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.UserDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.system.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

}

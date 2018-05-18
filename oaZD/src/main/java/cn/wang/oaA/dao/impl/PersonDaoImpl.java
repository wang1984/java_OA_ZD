package cn.wang.oaA.dao.impl;

import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.PersonDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.Person;

@Repository("personDao") 
public class PersonDaoImpl extends  BaseDaoImpl<Person> implements PersonDao{
	
}

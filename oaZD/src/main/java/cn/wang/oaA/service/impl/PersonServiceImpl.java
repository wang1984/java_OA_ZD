package cn.wang.oaA.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wang.oaA.dao.PersonDao;
import cn.wang.oaA.domain.Person;
import cn.wang.oaA.service.PersonService;
@Service("personService")
public class PersonServiceImpl implements PersonService{
	@Resource(name="personDao")
	private PersonDao personDao;
	
	public PersonDao getPersonDao() {
		return personDao;
	}
    
	@Transactional(readOnly=false)
	@Override
	public void savePerson(Person person) {  
		this.personDao.saveEntry(person);
	}
}

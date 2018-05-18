package cn.wang.oaA.test;

import org.junit.Test;

import cn.wang.oaA.service.PersonService;

public class PersonTest extends SpringUtils{
	@Test
	public void testPersonService(){
		PersonService personService = (PersonService) context.getBean("personService");
		System.out.println(personService);
	}
}

package cn.wang.oaA.struts2.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wang.oaA.domain.Person;
import cn.wang.oaA.service.PersonService;
import cn.wang.oaA.struts2.action.base.BaseAction;

import com.opensymphony.xwork2.ActionSupport;
@Controller("personAction")
@Scope("prototype")
public class PersonAction extends BaseAction<Person>{
	@Resource(name="personService")
	private PersonService personService;
 
	
	public String savePerson(){
	  Person person = new Person();
	  person.setName("王二麻子7");
	  this.personService.savePerson(person);
	  return null;
	}
}

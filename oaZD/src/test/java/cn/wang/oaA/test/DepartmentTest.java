package cn.wang.oaA.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.wang.oaA.domain.system.Department;
import cn.wang.oaA.service.DepartmentService;
 
public class DepartmentTest {
	@Test
	public void testSessionFactory(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		DepartmentService departmentService = (DepartmentService) context.getBean("departmentService");
		Department department = new Department();
		department.setName("公关部");
		department.setDescription("美女多");
		departmentService.saveDepartment(department);
	}
	
	//hibernate的一级缓存中不允许出现相同的标识符
	@Test
	public void test(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Department department = (Department)session.get(Department.class, 1L);//进入一级缓存中
		
		Department department2 = new Department();
		department2.setDid(1L);
		department2.setDescription("aaaa");
		
		session.update(department2);//进入一级缓存中
		transaction.commit();
		session.close();
	}
}

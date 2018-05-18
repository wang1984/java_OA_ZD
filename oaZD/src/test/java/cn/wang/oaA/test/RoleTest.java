package cn.wang.oaA.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import cn.wang.oaA.domain.system.Privilege;
import cn.wang.oaA.domain.system.Role;
import cn.wang.oaA.service.RoleService;



public class RoleTest extends SpringUtils{
	@Test
	public void testSaveRole(){
		RoleService roleService = (RoleService)context.getBean("roleService");
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		/**
		 * 用户角色、岗位角色、部门角色
		 */
		Role role1 = new Role();
		role1.setName("用户角色");
		role1.setDescription("拥有用户模块的crud的操作");
		//建立角色和权限之间的关联(不在这里建立关联)
		//List<Privilege> privileges1 = session.createQuery("from Privilege where id in(38,39,40,41)").list();
		//role1.setPrivileges(new HashSet<Privilege>(privileges1));
		roleService.saveRole(role1);
		
		Role role2 = new Role();
		role2.setName("部门角色");
		role2.setDescription("拥有部门模块的crud的操作");
		//建立角色和权限之间的关联(不在这里建立关联)
		//List<Privilege> privileges2 = session.createQuery("from Privilege where id in(42,44,45,46)").list();
		//role2.setPrivileges(new HashSet<Privilege>(privileges2));
		roleService.saveRole(role2);
		
		Role role3 = new Role();
		role3.setName("岗位角色");
		role3.setDescription("拥有岗位模块的crud的操作");
		//建立角色和权限之间的关联(不在这里建立关联)
		//List<Privilege> privileges3 = session.createQuery("from Privilege where id in(43,47,48,49)").list();
		//role3.setPrivileges(new HashSet<Privilege>(privileges3));
		roleService.saveRole(role3);
		
	}
}

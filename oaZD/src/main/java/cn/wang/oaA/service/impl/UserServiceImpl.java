package cn.wang.oaA.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wang.oaA.dao.DepartmentDao;
import cn.wang.oaA.dao.RoleDao;
import cn.wang.oaA.dao.UserDao;
import cn.wang.oaA.domain.system.Department;
import cn.wang.oaA.domain.system.Role;
import cn.wang.oaA.domain.system.User;
import cn.wang.oaA.service.UserService;

 
@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name="userDao")
	private UserDao userDao;
	
	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	
	@Resource(name="roleDao")
	private RoleDao roleDao;

	@Override
	public Collection<User> getAllUser() {
		
		return this.userDao.getAllEntry();
	}

	@Transactional(readOnly=false)
	public void saveUser(Long did,Long[] rids,User user) {
		/**
		 * 1、建立用户和部门之间的关系
		 * 2、建立用户和岗位之间的关系
		 *     用户来维护用户和部门之间的关系
		 *     用户来维护用户和岗位之间的关系
		 */
		Department department = this.departmentDao.getEntryById(did);
		//建立用户和部门之间的关系
		user.setDepartment(department);
		
		Set<Role> roles = this.roleDao.getEntrysByIDS(rids);
		//建立用户和岗位之间的关系
		user.setRoles(roles);
		
		this.userDao.saveEntry(user);
	}

	@Transactional(readOnly=false)
	public void updateUser(Long did,Long[] rids,User user) {
		
		Department department = this.departmentDao.getEntryById(did);
		//重新建立用户和部门之间的关系
		user.setDepartment(department);
		
		Set<Role> roles = this.roleDao.getEntrysByIDS(rids);
		//重新建立用户和岗位之间的关系
		user.setRoles(roles);
		
		this.userDao.updateEntry(user);
	}
    
	@Transactional(readOnly=false)
	public void updateUser(User user) {
		
		this.userDao.updateEntry(user);
	}
	
	@Transactional(readOnly=false)
	public void deleteUser(Serializable id) {
		
		this.userDao.deleteEntry(id);
	}

	@Override
	public User getUserById(Serializable id) {
		
		return this.userDao.getEntryById(id);
	}
	
	
}

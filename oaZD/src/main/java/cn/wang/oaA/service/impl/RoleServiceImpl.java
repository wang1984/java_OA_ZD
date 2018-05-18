package cn.wang.oaA.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wang.oaA.dao.RoleDao;
import cn.wang.oaA.domain.system.Role;
import cn.wang.oaA.service.RoleService;
 
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Resource(name="roleDao")
	private RoleDao roleDao;

	@Override
	public Collection<Role> getAllRole(){
		 //int a=1/0;
		 //throw new RuntimeException("测试错误处理");
		return this.roleDao.getAllEntry();
	}

	@Override
	public Role getRoleById(Serializable id) {
		
		return this.roleDao.getEntryById(id);
	}

	@Transactional(readOnly=false)
	public void updateRole(Role role) {
		
		this.roleDao.updateEntry(role);
	}

	@Transactional(readOnly=false)
	public void deleteRole(Serializable id) {
		
		this.roleDao.deleteEntry(id);
	}

	@Transactional(readOnly=false)
	public void saveRole(Role role) {
		
		this.roleDao.saveEntry(role);
	}

	@Override
	public Collection<Role> getRolesByUid(Long uid) {
		
		return this.roleDao.getRoles(uid);
	}

	@Override
	public Set<Role> getRolesByIds(String ids) {
		 
		return this.roleDao.getEntrysByIDS(ids);
	}
}

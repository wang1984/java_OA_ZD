package cn.wang.oaA.service.impl;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wang.oaA.dao.PrivilegeDao;
import cn.wang.oaA.dao.RoleDao;
import cn.wang.oaA.domain.system.Privilege;
import cn.wang.oaA.domain.system.Role;
import cn.wang.oaA.service.PrivilegeService;

@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService{
	@Resource(name="privilegeDao")
	private PrivilegeDao privilegeDao;
	
	@Resource(name="roleDao")
	private RoleDao roleDao;

	@Override
	public Collection<Privilege> getPrivilegesByRid(Long rid) {
		return this.privilegeDao.getPrivilegesByRid(rid);
	}

	@Transactional(readOnly=false)
	public void savePrivilege(Long rid, String checkedStr) {
		
		Role role = this.roleDao.getEntryById(rid);
		Set<Privilege> privileges = this.privilegeDao.getEntrysByIDS(checkedStr);
		//建立角色和权限之间的关系
		 
		if(privileges.isEmpty()){
			role.setPrivileges(null);
		}else
		{
			role.setPrivileges(privileges);
		}
		 
		//更新角色，hibernate内部自动维护角色和权限之间的关系
		this.roleDao.updateEntry(role);
	}

	@Override
	public Collection<Privilege> getPrivilegesByUid(Long uid,String username) {
	 
		return this.privilegeDao.getMenuitemsByUid(uid,username);
	}	
	
	@Override
	public Collection<Privilege> getFunctionsByUid(Long uid) {
		
		return this.privilegeDao.getFunctionsByUid(uid);
	}	
}

package cn.wang.oaA.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import cn.wang.oaA.domain.system.Role;
 
public interface RoleService {
	public Collection<Role> getAllRole();
	
	public Role getRoleById(Serializable id);
	
	public void updateRole(Role role);
	
	public void deleteRole(Serializable id);
	
	public void saveRole(Role role);
	
	public Collection<Role> getRolesByUid(Long uid);
	
	public Set<Role> getRolesByIds(String ids);
}

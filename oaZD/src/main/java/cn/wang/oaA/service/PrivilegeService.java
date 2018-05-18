package cn.wang.oaA.service;

import java.util.Collection;

import cn.wang.oaA.domain.system.Privilege;

 

public interface PrivilegeService {
	public Collection<Privilege> getPrivilegesByRid(Long rid);
	
	public void savePrivilege(Long rid,String checkedStr);
	
	public Collection<Privilege> getPrivilegesByUid(Long uid,String username);
	public Collection<Privilege> getFunctionsByUid(Long uid);
}

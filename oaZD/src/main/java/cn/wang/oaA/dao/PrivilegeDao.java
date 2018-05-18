package cn.wang.oaA.dao;

import java.util.Collection;

import cn.wang.oaA.dao.base.BaseDao;
import cn.wang.oaA.domain.system.Privilege;

 

public interface PrivilegeDao extends BaseDao<Privilege>{
	public Collection<Privilege> getPrivilegesByRid(Long rid);
	
	public Collection<Privilege> getMenuitemsByUid(Long uid,String username);
	
	/**
	 * 根据用户ID得到该用户的功能权限
	 * @param uid
	 * @return
	 */
	public Collection<Privilege> getFunctionsByUid(Long uid);
}

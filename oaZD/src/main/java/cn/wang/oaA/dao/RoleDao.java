package cn.wang.oaA.dao;

import java.util.Collection;

import cn.wang.oaA.dao.base.BaseDao;
import cn.wang.oaA.domain.system.Role;
 
public interface RoleDao extends BaseDao<Role>{
	public Collection<Role> getRoles(Long uid);
}

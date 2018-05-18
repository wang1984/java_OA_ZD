package cn.wang.oaA.dao.impl;

import java.util.Collection;

import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.RoleDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.system.Role;
  
@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao{
	@Override
	public Collection<Role> getRoles(Long uid) {
		/**
		 * 1、把所有的角色全部提取出来  allRoles
		 * 2、把用户能够访问到的角色提取出来  userRoles
		 * 3、遍历循环所有的角色 
		 *      再遍历用户能够访问到的角色
		 *          如果正在遍历的所有的角色当中的某一个角色正好是用户能够访问到的，那么设置checked为true
		 */
		Collection<Role> allRoles = this.getAllEntry();
		
		//用户能够访问到的角色 是内连接
		Collection<Role> userRoles = this.hibernateTemplate.find("from Role r inner join fetch r.users u where u.uid=?",uid);
		
		for(Role role:allRoles){//遍历所有的role
			for(Role role2:userRoles){//遍历用户能够访问到的role
				if(role.getRid().longValue()==role2.getRid().longValue()){
					role.setChecked(true);
					break;
				}
			}
		}
		return allRoles;
	}
}

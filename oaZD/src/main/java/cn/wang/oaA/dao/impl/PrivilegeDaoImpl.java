package cn.wang.oaA.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.PrivilegeDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.system.Privilege;


@Repository("privilegeDao")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao{
	/**
	 * 1、把所有的权限全部提取出来  allPrivilege
	 * 2、把角色能够访问到的权限提取出来  rolePrivilege
	 * 3、遍历循环所有的权限
	 *      再遍历角色能够访问到的角色
	 *          如果正在遍历的所有的权限当中的某一个权限正好是角色能够访问到的，那么设置checked为true
	 */
	@Override
	public Collection<Privilege> getPrivilegesByRid(Long rid) {
		Collection<Privilege> allPrivilege = this.getAllEntry();
		Collection<Privilege> rolePrivilege = this.hibernateTemplate.find("from Privilege p inner join fetch p.roles r where r.rid=?",rid);
		for(Privilege privilege:allPrivilege){
			for(Privilege privilege2:rolePrivilege){
				if(privilege.getId().longValue()==privilege2.getId().longValue()){
					privilege.setChecked(true);
					break;
				}
			}
		}
		return allPrivilege;
	}

	@Override
	public Collection<Privilege> getMenuitemsByUid(Long uid,String username) {
		List<Privilege> privileges = null;
		if("admin".equals(username)){//如果是超级管理员就有所有的权限菜单
			privileges = this.hibernateTemplate.find("from Privilege where flag='1'");
		}else{
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("from Privilege p inner join fetch p.roles r inner join fetch r.users u");
			stringBuffer.append(" where u.uid=?");
			stringBuffer.append(" and flag='1'");
			privileges = this.hibernateTemplate.find(stringBuffer.toString(),uid);
		}
		//return new HashSet<Privilege>(privileges);//去重（当一个用户有多个角色的时候，会有多个重复的权限）
		
		Set set = new  HashSet(); 
        List newList = new  ArrayList(); 
        for (Privilege cd : privileges) {
           if(set.add(cd)){
               newList.add(cd);
           }
        }
        return newList;//去重（当一个用户有多个角色的时候，会有多个重复的权限）  不打乱顺序
		
	}
	
	@Override
	public Collection<Privilege> getFunctionsByUid(Long uid) {
		List<Privilege> privileges = null;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("from Privilege p inner join fetch p.roles r inner join fetch r.users u");
		stringBuffer.append(" where u.uid=?");
		stringBuffer.append(" and flag='2'");
		privileges = this.hibernateTemplate.find(stringBuffer.toString(),uid);
		
		//return new HashSet<Privilege>(privileges);
		
		Set set = new HashSet(); 
        List newList = new  ArrayList(); 
        for (Privilege cd : privileges) {
           if(set.add(cd)){
               newList.add(cd);
           }
        }
        return newList;//去重（当一个用户有多个角色的时候，会有多个重复的权限）  不打乱顺序
		
		
	}
}

package cn.wang.oaA.dao.impl;

import org.springframework.stereotype.Repository;

import cn.wang.oaA.dao.DepartmentDao;
import cn.wang.oaA.dao.base.impl.BaseDaoImpl;
import cn.wang.oaA.domain.system.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao{

}

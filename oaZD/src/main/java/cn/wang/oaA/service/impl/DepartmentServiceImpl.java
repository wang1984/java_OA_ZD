package cn.wang.oaA.service.impl;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wang.oaA.dao.DepartmentDao;
import cn.wang.oaA.domain.system.Department;
import cn.wang.oaA.service.DepartmentService;
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService{

	@Resource(name="departmentDao")
	private DepartmentDao departmentDao;
	
	@Override
	public Collection<Department> getAllDepartment() {
		 
		return this.departmentDao.getAllEntry();
	}
	
	@Transactional(readOnly=false)
	public void saveDepartment(Department department) {
		this.departmentDao.saveEntry(department);
	}
	
	@Transactional(readOnly=false)
	public void deleteDepartment(Long id) {
		 
		this.departmentDao.deleteEntry(id);
	}
	@Override
	public Department getDepartmentById(Serializable id) {
		 
		return this.departmentDao.getEntryById(id);
	}
	@Transactional(readOnly=false)
	public void updateDepartment(Department department) {
		 
		this.departmentDao.updateEntry(department);
	}
}

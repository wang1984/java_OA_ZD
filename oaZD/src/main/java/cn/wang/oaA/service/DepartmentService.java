package cn.wang.oaA.service;

import java.io.Serializable;
import java.util.Collection;

import cn.wang.oaA.domain.system.Department;
 
public interface DepartmentService {
	public Collection<Department> getAllDepartment();
	
	public void saveDepartment(Department department);
    
	public void deleteDepartment(Long id);
	
	public Department getDepartmentById(Serializable id);
	
	public void updateDepartment(Department department);
}

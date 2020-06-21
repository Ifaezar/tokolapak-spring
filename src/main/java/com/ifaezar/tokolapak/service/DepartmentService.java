package com.ifaezar.tokolapak.service;

import com.ifaezar.tokolapak.entity.Department;

public interface DepartmentService {
	
	public Iterable<Department> getAllDepartments();
	public Department AddDeparment(Department department);
}

package com.ifaezar.tokolapak.service.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifaezar.tokolapak.dao.DepartmnetRepo;
import com.ifaezar.tokolapak.entity.Department;
import com.ifaezar.tokolapak.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmnetRepo departmentRepo;
	
	@Override	
	@Transactional
	public Iterable<Department> getAllDepartments() {
		return departmentRepo.findAll();
	}

	@Override
	@Transactional
	public Department AddDeparment(Department department) {
		department.setId(0);
		return departmentRepo.save(department);
	}
	
	
	
	
}

package com.ifaezar.tokolapak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifaezar.tokolapak.dao.DepartmnetRepo;
import com.ifaezar.tokolapak.dao.EmployeeRepo;
import com.ifaezar.tokolapak.entity.Department;
import com.ifaezar.tokolapak.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DepartmnetRepo departmentRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@GetMapping
	public Iterable<Department> getAllDepartments(){
		return departmentService.getAllDepartments();
	}
	
	@PostMapping
	public Department addDepartmnet(@RequestBody Department department) {
		return departmentService.AddDeparment(department);
	}
	
	@DeleteMapping("{departmentId}")
	public void deleteDataDepartment(@PathVariable int departmentId) {
		Department findDeparment = departmentRepo.findById(departmentId).get();
		findDeparment.getEmployee().forEach(employee ->{
			employee.setDepartment(null);
			employeeRepo.save(employee);
		});
		findDeparment.setEmployee(null);
		
		departmentRepo.deleteById(departmentId);;
		
	}
}

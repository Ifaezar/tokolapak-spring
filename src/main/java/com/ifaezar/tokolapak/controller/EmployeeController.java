package com.ifaezar.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifaezar.tokolapak.dao.DepartmnetRepo;
import com.ifaezar.tokolapak.dao.EmployeeAddressRepo;
import com.ifaezar.tokolapak.dao.EmployeeRepo;
import com.ifaezar.tokolapak.dao.ProjectRepo;
import com.ifaezar.tokolapak.entity.Department;
import com.ifaezar.tokolapak.entity.Employee;
import com.ifaezar.tokolapak.entity.EmployeeAddress;
import com.ifaezar.tokolapak.entity.Product;
import com.ifaezar.tokolapak.entity.Project;
import com.ifaezar.tokolapak.service.DepartmentService;
import com.ifaezar.tokolapak.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	public EmployeeRepo employeeRepo;
	
	@Autowired
	public EmployeeService employeeService;
	
	@Autowired
	public EmployeeAddressRepo employeeAddressRepo;
	
	@Autowired
	public DepartmnetRepo departmentRepo;
	
	@Autowired
	public ProjectRepo projectRepo;
	
	
	@PostMapping("/department/{departmentId}")
	public Employee addEmployee(@RequestBody Employee employee, @PathVariable int departmentId) {
		Department findDepartment = departmentRepo.findById(departmentId).get();
		if(findDepartment == null) {
			throw new RuntimeException("Employee Not Found");
		}
		employee.setDepartment(findDepartment);
		//send email after registration
		//emailUtil.sendEmail(employee.getEmail(),subject, body);
		return employeeRepo.save(employee);
	}
	
	@PutMapping("/{employeeId}/deparment/{departmentId}")
	public Employee addEmployeeDepartment(@PathVariable int departmentId, @PathVariable int employeeId) {
		Employee findEmployee = employeeRepo.findById(employeeId).get();
		if(findEmployee.toString() == "Optional.empty") {
			throw new RuntimeException("Employee Not Found");
		}
		// cara pertama
//		return departmentRepo.findById(departmentId).map(department ->{
//			findEmployee.setDepartment(department);
//			return employeeRepo.save(findEmployee);
//		}).orElseThrow(() -> new RuntimeException("Department Not Found"));
		
		//carakedua
		Department findDepartment = departmentRepo.findById(departmentId).get();
		findEmployee.setDepartment(findDepartment);
		return employeeRepo.save(findEmployee);
	}
	
	@GetMapping
	public Iterable <Employee> getEmployee() {
		return employeeRepo.findAll();
	}
	
	@DeleteMapping("/address/{id}")
	public void deleteEmployeeAddressById(@PathVariable int id) {
		Optional<EmployeeAddress> employeeAddress = employeeAddressRepo.findById(id);
		
		if(employeeAddress.get() == null) {
			throw new RuntimeException("Employee Address not Found;");
		}
		
		
		employeeService.deleteEmployeeAddress(employeeAddress.get());
	}
	
	@PutMapping("/{employeeId}/address")
	public Employee updateEmployeeAddress(@RequestBody EmployeeAddress employeeAddress, @PathVariable int employeeId) {
		Employee findEmployee = employeeRepo.findById(employeeId).get();
		findEmployee.setEmployeeAddress(employeeAddress);
		return employeeRepo.save(findEmployee);
	}
	
	@PostMapping("/{employeeId}/projects/{projectId}")
	public Employee addProjectToEmployee(@PathVariable int employeeId, @PathVariable int projectId){
		Employee findEmployee = employeeRepo.findById(employeeId).get();
		
		Project findProject = projectRepo.findById(projectId).get();
		
		findEmployee.getProjects().add(findProject);
		return employeeRepo.save(findEmployee);
	}
	
	
	
	
	
}

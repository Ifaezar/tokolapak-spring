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

import com.ifaezar.tokolapak.dao.EmployeeAddressRepo;
import com.ifaezar.tokolapak.dao.EmployeeRepo;
import com.ifaezar.tokolapak.entity.Employee;
import com.ifaezar.tokolapak.entity.EmployeeAddress;
import com.ifaezar.tokolapak.entity.Product;
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
	
	
	@PostMapping
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeRepo.save(employee);
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
	
	@PutMapping
	public Employee updateEmployeeAddress(@RequestBody Employee Employee) {
		return employeeService.updateEmployeeAddress(Employee);
	}
	
	
	
}

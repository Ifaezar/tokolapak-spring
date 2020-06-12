package com.ifaezar.tokolapak.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifaezar.tokolapak.dao.EmployeeAddressRepo;
import com.ifaezar.tokolapak.dao.EmployeeRepo;
import com.ifaezar.tokolapak.entity.Employee;
import com.ifaezar.tokolapak.entity.EmployeeAddress;
import com.ifaezar.tokolapak.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private EmployeeAddressRepo employeeAddressRepo;
	
	@Override
	public void deleteEmployeeAddress(EmployeeAddress employeeAddress) {
		employeeAddress.getEmployee().setEmployeeAddress(null); //putuskan hubungan dari employee ke address
		employeeAddress.setEmployee(null); //putuskan hubungan dari address ke employee
		employeeAddressRepo.delete(employeeAddress);
		
	}
	
	@Override
	@Transactional
	public Employee updateEmployeeAddress(Employee employee) {
		//employeeAddress.getEmployee().setEmployeeAddress(employeeAddress);
		return employeeRepo.save(employee);
	}

	
}

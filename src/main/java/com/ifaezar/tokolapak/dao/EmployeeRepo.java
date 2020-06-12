package com.ifaezar.tokolapak.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifaezar.tokolapak.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
}

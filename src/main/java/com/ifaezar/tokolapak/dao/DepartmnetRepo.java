package com.ifaezar.tokolapak.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifaezar.tokolapak.entity.Department;

public interface DepartmnetRepo extends JpaRepository<Department, Integer> {
	public Department findByName(String name);
}

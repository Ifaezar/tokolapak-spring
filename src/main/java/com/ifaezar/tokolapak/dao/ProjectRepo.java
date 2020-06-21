package com.ifaezar.tokolapak.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifaezar.tokolapak.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Integer> {

}

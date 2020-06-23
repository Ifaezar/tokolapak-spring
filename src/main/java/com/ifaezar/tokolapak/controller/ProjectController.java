package com.ifaezar.tokolapak.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifaezar.tokolapak.dao.EmployeeRepo;
import com.ifaezar.tokolapak.dao.ProjectRepo;
import com.ifaezar.tokolapak.entity.Department;
import com.ifaezar.tokolapak.entity.Employee;
import com.ifaezar.tokolapak.entity.Project;

@RestController
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@GetMapping
	public Iterable<Project> getAllProjects(){
		return projectRepo.findAll();
	}
	
	@GetMapping("/{projectId}")
	public Project getProjectById(@PathVariable int projectId) {
		Project findProject = projectRepo.findById(projectId).get();
		if(findProject == null) 
			throw new RuntimeException("project not found");
		return findProject;
	}
	
	@GetMapping("/{projectId}/employee")
	public List<Employee> getEmployeeProject(@PathVariable int projectId){
		Project findProject = projectRepo.findById(projectId).get();
		
		return findProject.getEmployees();
	}
	
	@PostMapping
	public Project addProject(@RequestBody Project project) {
		return projectRepo.save(project);
	}
	
	@DeleteMapping("{projectId}")
	public void deleteDataDepartment(@PathVariable int projectId) {
		Project findProject = projectRepo.findById(projectId).get();
		
		findProject.setEmployees(null);
		projectRepo.save(findProject);
		projectRepo.delete(findProject);
		
	}
	
}

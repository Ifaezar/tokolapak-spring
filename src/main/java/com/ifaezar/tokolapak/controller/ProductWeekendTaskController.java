package com.ifaezar.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifaezar.tokolapak.dao.ProductWeekendTaskRepo;
import com.ifaezar.tokolapak.entity.ProductWeekendTask;

@RestController
@RequestMapping("/productsWeekendTask")
@CrossOrigin
public class ProductWeekendTaskController {

	@Autowired
	private ProductWeekendTaskRepo productWeekendTaskRepo;
	
	@GetMapping
	public Iterable<ProductWeekendTask> getProducts(){
		return productWeekendTaskRepo.findAll();
	}
	
	@PostMapping
	public ProductWeekendTask addProduct(@RequestBody ProductWeekendTask productWeekendTask) {
		productWeekendTask.setId(0);
		return productWeekendTaskRepo.save(productWeekendTask);
	}
	
	@DeleteMapping("/{id}")
	public void DeleteProduct(@PathVariable int id) {
		Optional <ProductWeekendTask> findProduct = productWeekendTaskRepo.findById(id);
		productWeekendTaskRepo.deleteById(id);
	}
	
	@PutMapping("/edit")
	public ProductWeekendTask editProduct(@RequestBody ProductWeekendTask productWeekendTask) {
		return productWeekendTaskRepo.save(productWeekendTask);
	}
}

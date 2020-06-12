package com.ifaezar.tokolapak.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ifaezar.tokolapak.dao.ProductRepo;
import com.ifaezar.tokolapak.entity.Product;
import com.ifaezar.tokolapak.service.ProductService;

@RestController
public class productController {
	
	//@Autowired
	//private ProductRepo productRepo;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public Iterable<Product> getProducts() {
		return productService.getProducts();
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getProductById(@PathVariable int id){
		return productService.getProductById(id);
	}
	
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProductById(@PathVariable int id) {
		productService.deleteProductById(id);
	}
	
	@PutMapping("/products")
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	@GetMapping("/productName/{productName}")
	public Product getProductByProductName(@PathVariable String productName) {
		return productService.getProductByProductName(productName);
	}
}

package com.ifaezar.tokolapak.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ifaezar.tokolapak.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	public Product findByProductName(String productName);
}

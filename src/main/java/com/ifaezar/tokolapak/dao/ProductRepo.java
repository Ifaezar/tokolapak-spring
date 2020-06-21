package com.ifaezar.tokolapak.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ifaezar.tokolapak.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

	public Product findByProductName(String productName);

	@Query(value = "SELECT * FROM Product WHERE PRICE < ?1 AND product_name like ?2", nativeQuery = true)
	public Iterable<Product> findByMinPrice(double minPrice, String name);
	
	@Query(value = "SELECT * FROM Product WHERE PRICE > :maxPrice AND product_name like %:productName%", nativeQuery = true)
	public Iterable<Product> findByMaxPrice(@Param("maxPrice") double maxPrice, @Param("productName") String namaProduct);
	
}

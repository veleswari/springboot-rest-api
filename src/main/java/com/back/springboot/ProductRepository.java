package com.back.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	public Product findByName(String name);
	public Product findByNameIgnoreCase(String name); 
}
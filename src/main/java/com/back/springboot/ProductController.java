package com.back.springboot;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class ProductController {
	@Autowired
	private ProductService service;
	
	private final Logger LOGGER=  LoggerFactory.getLogger(ProductController.class);
	
	public ProductController(ProductService service) {
		super();
		this.service = service;
	}

	@GetMapping("/products")
	public List<Product> list() {
		LOGGER.info("Inside get method of ProductController");
	    return service.listAll();
	}
	
	@GetMapping("/products/{id}")
	public Product get(@PathVariable("id") Integer Id) throws ProductNotFoundException {
		return service.get(Id); 
	} 
	
	@PostMapping("/add")
	public void add(@Valid @RequestBody Product product) {
		LOGGER.info("Inside add method of ProductController");
		service.save(product);
	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable("id") Integer Id) {
		service.delete(Id);
	}
	
	@PutMapping("update/{id}")
	public void put(@PathVariable("id") Integer Id, @RequestBody Product product) {
		service.put(Id,product);
	}
	
	@GetMapping("products/name/{name}")
	public Product getProductByName(@PathVariable("name") String name) {
		return service.getProductByName(name); 
	}
	
}


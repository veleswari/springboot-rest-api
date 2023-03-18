package com.back.springboot;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
 
@Service 
@Transactional
public class ProductService{ 
	@Autowired
	private ProductRepository repo;
	
	public List<Product> listAll(){
		return repo.findAll();
	}
	
	public void save(Product product) {
		repo.save(product);
	}
	
	public Product get(Integer id) throws ProductNotFoundException {
		Optional<Product> product= repo.findById(id);
		if(!product.isPresent()) {
			throw new ProductNotFoundException("Product is not available");
		}
		return product.get(); 
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public void put(Integer id, Product product) {
		Product dbpro = repo.findById(id).get();
		
		if(Objects.nonNull(product.getName()) && !"".equalsIgnoreCase(product.getName())) {
			dbpro.setName(product.getName());
		}
		if(Objects.nonNull(product.getPrice())) {
			dbpro.setPrice(product.getPrice());
		}
		repo.save(dbpro);
	}

	public Product getProductByName(String name) {
		return repo.findByNameIgnoreCase(name); 
	}	
}

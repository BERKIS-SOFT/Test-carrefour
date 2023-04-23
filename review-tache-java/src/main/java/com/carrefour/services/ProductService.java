package com.carrefour.services;

import java.util.List;

import com.carrefour.entities.Product;

public interface ProductService {

	Product save(Product product);
	Product update(Product product,Long id);
	List<Product> findAll();
	Product find(Long id);
	void delete(Long id);
	
}

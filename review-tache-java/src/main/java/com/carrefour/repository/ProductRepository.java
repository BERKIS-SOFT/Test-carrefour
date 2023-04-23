package com.carrefour.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carrefour.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

}

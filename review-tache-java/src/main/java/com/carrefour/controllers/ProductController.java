package com.carrefour.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrefour.dtos.Inventory;
import com.carrefour.dtos.ProductDto;
import com.carrefour.entities.Product;
import com.carrefour.services.InventoryService;
import com.carrefour.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@RestController
@RequestMapping("/products")
@Api(description = "Api for manage product and inventory")
public class ProductController {

	@Autowired ModelMapper mapper;
	private ProductService productService;
	private InventoryService inventoryService;
	
	public ProductController(ProductService productService,InventoryService inventoryService) {
		this.productService=productService;
		this.inventoryService=inventoryService;
	}
	
	@ApiOperation(value = "find all products")
	@GetMapping
	public List<ProductDto> findAll(){
		return productService.findAll().stream().map(product->mapper.map(product,ProductDto.class)).collect(Collectors.toList());
	}
	
	
	@ApiOperation(value = "find product by id")
	@GetMapping("/{id}")
	public ProductDto find(
			@PathVariable
			@ApiParam(defaultValue = "1",example = "5",required = true,type = "entier")
			long id) {
		return mapper.map(productService.find(id), ProductDto.class);
	}
	
	@ApiOperation(value = "create a new  product")
	@PostMapping
	public ProductDto save(@RequestBody ProductDto productDto) {
		Product productRequest = mapper.map(productDto, Product.class);
		productService.save(productRequest);
		ProductDto productResoponse = mapper.map(productRequest, ProductDto.class);
		return productResoponse;
	}
	
	@ApiOperation(value = "update an existing product")
	@PutMapping("/{id}")
	public ProductDto update(@RequestBody ProductDto productDto,@PathVariable long id) {
		Product productRequest = mapper.map(productDto, Product.class);
		productService.update(productRequest, id);
		ProductDto productResoponse = mapper.map(productRequest, ProductDto.class);
		return productResoponse;
	}
	
	@ApiOperation(value = "delete an existing product")
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		this.productService.delete(id);
	}
	
	@ApiOperation(value = "get product inventory")
	@GetMapping("/inventory")
	public Inventory getInventory() {
		return inventoryService.getProductsInventory();
	}
}

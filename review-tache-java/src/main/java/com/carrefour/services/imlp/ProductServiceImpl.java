package com.carrefour.services.imlp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.carrefour.dtos.Inventory;
import com.carrefour.dtos.ProductInventory;
import com.carrefour.entities.Product;
import com.carrefour.exceptions.NoContent;
import com.carrefour.exceptions.NotFound;
import com.carrefour.repository.ProductRepository;
import com.carrefour.services.InventoryService;
import com.carrefour.services.ProductService;


@Service
public class ProductServiceImpl implements ProductService,InventoryService{
	
	private ProductRepository productRepository;
	
	 public ProductServiceImpl(ProductRepository productRepository) {
		 this.productRepository=productRepository;
	 }

	
	@Override
	public Product save(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public Product update(Product product, Long id) {
		Optional<Product> productResponce = this.productRepository.findById(id); 
		if(!productResponce.isPresent()) {
			return null;
		}
			productResponce.get().setId(id);
			productResponce.get().setNom(product.getNom());
			productResponce.get().setPrice(product.getPrice());
			productResponce.get().setBarcode(product.getBarcode());
			productResponce.get().setState(product.getState());
			return productRepository.save(productResponce.get());
	}

	@Override
	public List<Product> findAll() {
		List<Product> productResponse = StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
		
		if(productResponse.isEmpty() || productResponse==null) {
			throw new NoContent();
		}
		return productResponse;
	}

	@Override
	public Product find(Long id) {
		Optional<Product> productResponce = this.productRepository.findById(id); 
		if(!productResponce.isPresent()) {
			throw new NotFound();
		}
			return productResponce.get();	
	}

	@Override
	public void delete(Long id) {
		Optional<Product> productResponce = this.productRepository.findById(id); 
		if(!productResponce.isPresent()) {
			throw new NotFound();
		}
	    productRepository.delete(productResponce.get());
	}

	@Override
	public Inventory getProductsInventory() {
		Inventory inventory = new Inventory();
        for(Product p: productRepository.findAll()) {
            if (inventory.containsKey(p.getNom())) {
            	ProductInventory productInventory = inventory.get(p.getNom());
            	String state=p.getState();
                if (!("broken".equals(state))) {
                	productInventory.setQty(productInventory.getQty() + 1);
                    productInventory.setTotalPrice(productInventory.getTotalPrice() + p.getPrice());
                    productInventory.setProductBarcodeList(productInventory.getProductBarcodeList()+ "," + p.getBarcode());
                }
                //inventory.put(p.getNom(), productInventory);
            }else {
            	ProductInventory productInventory = new ProductInventory(p.getNom().toLowerCase(), p.getPrice(), 1);
            	productInventory.setProductBarcodeList(p.getBarcode());
                inventory.put(p.getNom(),productInventory);
            }
        }
        return inventory;	
        }

}

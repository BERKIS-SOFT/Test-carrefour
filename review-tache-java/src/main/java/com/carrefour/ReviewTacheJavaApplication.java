package com.carrefour;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.carrefour.entities.Product;
import com.carrefour.repository.ProductRepository;
/**
 * pour acceder à la documentation utiliser cette url 
 * //http://localhost:8080/swagger-ui/index.html#/ access to swagger 3.0
 * @author AHMED.CHABANE
 *
 */
@EnableWebMvc
@SpringBootApplication
public class ReviewTacheJavaApplication  implements ApplicationRunner{
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(ReviewTacheJavaApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Product product_1 = new Product(1L, "Pen",3.52f,"AXD2MDD", null);
		Product product_2 = new Product(2L, "Pen",3.16f,"AFD2MDD", null);
		Product product_3 = new Product(3L, "box",7f,"AXD45DD", "broken");
		Product product_4 = new Product(4L, "box",4.5f,"AX645DD", "reconditionned");
		Product product_5 = new Product(5L, "Box",7f,"AXD45XX", "ok");
		Product product_6 = new Product(6L, "book",12.7f,"AXD478D", null);
		Product product_7 = new Product(7L, "Game",8.9f,"542878D", "broken");
		
		productRepository.save(product_1);
		productRepository.save(product_2);
		productRepository.save(product_3);
		productRepository.save(product_4);
		productRepository.save(product_5);
		productRepository.save(product_6);
		productRepository.save(product_7);



		
	}

}

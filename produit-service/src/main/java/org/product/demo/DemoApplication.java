package org.product.demo;

import org.product.demo.dto.ProductReqDTO;
import org.product.demo.model.Product;
import org.product.demo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductService productService){
		return  args -> {
			productService.save(new ProductReqDTO( 0L, "Bimo", 50.94, 1000, new Date(), "product 1"));
			productService.save(new ProductReqDTO(0L, "Nescafe", 99.64, 500, new Date(), "product 2"));
		};
	}

}

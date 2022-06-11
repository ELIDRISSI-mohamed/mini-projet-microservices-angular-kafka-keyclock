package org.product.demo;

import org.product.demo.dto.ProductReqDTO;
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
			productService.save(new ProductReqDTO(0L, "product-1", 50.94, 1000, new Date(), "product 1"));
			productService.save(new ProductReqDTO(0L, "product-2", 30.94, 2300, new Date(), "product 2"));
			productService.save(new ProductReqDTO(0L, "product-3", 230.94, 4500, new Date(), "product 3"));
			productService.save(new ProductReqDTO(0L, "product-4", 99.64, 500, new Date(), "product 4"));
		};
	}

}

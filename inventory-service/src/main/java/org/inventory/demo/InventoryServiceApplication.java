package org.inventory.demo;

import org.inventory.demo.dto.InventoryReqDTO;
import org.inventory.demo.entity.Product;
import org.inventory.demo.openfeign.ProductRestClient;
import org.inventory.demo.service.InventoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(InventoryService inventoryService, ProductRestClient productRestClient){
		return args -> {
			List<Product> products = productRestClient.getAllProducts();
		};
	}
}

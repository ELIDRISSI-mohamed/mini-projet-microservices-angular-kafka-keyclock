package org.demo.miniProjet;

import org.demo.miniProjet.dto.CustomerReqDTO;
import org.demo.miniProjet.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class CustomerServiceKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceKafkaApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CustomerService customerService){
		return args -> {
			customerService.save(new CustomerReqDTO( null, "r.product", "product@gmail.com"));
			customerService.save(new CustomerReqDTO( null, "r.customer", "customer@gmail.com"));
			customerService.save(new CustomerReqDTO( null, "r.customer1", "customer1@gmail.com"));
			customerService.save(new CustomerReqDTO( null, "r.customer2", "customer2@gmail.com"));
			customerService.save(new CustomerReqDTO(null, "r.billing", "billing@gmail.com"));
			customerService.save(new CustomerReqDTO(null, "r.user", "user@gmail.com"));
			customerService.save(new CustomerReqDTO(null, "r.admin", "admin@gmail.com"));
		};
	}

}

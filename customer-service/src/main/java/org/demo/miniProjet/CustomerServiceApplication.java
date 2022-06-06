package org.demo.miniProjet;

import org.demo.miniProjet.dto.CustomerReqDTO;
import org.demo.miniProjet.repository.CustomerRepo;
import org.demo.miniProjet.service.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner run(CustomerService customerService){
		return args -> {
			customerService.save(new CustomerReqDTO( null, "idrissi", "idrissi@gmail.com"));
			customerService.save(new CustomerReqDTO( null, "kamali", "kamalin@gmail.com"));
			customerService.save(new CustomerReqDTO(null, "hasani", "hasani@gmail.com"));
		};
	}

}

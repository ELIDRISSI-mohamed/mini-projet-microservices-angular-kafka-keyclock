package org.billing.demo;

import org.billing.demo.dto.InvoiceReqDTO;
import org.billing.demo.service.InvoiceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceKafkaApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(InvoiceService invoiceService){
		return args -> {
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(10000), "r.customer1"));
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(20000), "r.customer"));
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(30000), "r.customer2"));
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(500), "r.cutsomer1"));
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(5000), "r.cutsomer1"));
		};
	}

}

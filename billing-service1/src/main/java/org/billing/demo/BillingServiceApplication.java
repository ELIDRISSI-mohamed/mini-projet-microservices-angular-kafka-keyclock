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
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(InvoiceService invoiceService){
		return args -> {
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(10000), "idrissi"));
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(20000), "idrissi"));
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(30000), "hasani"));
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(500), "kamali"));
			invoiceService.save(new InvoiceReqDTO(BigDecimal.valueOf(5000), "kamali"));
		};
	}

}

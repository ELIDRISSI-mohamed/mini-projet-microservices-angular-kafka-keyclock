package org.inventory.demo.openfeign;

import org.inventory.demo.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/api/customer/{name}")
    Customer getCustomer(@PathVariable String name);
    @GetMapping("/api/customer/all")
    List<Customer> allCustomers();

}

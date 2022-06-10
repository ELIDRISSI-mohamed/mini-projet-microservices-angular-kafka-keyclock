package org.inventory.demo.openfeign;

import org.inventory.demo.entity.Customer;
import org.inventory.demo.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("PRODUCT-SERVICE")
public interface ProductRestClient {
    @GetMapping("/api/product/{id}")
    Product getProduct(@PathVariable Long id);
    @GetMapping("/api/product/all")
    List<Product> getAllProducts();
}

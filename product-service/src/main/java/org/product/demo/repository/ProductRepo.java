package org.product.demo.repository;

import org.product.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

public interface ProductRepo extends JpaRepository<Product,Long> {
}

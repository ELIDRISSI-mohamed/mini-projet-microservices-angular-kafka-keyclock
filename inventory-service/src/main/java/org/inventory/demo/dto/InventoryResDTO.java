package org.inventory.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.inventory.demo.entity.Customer;
import org.inventory.demo.entity.Product;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class InventoryResDTO {
    private Date date;
    private BigDecimal amount;
    List<Product> products;
    private String name;
    @Transient
    private Customer customer;
}

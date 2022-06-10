package org.inventory.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.inventory.demo.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class InventoryReqDTO {
    private BigDecimal amount;
    private List<Product> products;
    private String name;
}

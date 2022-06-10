package org.product.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductResDTO {
    private Long id;
    private String libele;
    private double prix;
    private long qte;
    private Date date;
    private String description;
}

package org.product.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class ProductReqDTO {
    private Long id;
    private String libele;
    private double prix;
    private long qte;
    private Date date;
    private String description;
}

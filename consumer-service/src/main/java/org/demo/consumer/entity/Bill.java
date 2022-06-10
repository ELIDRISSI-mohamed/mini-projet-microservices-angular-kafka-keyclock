package org.demo.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Bill {
    @Id
    private Long id;
    private String customer;
    private double price;
}

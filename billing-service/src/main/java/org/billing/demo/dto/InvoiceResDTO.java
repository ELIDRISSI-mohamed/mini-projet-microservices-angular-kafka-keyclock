package org.billing.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.billing.demo.entity.Customer;

import java.math.BigDecimal;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class InvoiceResDTO {
    private Date date;
    private BigDecimal amount;
    private Customer customer;
}

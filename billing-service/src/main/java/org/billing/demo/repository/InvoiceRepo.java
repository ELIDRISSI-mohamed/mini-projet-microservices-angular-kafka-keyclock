package org.billing.demo.repository;


import org.billing.demo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepo extends JpaRepository<Invoice,Long> {
    List<Invoice> findByName(String name);
}

package org.billing.demo.repository;


import org.billing.demo.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
public interface InvoiceRepo extends JpaRepository<Invoice,Long> {
    List<Invoice> findByName(String name);
}

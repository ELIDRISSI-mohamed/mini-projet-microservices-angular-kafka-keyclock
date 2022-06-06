 package org.billing.demo.service;

 import org.billing.demo.dto.InvoiceReqDTO;
 import org.billing.demo.dto.InvoiceResDTO;

 import java.util.List;

public interface InvoiceService {

    InvoiceResDTO save(InvoiceReqDTO invoiceReqDTO);
    InvoiceResDTO getInvoice(Long id);
    List<InvoiceResDTO> allInvoiceByCustomerName(String name);
    List<InvoiceResDTO> getAllInvoices();
}

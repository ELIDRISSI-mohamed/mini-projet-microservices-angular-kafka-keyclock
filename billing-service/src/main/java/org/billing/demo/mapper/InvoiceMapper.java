package org.billing.demo.mapper;

import org.mapstruct.Mapper;
import org.billing.demo.dto.InvoiceReqDTO;
import org.billing.demo.dto.InvoiceResDTO;
import org.billing.demo.entity.Invoice;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceResDTO fromInvoice(Invoice invoice);
    Invoice fromInvoiceReqDTO(InvoiceReqDTO invoiceReqDTO);
}

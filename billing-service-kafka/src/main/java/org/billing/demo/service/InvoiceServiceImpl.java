package org.billing.demo.service;


import org.billing.demo.dto.InvoiceReqDTO;
import org.billing.demo.dto.InvoiceResDTO;
import org.billing.demo.entity.Customer;
import org.billing.demo.entity.Invoice;
import org.billing.demo.mapper.InvoiceMapper;
import org.billing.demo.repository.CustomerRepo;
import org.billing.demo.repository.InvoiceRepo;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {
    private InvoiceRepo invoiceRepo;
    private InvoiceMapper invoiceMapper;
    private CustomerRepo customerRepo;

    public InvoiceServiceImpl(InvoiceRepo invoiceRepo, InvoiceMapper invoiceMapper, CustomerRepo customerRepo){
        this.invoiceRepo = invoiceRepo;
        this.invoiceMapper = invoiceMapper;
        this.customerRepo = customerRepo;
    }

    @KafkaListener(topics = "CUSTOMER", containerFactory = "kafkaListenerContainerFactory")
    public void consumeImportOffreTopic(Customer customer) throws Exception {
        System.out.println("Received ==> "+customer.toString());
        customerRepo.save(customer);
    }

    @Override
    public InvoiceResDTO save(InvoiceReqDTO invoiceReqDTO) {
        Invoice invoice = invoiceMapper.fromInvoiceReqDTO(invoiceReqDTO);
        invoice.setDate(new Date());
        Invoice saveInvoice = invoiceRepo.save(invoice);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public InvoiceResDTO getInvoice(@PathVariable Long id) {
        Invoice invoice = invoiceRepo.findById(id).get();
        Customer customer = customerRepo.getByName(invoice.getName());
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }

    @Override
    public List<InvoiceResDTO> allInvoiceByCustomerName(@PathVariable String name) {
        List<Invoice> invoices = invoiceRepo.findByName(name);
        return invoices.stream()
                .map(invoice -> mapper(invoice))
                .collect(Collectors.toList());
    }
    @Override
    public List<InvoiceResDTO> getAllInvoices() {
        List<Invoice> invoices = invoiceRepo.findAll();
        return invoices.stream()
                .map(invoice -> mapper(invoice))
                .collect(Collectors.toList());
    }
    public InvoiceResDTO mapper(Invoice invoice){
        Customer customer = customerRepo.getByName(invoice.getName());
        System.out.println(customer);
        invoice.setCustomer(customer);
        return invoiceMapper.fromInvoice(invoice);
    }
}

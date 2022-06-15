package org.demo.miniProjet.service;

import lombok.extern.slf4j.Slf4j;
import org.demo.miniProjet.dto.CustomerReqDTO;
import org.demo.miniProjet.dto.CustomerResDTO;
import org.demo.miniProjet.entity.Customer;
import org.demo.miniProjet.exception.ExceptionCode;
import org.demo.miniProjet.exception.TechnicalException;
import org.demo.miniProjet.mapper.CustomerMapper;
import org.demo.miniProjet.repository.CustomerRepo;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional @Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo customerRepo;
    private CustomerMapper customerMapper;
    private final KafkaTemplate<String, CustomerResDTO> template;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper, KafkaTemplate<String, CustomerResDTO> template){
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
        this.template = template;
    }

    @Override
    public CustomerResDTO save(@RequestBody CustomerReqDTO customerReqDTO) {
        log.info("Customer save service");
        Customer customer = customerMapper.customerReqDTOCustomer(customerReqDTO);
        Customer saveCustomer = customerRepo.save(customer);
        CustomerResDTO customerResDTO = customerMapper.customerToCustomerResDTO(saveCustomer);
        template.send("CUSTOMER", "key"+customerResDTO.getClass(), customerResDTO);
        return customerResDTO;
    }

    @Override
    public CustomerResDTO getCustomer(@PathVariable String name) throws TechnicalException {
        log.info("Get customer service");
        System.out.println(name);
        Customer customer = customerRepo.findByName(name);
        System.out.println(customer);
        if(customer==null) throw  new TechnicalException(ExceptionCode.CUSTOMER_NOT_EXIST);
        return customerMapper.customerToCustomerResDTO(customer);
    }

    @Override
    public CustomerResDTO update(@RequestBody CustomerReqDTO customerReqDTO) {
        log.info("Update customer service");
        Customer customer = customerMapper.customerReqDTOCustomer(customerReqDTO);
        Customer updateCustomer = customerRepo.save(customer);
        return customerMapper.customerToCustomerResDTO(updateCustomer);
    }

    @Override
    public void deleteByName(@PathVariable String name) {
        customerRepo.deleteByName(name);
    }

    @Override
    public List<CustomerResDTO> allCustomers() {
        log.info("All customers service");
        List<Customer> customers = customerRepo.findAll();
        List<CustomerResDTO> customerResDTOS =
                customers.stream()
                .map(customer -> customerMapper.customerToCustomerResDTO(customer))
                .collect(Collectors.toList());
        return customerResDTOS;
    }
}

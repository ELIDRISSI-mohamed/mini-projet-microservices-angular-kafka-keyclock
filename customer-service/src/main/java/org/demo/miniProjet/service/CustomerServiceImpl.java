package org.demo.miniProjet.service;

import lombok.extern.slf4j.Slf4j;
import org.demo.miniProjet.dto.CustomerReqDTO;
import org.demo.miniProjet.dto.CustomerResDTO;
import org.demo.miniProjet.entity.Customer;
import org.demo.miniProjet.exception.ExceptionCode;
import org.demo.miniProjet.exception.TechnicalException;
import org.demo.miniProjet.mapper.CustomerMapper;
import org.demo.miniProjet.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional @Slf4j
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepo customerRepo;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepo customerRepo, CustomerMapper customerMapper){
        this.customerRepo = customerRepo;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResDTO save(CustomerReqDTO customerReqDTO) {
        log.info("Customer save service");
        Customer customer = customerMapper.customerReqDTOCustomer(customerReqDTO);
        Customer saveCustomer = customerRepo.save(customer);
        CustomerResDTO customerResDTO = customerMapper.customerToCustomerResDTO(saveCustomer);
        return customerResDTO;
    }

    @Override
    public CustomerResDTO getCustomer(String name) throws TechnicalException {
        log.info("Get customer service");
        Customer customer = customerRepo.findByName(name);
        if(customer==null) throw  new TechnicalException(ExceptionCode.CUSTOMER_NOT_EXIST);
        return customerMapper.customerToCustomerResDTO(customer);
    }

    @Override
    public CustomerResDTO update(CustomerReqDTO customerReqDTO) {
        log.info("Update customer service");
        Customer customer = customerMapper.customerReqDTOCustomer(customerReqDTO);
        Customer updateCustomer = customerRepo.save(customer);
        return customerMapper.customerToCustomerResDTO(updateCustomer);
    }

    @Override
    public void deleteByName(String name) {
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

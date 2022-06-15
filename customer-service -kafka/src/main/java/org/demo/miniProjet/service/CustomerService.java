package org.demo.miniProjet.service;

import org.demo.miniProjet.dto.CustomerReqDTO;
import org.demo.miniProjet.dto.CustomerResDTO;
import org.demo.miniProjet.exception.TechnicalException;

import java.util.List;

public interface CustomerService {

    CustomerResDTO save(CustomerReqDTO customerReqDTO);
    CustomerResDTO getCustomer(String name) throws TechnicalException;
    CustomerResDTO update(CustomerReqDTO customerReqDTO);
    void deleteByName(String name);
    List<CustomerResDTO> allCustomers();
}

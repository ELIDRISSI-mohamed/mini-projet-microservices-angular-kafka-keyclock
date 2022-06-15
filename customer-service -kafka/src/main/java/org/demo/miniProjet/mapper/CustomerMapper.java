package org.demo.miniProjet.mapper;

import org.demo.miniProjet.dto.CustomerReqDTO;
import org.demo.miniProjet.dto.CustomerResDTO;
import org.demo.miniProjet.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResDTO customerToCustomerResDTO(Customer customerReqDTO);
    Customer customerReqDTOCustomer(CustomerReqDTO customerReqDTO);
}

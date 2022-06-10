package org.demo.miniProjet.controller;

import org.demo.miniProjet.dto.CustomerReqDTO;
import org.demo.miniProjet.dto.CustomerResDTO;
import org.demo.miniProjet.exception.TechnicalException;
import org.demo.miniProjet.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public List<CustomerResDTO> allCustomers(){
        return customerService.allCustomers();
    }
    @PostMapping("/save")
    public CustomerResDTO save(@RequestBody CustomerReqDTO customerReqDTO){
        return customerService.save(customerReqDTO);
    }
    @GetMapping("/{name}")
    public CustomerResDTO getCustomer(@PathVariable String name) throws TechnicalException {
        return customerService.getCustomer(name);
    }
    @PutMapping("/update")
    public CustomerResDTO update(@RequestBody CustomerReqDTO customerReqDTO){
        return customerService.update(customerReqDTO);
    }
    @DeleteMapping("/delete/{name}")
    public void delete(@PathVariable String name){
        customerService.deleteByName(name);
    }
}

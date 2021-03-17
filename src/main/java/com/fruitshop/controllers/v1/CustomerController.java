package com.fruitshop.controllers.v1;

import com.fruitshop.api.v1.model.CustomerDTO;
import com.fruitshop.api.v1.model.CustomerListDTO;
import com.fruitshop.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<>(
                new CustomerListDTO(customerService.getAllCustomers()),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable Long id) {
        return new ResponseEntity<>(
                customerService.getCustomerById(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDTO> updateCustomerByDTO(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        if(customerService.getCustomerById(id) != null)
            customerDTO.setCustomer_url("/api/v1/customers/" +id);
        return  new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
    }
}

package com.fruitshop.api.v1.mapper;

import com.fruitshop.api.v1.model.CustomerDTO;
import com.fruitshop.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    final String FNAME = "John";
    final String LANME = "Doe";
    final Long ID = 1L;
    final String URL_FORMAT = "/api/v1/customers/";

    @Test
    void customerToCustomerDTO() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFName(FNAME);
        customer.setLName(LANME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customer.getFName(), customerDTO.getFirstname());
        assertEquals(customer.getLName(), customerDTO.getLastname());
        assertEquals(URL_FORMAT + ID, customerDTO.getCustomer_url());
    }

    @Test
    void idToUrl() {

        assertEquals(customerMapper.idToUrl(ID), URL_FORMAT + ID);
    }
}
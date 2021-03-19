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
    void customerToCustomerDTOTest() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FNAME);
        customer.setLastName(LANME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customer.getFirstName(), customerDTO.getFirstname());
        assertEquals(customer.getLastName(), customerDTO.getLastname());
        assertEquals(URL_FORMAT + ID, customerDTO.getCustomer_url());
    }

    @Test
    void idToUrlTest() {

        assertEquals(URL_FORMAT + ID, customerMapper.idToUrl(ID));
    }

    @Test
    void urlToIdTest() {

        assertEquals(ID, customerMapper.urlToId(URL_FORMAT + ID));
    }

    @Test
    void CustomerDTOToCustomer() {

        CustomerDTO customerDTO = CustomerDTO
                .builder()
                .customer_url(URL_FORMAT+ID)
                .firstname(FNAME)
                .lastname(LANME)
                .build();

        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        assertEquals(customer.getFirstName(), customerDTO.getFirstname());
        assertEquals(customer.getLastName(), customerDTO.getLastname());
        assertEquals(customer.getId(), customerMapper.urlToId(customerDTO.getCustomer_url()));
    }
}
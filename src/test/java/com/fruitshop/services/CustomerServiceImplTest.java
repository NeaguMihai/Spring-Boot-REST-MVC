package com.fruitshop.services;

import com.fruitshop.api.v1.mapper.CustomerMapper;
import com.fruitshop.api.v1.model.CustomerDTO;
import com.fruitshop.domain.Customer;
import com.fruitshop.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    public static final String F_NAME = "John";
    public static final String L_NAME = "Doe";
    public static final long ID = 1L;
    public static final String URL = "/api/v1/customers/";
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        assertEquals(customerDTOS.size(), 3);
    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setLName(L_NAME);
        customer.setFName(F_NAME);
        customer.setId(ID);

        when(customerRepository.findCustomerById(anyLong())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerById(ID);

        assertEquals(customerDTO.getFirstname(), F_NAME);
        assertEquals(customerDTO.getLastname(), L_NAME);
        assertEquals(customerDTO.getCustomer_url(), URL + ID);
    }
}
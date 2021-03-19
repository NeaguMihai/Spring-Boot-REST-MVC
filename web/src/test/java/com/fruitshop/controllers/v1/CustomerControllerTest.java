package com.fruitshop.controllers.v1;


import com.fruitshop.api.v1.model.CustomerDTO;
import com.fruitshop.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.fruitshop.controllers.v1.AbstractRestTestHelper.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    public static final String FIRST_N1 = "John1";
    public static final String LAST_N1 = "Doe1";
    public static final String URL1 = "/api/v1/customers/1";
    public static final String FIRST_N2 = "John2";
    public static final String LAST_N2 = "Doe2";
    public static final String URL2 = "/api/v1/customers/2";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getAllCustomers() throws Exception {


        List<CustomerDTO> customerDTOS = Arrays.asList(
                CustomerDTO.builder()
                        .firstname(FIRST_N1)
                        .lastname(LAST_N1)
                        .customer_url(URL1)
                        .build(),
                CustomerDTO.builder()
                        .firstname(FIRST_N2)
                        .lastname(LAST_N2)
                        .customer_url(URL2)
                        .build()
        );

        when(customerService.getAllCustomers()).thenReturn(customerDTOS);

        mockMvc.perform(get("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));


    }

    @Test
    void getById() throws Exception {

        when(customerService.getCustomerById(anyLong())).thenReturn(
                CustomerDTO
                        .builder()
                        .customer_url(URL1)
                        .firstname(FIRST_N1)
                        .lastname(LAST_N1)
                        .build()
        );

        mockMvc.perform(get(URL1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_N1)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_N1)));

    }

    @Test
    public void createNewCustomer() throws Exception {

        CustomerDTO customerDTO  = CustomerDTO
                .builder()
                .firstname(FIRST_N1)
                .lastname(LAST_N1)
                .build();
        CustomerDTO returned  = CustomerDTO
                .builder()
                .firstname(FIRST_N1)
                .lastname(LAST_N1)
                .customer_url(URL1)
                .build();

        when(customerService.createCustomer(customerDTO)).thenReturn(returned);

        mockMvc.perform(post("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_N1)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_N1)));

    }

    @Test
    public void updateCustomerByDTOTest() throws Exception {
        CustomerDTO customerDTOExists = CustomerDTO
                .builder()
                .firstname(FIRST_N1)
                .lastname(LAST_N1)
                .build();
        CustomerDTO customerDTONotExists = CustomerDTO
                .builder()
                .firstname(FIRST_N1)
                .lastname(LAST_N1)
                .customer_url(URL1)
                .build();

        when(customerService.createCustomer(customerDTOExists)).thenReturn(customerDTONotExists);

        mockMvc.perform(put("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTOExists)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_N1)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_N1)))
                .andExpect(jsonPath("$.customer_url", equalTo(URL1)));

    }
}
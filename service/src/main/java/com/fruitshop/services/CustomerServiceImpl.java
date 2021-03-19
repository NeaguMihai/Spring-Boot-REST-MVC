package com.fruitshop.services;

import com.fruitshop.api.v1.mapper.CustomerMapper;
import com.fruitshop.api.v1.model.CustomerDTO;
import com.fruitshop.domain.Customer;
import com.fruitshop.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDTO(
                customerRepository
                        .findCustomerById(id)
        );
    }//TODO:adauga optional

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDTOToCustomer(customerDTO);

        Customer saved = customerRepository.save(customer);

        return customerMapper.customerToCustomerDTO(saved);
    }

    @Override
    public CustomerDTO patchCustomer(CustomerDTO customerDTO) {
        Customer model = customerMapper.customerDTOToCustomer(customerDTO);
        //TODO:adauga exceptii si verificare pentru null
        Customer returned = customerRepository.findCustomerById(model.getId());
        return null;
    }

}

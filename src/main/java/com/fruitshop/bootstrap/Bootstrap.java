package com.fruitshop.bootstrap;


import com.fruitshop.domain.Category;
import com.fruitshop.domain.Customer;
import com.fruitshop.repository.CategoryRepository;
import com.fruitshop.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) {

        Category fruits = new Category();
        fruits.setName("fruits");

        Category dried = new Category();
        dried.setName("dried");

        Category fresh = new Category();
        fresh.setName("fresh");

        Category exotic = new Category();
        exotic.setName("exotic");

        Category nuts = new Category();
        nuts.setName("nuts");

        categoryRepository.saveAll(Arrays.asList(fruits, dried, fresh, exotic, nuts));

        Customer c1 = Customer.builder().firstName("John1").lastName("Doe1").build();
        Customer c2 = Customer.builder().firstName("John2").lastName("Doe2").build();
        Customer c3 = Customer.builder().firstName("John3").lastName("Doe3").build();
        Customer c4 = Customer.builder().firstName("John4").lastName("Doe4").build();

        customerRepository.saveAll(Arrays.asList(c1, c2, c3, c4));

    }

}

package com.sruitshop.bootstrap;

import com.sruitshop.domain.Category;
import com.sruitshop.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Bootstrap implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

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

    }

}

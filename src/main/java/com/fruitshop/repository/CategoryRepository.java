package com.fruitshop.repository;


import com.fruitshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getAllCategories();

    Category findByName(String name);
}

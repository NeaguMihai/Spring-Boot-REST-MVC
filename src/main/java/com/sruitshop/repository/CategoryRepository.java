package com.sruitshop.repository;


import com.sruitshop.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> getAllCategories();

    Category findByName(String name);
}

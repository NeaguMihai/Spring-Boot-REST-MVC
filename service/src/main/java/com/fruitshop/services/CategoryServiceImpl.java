package com.fruitshop.services;

import com.fruitshop.api.v1.mapper.CategoryMapper;
import com.fruitshop.api.v1.model.CategoryDTO;
import com.fruitshop.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper ;
    private final CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return  categoryRepository.findAll()
                .stream()
                .map(categoryMapper::CategoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.CategoryToCategoryDTO(categoryRepository.findByName(name));
    }
}

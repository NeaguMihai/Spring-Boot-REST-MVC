package com.sruitshop.services;

import com.sruitshop.api.v1.mapper.CategoryMapper;
import com.sruitshop.api.v1.model.CategoryDTO;
import com.sruitshop.domain.Category;
import com.sruitshop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    public static final Long ID = 1L;
    public static final String NAME = "Name";

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {

        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.getAllCategories()).thenReturn(categories);

        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        assertEquals(3, categoryDTOS.size());
    }

    @Test
    void getCategoryByName() {
        Category categ = new Category();
        categ.setId(ID);
        categ.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(categ);

        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        assertEquals(categoryDTO.getId(), ID);
        assertEquals(categoryDTO.getName(), NAME);
    }
}
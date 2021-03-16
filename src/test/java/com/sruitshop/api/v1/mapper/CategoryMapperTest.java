package com.sruitshop.api.v1.mapper;


import com.fruitshop.api.v1.mapper.CategoryMapper;
import com.fruitshop.api.v1.model.CategoryDTO;
import com.fruitshop.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Id;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {


    public static final String NAME = "Dry";
    public static final Long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDTOTest() {
        Category dry = new Category();
        dry.setName(NAME);
        dry.setId(ID);

        CategoryDTO dryDTO = categoryMapper.CategoryToCategoryDTO(dry);

        assertEquals(ID, dryDTO.getId());
        assertEquals(NAME, dryDTO.getName());
    }

}
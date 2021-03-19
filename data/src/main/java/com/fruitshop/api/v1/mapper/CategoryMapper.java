package com.fruitshop.api.v1.mapper;


import com.fruitshop.api.v1.model.CategoryDTO;
import com.fruitshop.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO CategoryToCategoryDTO(Category category);
}

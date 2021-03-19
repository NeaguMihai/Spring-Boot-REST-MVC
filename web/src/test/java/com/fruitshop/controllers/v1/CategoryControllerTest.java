package com.fruitshop.controllers.v1;

import com.fruitshop.api.v1.model.CategoryDTO;
import com.fruitshop.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class CategoryControllerTest {

    public static final String NUM_1 = "num1";
    public static final String NUM_2 = "num2";
    public static final long ID = 1L;
    public static final long ID1 = 2L;
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategories() throws Exception {

        CategoryDTO categ1 = new CategoryDTO();
        categ1.setId(ID);
        categ1.setName(NUM_1);

        CategoryDTO categ2 = new CategoryDTO();
        categ1.setId(ID1);
        categ1.setName(NUM_2);

        List<CategoryDTO> categList = Arrays.asList(categ1, categ2);

        when(categoryService.getAllCategories()).thenReturn(categList);

        mockMvc.perform(get("/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    void getCategoryByName() throws Exception {

        CategoryDTO categ1 = new CategoryDTO();
        categ1.setId(ID);
        categ1.setName(NUM_1);

        when(categoryService.getCategoryByName(anyString())).thenReturn(categ1);

        mockMvc.perform(get("/api/v1/categories/"+NUM_1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NUM_1)));

    }
}
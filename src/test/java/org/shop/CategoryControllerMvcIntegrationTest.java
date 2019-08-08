package org.shop;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shop.model.input.CategoryInputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.shop.TestConstant.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-category-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-category-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CategoryControllerMvcIntegrationTest {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;


    @Test
    public void getCategoryById_WithNotExistCategoryId() throws Exception{
        this.mockMvc.perform(get("/api/shop/categories/{categoryId}", NOT_EXIST_CATEGORYID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getCategoryById_WithInvalidCategoryId() throws Exception{
        this.mockMvc.perform(get("/api/shop/categories/{categoryId}", INVALID_CATEGORYID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void getById() throws Exception{
        this.mockMvc.perform(get("/api/shop/categories/{categoryId}", PHONE_CATEGORYID)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));

    }

    @Sql(value = {"/create-category-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    public void saveCategory()throws Exception{
        CategoryInputModel categoryInputModel = new CategoryInputModel("Phone", "New Phone");
        this.mockMvc.perform(post("/api/shop/categories")
                .content(objectMapper.writeValueAsString(categoryInputModel))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Phone")))
                .andExpect(jsonPath("$.description", is("New Phone")));
    }

    @Test
    public void updateCategory()throws Exception{
        CategoryInputModel categoryInputModel = new CategoryInputModel("Tel", "New Tel");

        this.mockMvc.perform(put("/api/shop/categories/{categoriesId}", PHONE_CATEGORYID)
        .content(objectMapper.writeValueAsString(categoryInputModel))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Tel")))
                .andExpect(jsonPath("$.description", is("New Tel")));
    }

}

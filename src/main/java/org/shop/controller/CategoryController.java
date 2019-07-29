package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.shop.dao.entity.Category;
import org.shop.dao.repository.CategoryRepository;
import org.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
@Api(value = "Category", description = "Category of product", basePath = "/api/shop")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoriesRepositories;


    @ApiOperation(value = "Get all category")
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoriesRepositories.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @ApiOperation(value = "Create new category")
    @PostMapping("/categories")
    private ResponseEntity<Category> createNewCategory(
            @ApiParam(value = "Request body Category", required = true)@Valid @RequestBody Category category){
        categoriesRepositories.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @ApiOperation(value = "Update category")
    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<Category> updateCategories(
            @ApiParam(value = "Category param id", example = "1") @PathVariable Long categoryId,
            @ApiParam(value = "Request body Category", required = true)@Valid @RequestBody Category category){
        Category categoryDB = categoryService.update(categoryId, category);
        return new ResponseEntity<>(categoryDB, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete category by category param: id")
    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(
            @ApiParam(value = "Category param id", example = "1")@PathVariable Long categoryId){
        categoriesRepositories.deleteById(categoryId);
    }
}

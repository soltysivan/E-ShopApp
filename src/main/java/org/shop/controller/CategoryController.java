package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.shop.dao.entity.Category;
import org.shop.dao.repository.CategoryRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.shop.model.input.CategoryInputModel;
import org.shop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop/categories")
@Api(value = "Category", description = "Category of product", basePath = "/api/shop")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoriesRepositories;


    @ApiOperation(value = "Get all category")
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoriesRepositories.findAll();
        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get category by: id")
    @GetMapping("{categoriesId}")
    public ResponseEntity<Category> getCategoryById(
            @ApiParam(value = "Category param id", example = "1")@PathVariable Long categoriesId){
        Category category = categoriesRepositories.findById(categoriesId)
                .orElseThrow(NotFoundExceptions::new);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @ApiOperation(value = "Create new category")
    @PostMapping
    private ResponseEntity<Category> createNewCategory(
            @ApiParam(value = "Request body Category", required = true)@Valid @RequestBody CategoryInputModel categoryInputModel){
        Category category = categoryService.saveCategory(categoryInputModel);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update category")
    @PutMapping("{categoriesId}")
    public ResponseEntity<Category> updateCategories(
            @ApiParam(value = "Category param id", example = "1") @PathVariable Long categoriesId,
            @ApiParam(value = "Request body Category", required = true)@Valid @RequestBody Category category){
        Category categoryDB = categoryService.update(categoriesId, category);
        return new ResponseEntity<>(categoryDB, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete category by category param: id")
    @DeleteMapping("{categoriesId}")
    public void deleteCategory(
            @ApiParam(value = "Category param id", example = "1")@PathVariable Long categoriesId){
        categoriesRepositories.deleteById(categoriesId);
    }
}

package org.shop.controller;

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
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoriesRepositories;

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoriesRepositories.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/categories")
    private ResponseEntity<Category> createNewCategory(@Valid @RequestBody Category category){
        categoriesRepositories.save(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/categories/{categoryId}")
    public ResponseEntity<Category> updateCategories(@PathVariable Long categoryId,
                                                     @Valid @RequestBody Category category){
        Category categoryDB = categoryService.update(categoryId, category);
        return new ResponseEntity<>(categoryDB, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        categoriesRepositories.deleteById(categoryId);
    }
}

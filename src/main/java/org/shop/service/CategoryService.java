package org.shop.service;

import org.shop.dao.entity.Category;
import org.shop.dao.repository.CategoryRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.shop.model.input.CategoryInputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoriesRepositories;

    public Category update(Long id, Category category) {
        Category categoryFromDB = categoriesRepositories.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        categoryFromDB.setDescription(category.getDescription());
        categoryFromDB.setName(category.getName());
        return categoriesRepositories.save(categoryFromDB);
    }

    public Category saveCategory(CategoryInputModel categoryInputModel) {
        Category category = CategoryInputModel.of(categoryInputModel);
        List<Category> categories = categoriesRepositories.findAll()
                .stream().filter(category1 -> category1.getName().equals(category.getName()))
                .collect(Collectors.toList());
       if(categories.size() != 0){
           throw new IllegalArgumentException("Category width the name is already exists");
       }
        return categoriesRepositories.save(category);
    }
}

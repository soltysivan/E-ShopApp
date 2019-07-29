package org.shop.service;

import org.shop.dao.entity.Category;
import org.shop.dao.repository.CategoryRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

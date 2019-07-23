package org.shop.service;

import org.shop.dao.entity.Category;
import org.shop.dao.entity.Product;
import org.shop.dao.repository.CategoryRepository;
import org.shop.dao.repository.OrderItemRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CategoryRepository categoriesRepositories;

    public List<Product> findAllByCategory(Long id) {
        return productRepository.findAll()
                .stream().filter(product -> product.getCategory().getId().equals(id))
                .collect(Collectors.toList());
    }

    public Product saveProduct(Product product, Long categoryId) {
        Category category = categoriesRepositories.findById(categoryId)
                .orElseThrow(NotFoundExceptions::new);
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product update(Long id, Product product) {
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        BeanUtils.copyProperties(product, productFromDB, "id", "category", "articles", "comments", "orderItems");
        return productRepository.save(productFromDB);
    }


}

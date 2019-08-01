package org.shop.service;

import org.shop.dao.entity.Category;
import org.shop.dao.entity.Product;
import org.shop.dao.repository.CategoryRepository;
import org.shop.dao.repository.OrderItemRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.shop.model.input.ProductInputModel;
import org.shop.model.output.ProductOutputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CategoryRepository categoriesRepositories;

    public List<ProductOutputModel> findAllByCategory(Long id) {
        List<Product> products = productRepository.findAll()
                .stream().filter(product -> product.getCategory().getId().equals(id))
                .collect(Collectors.toList());
        List<ProductOutputModel> productOutputModels = new ArrayList<>();
        products.forEach(product -> productOutputModels.add(ProductOutputModel.of(product)));
        return productOutputModels;
    }

    public ProductOutputModel saveProduct(ProductInputModel productInputModel) {
        Category category = categoriesRepositories.findById(productInputModel.getCategoryId())
                .orElseThrow(NotFoundExceptions::new);
        Product product = ProductInputModel.of(productInputModel);
        product.setCategory(category);
        productRepository.save(product);
        return ProductOutputModel.of(product);
    }

    public ProductOutputModel updateProduct(Long id, Product product) {
        Product productFromDB = productRepository.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        productFromDB.setDescription(product.getDescription());
        productFromDB.setName(product.getName());
        productFromDB.setPhoto(product.getPhoto());
        productFromDB.setPrice(product.getPrice());
        productFromDB.setQuantity(product.getQuantity());
        productRepository.save(productFromDB);
        return ProductOutputModel.of(product);
    }


    public List<ProductOutputModel> findAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductOutputModel> productOutputModels = new ArrayList<>();
        products.forEach(product -> productOutputModels.add(ProductOutputModel.of(product)));
        return productOutputModels;
    }

    public ProductOutputModel findProductById(Long paroductsId) {
        ProductOutputModel productOutputModel = ProductOutputModel.of(productRepository.findById(paroductsId)
                .orElseThrow(NotFoundExceptions::new));
        return productOutputModel;
    }
}

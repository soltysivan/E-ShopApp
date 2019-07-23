package org.shop.controller;

import org.shop.dao.entity.Product;
import org.shop.dao.repository.ProductRepository;
import org.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<Product>> getAllProductsByCategory(@RequestParam Long categoryId){
        List<Product> products = productService.findAllByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/categories/{categoryId}/product")
    private ResponseEntity<Product> createNewProduct(@PathVariable Long categoryId,
                                                     @Valid @RequestBody Product product){
        productService.saveProduct(product, categoryId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProducts(@PathVariable Long productId,
                                                  @Valid @RequestBody Product product){
        Product productDB = productService.update(productId, product);
        return new ResponseEntity<>(productDB, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productRepository.deleteById(productId);
    }
}

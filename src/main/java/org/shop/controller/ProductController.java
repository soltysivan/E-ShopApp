package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "Product", description = "All functions width Product", basePath = "/api/shop")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @ApiOperation(value = "Get all products")
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all products by category pram: id")
    @GetMapping("/categories/{categoryId}/products")
    public ResponseEntity<List<Product>> getAllProductsByCategory(
            @ApiParam(value = "Category param id", example = "1", required = true) @RequestParam Long categoryId){
        List<Product> products = productService.findAllByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Create new product")
    @PostMapping("/categories/{categoryId}/product")
    private ResponseEntity<Product> createNewProduct(
            @ApiParam(value = "Category param id", example = "1")@PathVariable Long categoryId,
            @ApiParam(value = "Request body Product", required = true)@Valid @RequestBody Product product){
        productService.saveProduct(product, categoryId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @ApiOperation(value = "Update product")
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProducts(
            @ApiParam(value = "Category param id", example = "1")@PathVariable Long productId,
            @ApiParam(value = "Request body Product", required = true)@Valid @RequestBody Product product){
        Product productDB = productService.update(productId, product);
        return new ResponseEntity<>(productDB, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete product by param: id")
    @DeleteMapping("/products/{productId}")
    public void deleteProduct(
            @ApiParam(value = "Category param id", example = "1")@PathVariable Long productId){
        productRepository.deleteById(productId);
    }
}

package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.shop.dao.entity.Product;
import org.shop.dao.repository.ProductRepository;
import org.shop.model.input.ProductInputModel;
import org.shop.model.output.ProductOutputModel;
import org.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop/products")
@Api(value = "Product", description = "All functions with Product", basePath = "/api/shop/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @ApiOperation(value = "Get all products")
    @GetMapping
    public ResponseEntity<List<ProductOutputModel>> getAllProducts(){
        List<ProductOutputModel> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all products by category pram: id")
    @GetMapping("categories/{categoryId}")
    public ResponseEntity<List<ProductOutputModel>> getAllProductsByCategory(
            @ApiParam(value = "Category param id", example = "1", required = true) @RequestParam Long categoryId){
        List<ProductOutputModel> products = productService.findAllByCategory(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Get product by id")
    @GetMapping("{productId}")
    public ResponseEntity<ProductOutputModel> getProductById(
            @ApiParam(value = "Product param id", example = "1")@PathVariable Long productId){
        ProductOutputModel product = productService.findProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @ApiOperation(value = "Get products by many [productsId]")
    @GetMapping("/list")
    public ResponseEntity<List<Product>> getProducts(
            @ApiParam(value = "Request param String [productsId]", required = true)@RequestParam String[] productId){
        List<Product> products = productService.findProductsById(productId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @ApiOperation(value = "Create new product")
    @PostMapping
    private ResponseEntity<ProductOutputModel> createNewProduct(
            @ApiParam(value = "Request body Product", required = true)@Valid @RequestBody ProductInputModel productInputModel){
        ProductOutputModel product = productService.saveProduct(productInputModel);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update product")
    @PutMapping("{productId}")
    public ResponseEntity<ProductOutputModel> updateProducts(
            @ApiParam(value = "Category param id", example = "1")@PathVariable Long productId,
            @ApiParam(value = "Request body Product", required = true)@Valid @RequestBody ProductInputModel productInputModel){
        ProductOutputModel productDB = productService.updateProduct(productId, productInputModel);
        return new ResponseEntity<>(productDB, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete product by param: id")
    @DeleteMapping("{productId}")
    public void deleteProduct(
            @ApiParam(value = "Category param id", example = "1")@PathVariable Long productId){
        productRepository.deleteById(productId);
    }
}

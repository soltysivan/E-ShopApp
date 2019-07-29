package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.shop.dao.entity.Article;
import org.shop.dao.repository.ArticleRepository;
import org.shop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
@Api(value = "Article", description = "Article to product", basePath = "/api/shop")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @ApiOperation(value = "Get all articles", response = Article.class)
    @GetMapping("/products/{productId}/articles")
    public ResponseEntity<List<Article>> getAllArticlesByProduct(
            @ApiParam(value = "Product param id", example = "1")@PathVariable Long productId){
        List<Article> articles = articleService.findAllByProductId(productId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @ApiOperation(value = "Create article to product by param: id")
    @PostMapping("/products/{productId}/articles")
    private ResponseEntity<Article> createNewArticleToProduct(
            @ApiParam(value = "Product param id", example = "1")@PathVariable Long productId,
            @ApiParam(value = "Request body Article", required = true)@Valid @RequestBody Article article){
        Article articleDB = articleService.saveArticle(productId, article);
        return new ResponseEntity<>(articleDB, HttpStatus.OK);
    }

    @ApiOperation(value = "Update articles to category")
    @PutMapping("/articles/{articleId}")
    public ResponseEntity<Article> updateArticle(
            @ApiParam(value = "Article param id", example = "1")@PathVariable Long articleId,
            @ApiParam(value = "Request body Article ", required = true)@Valid @RequestBody Article article){
        Article articleDB = articleService.update(articleId, article);
        return new ResponseEntity<>(articleDB, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete article by param: id")
    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(
            @ApiParam(value = "Article param id", example = "1")@PathVariable Long articleId){
        articleRepository.deleteById(articleId);
    }
}

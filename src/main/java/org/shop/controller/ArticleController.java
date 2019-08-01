package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.shop.dao.entity.Article;
import org.shop.dao.repository.ArticleRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.shop.model.input.ArticleInputModel;
import org.shop.model.output.ArticleOutputModel;
import org.shop.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop/articles")
@Api(value = "Article", description = "Article to product", basePath = "/api/shop/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @ApiOperation(value = "Get all articles", response = Article.class)
    @GetMapping("/products/{productId}")
    public ResponseEntity<List<ArticleOutputModel>> getAllArticlesByProduct(
            @ApiParam(value = "Product param id", example = "1")@PathVariable Long productId){
        List<ArticleOutputModel> articles = articleService.findAllByProductId(productId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @ApiOperation(value = "Get article by param id")
    @GetMapping("{articleId}")
    public ResponseEntity<ArticleOutputModel> getArticleById(
            @ApiParam(value = "Article param id", example = "1")@PathVariable Long articleId){
        ArticleOutputModel article = articleService.findArticleById(articleId);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @ApiOperation(value = "Create article to product by param : product_id")
    @PostMapping
    private ResponseEntity<ArticleOutputModel> createNewArticleToProduct(
            @ApiParam(value = "Request body Article", required = true)@Valid @RequestBody ArticleInputModel articleInputModel){
        ArticleOutputModel articleDB = articleService.saveArticle(articleInputModel);
        return new ResponseEntity<>(articleDB, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update articles to category")
    @PutMapping("{articleId}")
    public ResponseEntity<ArticleOutputModel> updateArticle(
            @ApiParam(value = "Article param id", example = "1")@PathVariable Long articleId,
            @ApiParam(value = "Request body Article ", required = true)@Valid @RequestBody Article article){
        ArticleOutputModel articleDB = articleService.update(articleId, article);
        return new ResponseEntity<>(articleDB, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete article by param: id")
    @DeleteMapping("{articleId}")
    public void deleteArticle(
            @ApiParam(value = "Article param id", example = "1")@PathVariable Long articleId){
        articleRepository.deleteById(articleId);
    }
}

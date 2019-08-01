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
@Api(value = "Article", description = "Article to product", basePath = "/api/shop")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @ApiOperation(value = "Get all articles", response = Article.class)
    @GetMapping("/products/{productsId}")
    public ResponseEntity<List<ArticleOutputModel>> getAllArticlesByProduct(
            @ApiParam(value = "Product param id", example = "1")@PathVariable Long productsId){
        List<ArticleOutputModel> articles = articleService.findAllByProductId(productsId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @ApiOperation(value = "Get article by param id")
    @GetMapping("{articlesId}")
    public ResponseEntity<ArticleOutputModel> getArticleById(
            @ApiParam(value = "Article param id", example = "1")@PathVariable Long articlesId){
        ArticleOutputModel article = articleService.findArticleById(articlesId);
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
    @PutMapping("{articlesId}")
    public ResponseEntity<ArticleOutputModel> updateArticle(
            @ApiParam(value = "Article param id", example = "1")@PathVariable Long articlesId,
            @ApiParam(value = "Request body Article ", required = true)@Valid @RequestBody Article article){
        ArticleOutputModel articleDB = articleService.update(articlesId, article);
        return new ResponseEntity<>(articleDB, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete article by param: id")
    @DeleteMapping("{articlesId}")
    public void deleteArticle(
            @ApiParam(value = "Article param id", example = "1")@PathVariable Long articlesId){
        articleRepository.deleteById(articlesId);
    }
}

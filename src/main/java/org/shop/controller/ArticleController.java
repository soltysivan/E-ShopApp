package org.shop.controller;

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
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/products/{productId}/articles")
    public ResponseEntity<List<Article>> getAllArticlesByProduct(@PathVariable Long productId){
        List<Article> articles = articleService.findAllByProductId(productId);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping("/products/{productId}/articles")
    private ResponseEntity<Article> createNewArticleWidthProduct(@PathVariable Long productId,
                                                                 @Valid @RequestBody Article article){
        Article articleDB = articleService.saveArticle(productId, article);
        return new ResponseEntity<>(articleDB, HttpStatus.OK);
    }

    @PutMapping("/articles/{articleId}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long articleId,
                                                 @Valid @RequestBody Article article){
        Article articleDB = articleService.update(articleId, article);
        return new ResponseEntity<>(articleDB, HttpStatus.OK);
    }

    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(@PathVariable Long articleId){
        articleRepository.deleteById(articleId);
    }
}

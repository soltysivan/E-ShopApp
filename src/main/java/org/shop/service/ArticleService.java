package org.shop.service;

import org.shop.dao.entity.Article;
import org.shop.dao.entity.Product;
import org.shop.dao.repository.ArticleRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProductRepository productRepository;

    public Article update(Long articleId, Article article) {
        Article articleDB = articleRepository.findById(articleId)
                .orElseThrow(NotFoundExceptions::new);
        BeanUtils.copyProperties(article, articleDB, "id", "product");
        return articleRepository.save(articleDB);
    }

    public Article saveArticle(Long productId, Article article) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundExceptions::new);
        article.setProduct(product);
        return articleRepository.save(article);
    }

    public List<Article> findAllByProductId(Long id) {
        List<Article> articles = articleRepository.findAll()
                .stream().filter(article -> article.getProduct().getId().equals(id))
                .collect(Collectors.toList());
        return articles;
    }
}

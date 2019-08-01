package org.shop.service;

import org.shop.dao.entity.Article;
import org.shop.dao.entity.Product;
import org.shop.dao.repository.ArticleRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.shop.model.input.ArticleInputModel;
import org.shop.model.output.ArticleOutputModel;
import org.shop.model.output.ProductOutputModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProductRepository productRepository;

    public ArticleOutputModel update(Long articleId, Article article) {
        Article articleDB = articleRepository.findById(articleId)
                .orElseThrow(NotFoundExceptions::new);
        articleDB.setText(article.getText());
        articleDB.setName(article.getName());
        articleRepository.save(articleDB);
        return ArticleOutputModel.of(articleDB);
    }

    public ArticleOutputModel saveArticle(ArticleInputModel articleInputModel) {
        Product product = productRepository.findById(articleInputModel.getProductId())
                .orElseThrow(NotFoundExceptions::new);
        Article article = ArticleInputModel.of(articleInputModel);
        article.setProduct(product);
        articleRepository.save(article);
        return ArticleOutputModel.of(article);
    }

    public List<ArticleOutputModel> findAllByProductId(Long id) {
        List<Article> articles = articleRepository.findAll()
                .stream().filter(article -> article.getProduct().getId().equals(id))
                .collect(Collectors.toList());
        List<ArticleOutputModel> articleOutputModels = new ArrayList<>();
        articles.forEach(article -> articleOutputModels.add(ArticleOutputModel.of(article)));
        return articleOutputModels;
    }

    public ArticleOutputModel findArticleById(Long articlesId) {
        ArticleOutputModel article = ArticleOutputModel.of(articleRepository.findById(articlesId)
                .orElseThrow(NotFoundExceptions::new));
        return article;
    }
}

package org.shop.model.output;

import lombok.Getter;
import org.shop.dao.entity.Article;

import java.util.Date;

@Getter
public class ArticleOutputModel {
    private final Long id;
    private final String name;
    private final String text;
    private final Date createdAt;
    private final Long productId;

    public ArticleOutputModel(Long id, String name, String text, Date createdAt, Long productId) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.createdAt = createdAt;
        this.productId = productId;
    }

    public static ArticleOutputModel of (Article article){
        return new ArticleOutputModel(
                article.getId(),
                article.getName(),
                article.getText(),
                article.getCreatedAt(),
                article.getProduct().getId()
        );
    }
}

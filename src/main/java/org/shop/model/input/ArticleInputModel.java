package org.shop.model.input;

import lombok.Getter;
import lombok.Setter;
import org.shop.dao.entity.Article;

import java.util.Date;

@Getter
@Setter
public class ArticleInputModel {
    private String name;
    private String text;
    private Date createdAt;
    private Long productId;

    public static Article of(ArticleInputModel articleInputModel){
        return new Article(
                articleInputModel.getName(),
                articleInputModel.getText(),
                articleInputModel.getCreatedAt()
        );
    }


}

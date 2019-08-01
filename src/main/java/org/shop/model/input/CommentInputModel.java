package org.shop.model.input;

import lombok.Getter;
import lombok.Setter;
import org.shop.dao.entity.Comment;

import java.util.Date;

@Getter
@Setter
public class CommentInputModel {
    private String text;
    private Long userId;
    private Long productId;
    private Date createdAt;
    private Date updatedAt;
    private Long comment_id;

    public static Comment of(CommentInputModel commentInputModel){
        return new Comment(
                commentInputModel.getText(),
                commentInputModel.getCreatedAt(),
                commentInputModel.getUpdatedAt()
        );
    }
}

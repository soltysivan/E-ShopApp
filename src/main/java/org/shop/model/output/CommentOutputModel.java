package org.shop.model.output;


import lombok.Getter;
import org.shop.dao.entity.Comment;

import java.util.Date;
import java.util.Optional;

@Getter
public class CommentOutputModel {

    private final Long id;
    private final String text;
    private final Date createdAt;
    private final Date updatedAt;
    private final String username;
    private final Long productId;
    private final Long commentId;

    public CommentOutputModel(Long id, String text, Date createdAt, Date updatedAt, String username, Long productId, Long commentId) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.username = username;
        this.productId = productId;
        this.commentId = commentId;
    }

    public static CommentOutputModel of(Comment comment){
        return new CommentOutputModel(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                comment.getUpdatedAt(),
                comment.getUser().getUsername(),
                comment.getProduct().getId(),
                Optional.ofNullable(comment.getParent()).map(Comment::getId).orElse(0L));
    }
}

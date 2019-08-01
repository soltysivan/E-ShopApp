package org.shop.model.output;


import lombok.Getter;
import org.shop.dao.entity.Comment;

import java.util.Date;

@Getter
public class CommentOutputModel {

    private final Long id;
    private final String text;
    private final Date createdAt;
    private final String username;
    private final Long commentId;

    public CommentOutputModel(Long id, String text, Date createdAt, String username, Long commentId){
        this.id = id;
        this.text = text;
        this.createdAt= createdAt;
        this.username = username;
        this.commentId = commentId;
    }

    public static CommentOutputModel of(Comment comment){
        return new CommentOutputModel(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                comment.getUser().getUsername(),
                comment.getComment());
    }
}

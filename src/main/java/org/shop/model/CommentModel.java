package org.shop.model;


import lombok.Getter;
import org.shop.dao.entity.Comment;

import java.util.Date;

@Getter
public class CommentModel {

    private final Long id;
    private final String text;
    private final Date createdAt;
    private final String username;
    private final Long commentId;

    public CommentModel (Long id, String text, Date createdAt, String username, Long commentId){
        this.id = id;
        this.text = text;
        this.createdAt= createdAt;
        this.username = username;
        this.commentId = commentId;
    }

    public static CommentModel of(Comment comment){
        return new CommentModel(
                comment.getId(),
                comment.getText(),
                comment.getCreatedAt(),
                comment.getUser().getUsername(),
                comment.getComment());
    }
}

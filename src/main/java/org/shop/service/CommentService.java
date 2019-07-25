package org.shop.service;

import org.shop.dao.entity.Comment;
import org.shop.dao.entity.Product;
import org.shop.dao.entity.User;
import org.shop.dao.repository.CommentRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.dao.repository.UserRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Comment> findAllCommentsByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundExceptions::new);
        List<Comment> comments = product.getComments();
        return comments;
    }

    public Comment saveComment(Long productId, Long authorId, Comment comment) {
        User author = userRepository.findById(authorId)
                .orElseThrow(NotFoundExceptions::new);
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundExceptions::new);
        comment.setAuthorComments(author);
        comment.setProduct(product);
        commentRepository.save(comment);
        return comment;
    }

    public Comment updateComment(Long id, Comment comment) {
        Comment commentFromDB = commentRepository.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        BeanUtils.copyProperties(comment, commentFromDB, "id", "authorComments", "products");
        return commentRepository.save(commentFromDB);
    }
}

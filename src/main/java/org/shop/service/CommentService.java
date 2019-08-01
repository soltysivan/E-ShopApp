package org.shop.service;

import org.shop.dao.entity.Comment;
import org.shop.dao.entity.Product;
import org.shop.dao.entity.User;
import org.shop.dao.repository.CommentRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.dao.repository.UserRepository;
import org.shop.exceptions.NotFoundExceptions;
import org.shop.model.input.CommentInputModel;
import org.shop.model.output.CommentOutputModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public List<CommentOutputModel> findAllCommentsByProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(NotFoundExceptions::new);
        List<Comment> comments = product.getComments();
        List<CommentOutputModel> commentOutputModels = new ArrayList<>();
        comments.forEach(comment -> commentOutputModels.add(CommentOutputModel.of(comment)));
        return commentOutputModels;
    }

    public CommentOutputModel saveComment(CommentInputModel commentInputModel) {
        User author = userRepository.findById(commentInputModel.getUserId())
                .orElseThrow(NotFoundExceptions::new);
        Product product = productRepository.findById(commentInputModel.getProductId())
                .orElseThrow(NotFoundExceptions::new);
        Comment comment = CommentInputModel.of(commentInputModel);
        comment.setUser(author);
        comment.setProduct(product);
        commentRepository.save(comment);
        return CommentOutputModel.of(comment);
    }

    public Comment updateComment(Long id, Comment comment) {
        Comment commentFromDB = commentRepository.findById(id)
                .orElseThrow(NotFoundExceptions::new);
        commentFromDB.setText(comment.getText());
        return commentRepository.save(commentFromDB);
    }

    public Comment saveCommentsToComment(Long commentId, Comment comment) {
        Comment commentFromDb = commentRepository.findById(commentId)
                .orElseThrow(NotFoundExceptions::new);
        comment.setUser(commentFromDb.getUser());
        comment.setProduct(null);
        comment.setComment(commentFromDb.getId());
        commentRepository.save(comment);
        return comment;
    }

    public List<CommentOutputModel> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentOutputModel> commentOutputModels = new ArrayList<>();
        comments.forEach(comment -> commentOutputModels.add(CommentOutputModel.of(comment)));
        return commentOutputModels;
    }
}

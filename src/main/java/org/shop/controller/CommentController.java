package org.shop.controller;

import org.shop.dao.entity.Comment;
import org.shop.dao.repository.CommentRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.model.input.CommentInputModel;
import org.shop.model.output.CommentOutputModel;
import org.shop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<CommentOutputModel>> getAll(){
        List<CommentOutputModel> commentOutputModels = commentService.findAllComments();
        return new ResponseEntity<>(commentOutputModels, HttpStatus.OK);
    }

    @GetMapping("products/{productId}")
    public ResponseEntity<List<CommentOutputModel>> getAllCommentsByProduct(@PathVariable Long productId){
        List<CommentOutputModel> comments = commentService.findAllCommentsByProduct(productId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CommentOutputModel> createNewComment(@Valid @RequestBody CommentInputModel commentInputModel){
        CommentOutputModel commentFromDB = commentService.saveComment(commentInputModel);
        return new ResponseEntity<>(commentFromDB, HttpStatus.CREATED);
    }

    @PostMapping("/comments/{commentId}/comment")
    public ResponseEntity<CommentOutputModel> createCommentsToComment(@PathVariable Long commentId,
                                                                      @Valid @RequestBody Comment comment){
        CommentOutputModel commentFromDB = CommentOutputModel.of(commentService.saveCommentsToComment(commentId, comment));
        return new ResponseEntity<>(commentFromDB, HttpStatus.OK);
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentOutputModel> updateOrderItem(@PathVariable Long commentId,
                                                              @Valid @RequestBody Comment comment){
        CommentOutputModel commentFromDB = CommentOutputModel.of(commentService.updateComment(commentId, comment));
        return new ResponseEntity<>(commentFromDB, HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteOrderItem(@PathVariable Long commentId){
        commentRepository.deleteById(commentId);
    }
}

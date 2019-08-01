package org.shop.controller;

import org.shop.dao.entity.Comment;
import org.shop.dao.repository.CommentRepository;
import org.shop.dao.repository.ProductRepository;
import org.shop.model.output.CommentOutputModel;
import org.shop.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
public class CommentController {

//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private CommentService commentService;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @GetMapping("/comments")
//    public ResponseEntity<List<Comment>> getAll(){
//        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping("/products/{productId}/comments")
//    public ResponseEntity<List<Comment>> getAllCommentsByProduct(@PathVariable Long productId){
//        List<Comment> comments = commentService.findAllCommentsByProduct(productId);
//        return new ResponseEntity<>(comments, HttpStatus.OK);
//    }
//
//    @PostMapping("/products/{productId}/authors/{authorId}/comments")
//    public ResponseEntity<CommentOutputModel> createNewComment(@PathVariable Long productId,
//                                                               @PathVariable Long authorId,
//                                                               @Valid @RequestBody Comment comment){
//        CommentOutputModel commentFromDB = CommentOutputModel.of(commentService.saveComment(productId, authorId, comment));
//        return new ResponseEntity<>(commentFromDB, HttpStatus.OK);
//    }
//
//    @PostMapping("/comments/{commentId}/comment")
//    public ResponseEntity<CommentOutputModel> createCommentsToComment(@PathVariable Long commentId,
//                                                                      @Valid @RequestBody Comment comment){
//        CommentOutputModel commentFromDB = CommentOutputModel.of(commentService.saveCommentsToComment(commentId, comment));
//        return new ResponseEntity<>(commentFromDB, HttpStatus.OK);
//    }
//
//    @PutMapping("/comments/{commentId}")
//    public ResponseEntity<CommentOutputModel> updateOrderItem(@PathVariable Long commentId,
//                                                              @Valid @RequestBody Comment comment){
//        CommentOutputModel commentFromDB = CommentOutputModel.of(commentService.updateComment(commentId, comment));
//        return new ResponseEntity<>(commentFromDB, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/comments/{commentId}")
//    public void deleteOrderItem(@PathVariable Long commentId){
//        commentRepository.deleteById(commentId);
//    }
}

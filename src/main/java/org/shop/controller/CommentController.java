package org.shop.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "Comment", description = "Comments of product", basePath = "/api/shop/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductRepository productRepository;

    @ApiOperation(value = "Get all comments")
    @GetMapping
    public ResponseEntity<List<CommentOutputModel>> getAll(){
        List<CommentOutputModel> commentOutputModels = commentService.findAllComments();
        return new ResponseEntity<>(commentOutputModels, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all comments by param product id")
    @GetMapping("products/{productId}")
    public ResponseEntity<List<CommentOutputModel>> getAllCommentsByProduct(
            @ApiParam(value = "Comments param id", example = "1")@PathVariable Long productId){
        List<CommentOutputModel> comments = commentService.findAllCommentsByProduct(productId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @ApiOperation(value = "Get sub_comments by comment id")
    @GetMapping("sub-comments/{commentId}")
    public ResponseEntity<List<CommentOutputModel>> getSubCommentsByCommentId(
            @ApiParam(value = "Comments param id", example = "1")@PathVariable Long commentId){
        List<CommentOutputModel> commentOutputModels = commentService.findAllByParent(commentId);
        return new ResponseEntity<>(commentOutputModels, HttpStatus.OK);
    }

    @ApiOperation(value = "Create new comments")
    @PostMapping
    public ResponseEntity<CommentOutputModel> createNewComment(
            @ApiParam(value = "Request body Comment", required = true)@Valid @RequestBody CommentInputModel commentInputModel){
        CommentOutputModel commentFromDB = commentService.saveComment(commentInputModel);
        return new ResponseEntity<>(commentFromDB, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Create new sub_comments")
    @PostMapping("sub-comments")
    public ResponseEntity<CommentOutputModel> createSubComments(
            @ApiParam(value = "Request body Comments", required = true)@Valid @RequestBody CommentInputModel commentInputModel){
        CommentOutputModel commentFromDB = commentService.saveCommentsToComment(commentInputModel);
        return new ResponseEntity<>(commentFromDB, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update comments")
    @PutMapping("{commentId}")
    public ResponseEntity<CommentOutputModel> updateComments(
            @ApiParam(value = "Comments param id", example = "1")@PathVariable Long commentId,
            @ApiParam(value = "Request body Text to comment", required = true)@Valid @RequestParam String text){
        CommentOutputModel commentFromDB = commentService.updateComment(commentId, text);
        return new ResponseEntity<>(commentFromDB, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete comments by param id")
    @DeleteMapping("{commentId}")
    public void deleteComments(
            @ApiParam(value = "Comments param id", example = "1")@PathVariable Long commentId){
        commentRepository.deleteById(commentId);
    }
}

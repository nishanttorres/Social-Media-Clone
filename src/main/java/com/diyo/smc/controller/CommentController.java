package com.diyo.smc.controller;

import com.diyo.smc.entity.Comment;
import com.diyo.smc.model.CommentDTO;
import com.diyo.smc.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentController {
    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAllComments(){
        return ResponseEntity.ok(commentService.findAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findCommentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.findCommentById(id));
    }

    @PostMapping("/addComment/{userId}/{postId}")
    public ResponseEntity<String> addNewComment(@PathVariable("userId") Long userID, @PathVariable("postId") Long postId, @RequestBody Comment comment){
        commentService.addNewComment(userID,postId,comment);
        return ResponseEntity.ok("Comment added successfully");
    }
    @PatchMapping
    public ResponseEntity<String> editComment(@RequestBody Comment comment){
        return ResponseEntity.ok(commentService.editComment(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.deleteCommentById(id));
    }
}

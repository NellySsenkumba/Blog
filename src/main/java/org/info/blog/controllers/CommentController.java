package org.info.blog.controllers;

import org.info.blog.models.Comment;
import org.info.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comment/")
public class CommentController {
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    private final CommentService commentService;

    @PostMapping("{userId}/{postId}/")
    public ResponseEntity<Comment> createComment(
            @PathVariable long postId,
            @PathVariable long userId,
            @RequestBody Comment comment
    ) {
        return commentService.createComment(userId, postId, comment);
    }

    @GetMapping("{postId}/")
    public ResponseEntity<List<Comment>> postComments(@PathVariable long postId) {
        return commentService.postComments(postId);
    }

}

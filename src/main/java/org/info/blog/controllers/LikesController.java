package org.info.blog.controllers;

import org.info.blog.models.Likes;
import org.info.blog.service.LikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/likes/")
public class LikesController {
    private final LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PostMapping("{userId}/{postId}/")
    public ResponseEntity<String> like(@PathVariable long userId, @PathVariable long postId) {
        return likesService.like(userId, postId);
    }

    @GetMapping("{postId}/")
    public ResponseEntity<Integer> postLikes(@PathVariable long postId) {
        return likesService.postLikes(postId);
    }

}

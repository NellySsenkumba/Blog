package org.info.blog.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.info.blog.models.Post;
import org.info.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/post/")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public ResponseEntity<Post> createPost(@NonNull HttpServletRequest request, @RequestBody Post post) {

        return postService.createPost(request, post);

    }

    @GetMapping("{authorId}/")
    public ResponseEntity<List<Post>> userPosts(@PathVariable long authorId) {
        return postService.userPosts(authorId);

    }

    @GetMapping
    public ResponseEntity<List<Post>> allPosts() {
        return postService.allPosts();
    }

    @PutMapping("{authorId}/{postId}")
    public ResponseEntity<Post> updatePost(@PathVariable long authorId, @PathVariable long postId, @RequestBody Post post) {
        return postService.updatePost(authorId, postId, post);
    }

    @DeleteMapping("{authorId}/{postId}")
    public ResponseEntity<Post> deletePost(@PathVariable long authorId, @PathVariable long postId) {
        return postService.deletePost(authorId, postId);
    }


}

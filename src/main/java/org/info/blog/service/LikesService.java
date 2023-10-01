package org.info.blog.service;

import org.info.blog.models.PostLikes;
import org.info.blog.models.Post;
import org.info.blog.repository.LikesRepository;
import org.info.blog.repository.PostRepository;
import org.info.blog.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesService {
    private final LikesRepository likesRepository;

    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    public LikesService(LikesRepository likesRepository, PostRepository postRepository, UsersRepository usersRepository) {
        this.likesRepository = likesRepository;
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
    }

    public ResponseEntity<String> like(long userId, long postId) {
        if (!usersRepository.existsById(userId) || !postRepository.existsById(postId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Post post = postRepository.findById(postId).orElseThrow();
        List<PostLikes> likes = post.getLikes();

        for (PostLikes like : likes) {
            if (like.getUser().getId() == userId) {
                post.getLikes().remove(like);
                likesRepository.delete(like);
                return new ResponseEntity<>("Post Unliked", HttpStatus.CREATED);
            }
        }
        PostLikes like = new PostLikes();
        like.setUser(usersRepository.findById(userId).orElseThrow());
        post.getLikes().add(like);
        likesRepository.save(like);
        postRepository.save(post);


        return new ResponseEntity<>("Post Liked", HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> postLikes(long postId) {
        Post post = postRepository.findById(postId).orElseThrow();

        return new ResponseEntity<>(post.getLikes().size(), HttpStatus.OK);
    }
}
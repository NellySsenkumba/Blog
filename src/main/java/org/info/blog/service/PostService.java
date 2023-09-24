package org.info.blog.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.info.blog.config.JwtService;
import org.info.blog.models.Post;
import org.info.blog.models.User;
import org.info.blog.repository.PostRepository;
import org.info.blog.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UsersRepository usersRepository;
    private final JwtService jwtService;


    public ResponseEntity<Post> createPost(HttpServletRequest request, Post post) {


        String email = jwtService.extractUsername(request.getHeader("Authorization").substring(7));

        User author = usersRepository.findByEmail(email).orElseThrow();


        if (post.getBody() == null || post.getTitle() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        post.setAuthor(author);
        post.setDate(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(postRepository.saveAndFlush(post), HttpStatus.CREATED);


    }

    public ResponseEntity<List<Post>> userPosts(long authorId) {
        if (!usersRepository.existsById(authorId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postRepository.findAllByAuthorId(authorId), HttpStatus.OK);
    }

    public ResponseEntity<List<Post>> allPosts() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Post> updatePost(long authorId, long postId, Post post) {
        if (!usersRepository.existsById(authorId) || !postRepository.existsById(postId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (post.getBody() == null || post.getTitle() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Post post1 = postRepository.findById(postId).orElseThrow();
        post1.setBody(post.getBody());
        post1.setTitle(post.getTitle());
        post1.setDate(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(postRepository.saveAndFlush(post1), HttpStatus.OK);
    }

    public ResponseEntity<Post> deletePost(long authorId, long postId) {
        if (!usersRepository.existsById(authorId) || !postRepository.existsById(postId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        postRepository.deleteById(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

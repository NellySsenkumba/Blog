package org.info.blog.service;

import org.info.blog.models.Comment;
import org.info.blog.repository.CommentRepository;
import org.info.blog.repository.PostRepository;
import org.info.blog.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UsersRepository usersRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UsersRepository usersRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.usersRepository = usersRepository;
    }


    public ResponseEntity<Comment> createComment(long userId, long postId, Comment comment) {
        if (!usersRepository.existsById(userId) || !postRepository.existsById(postId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (comment.getBody() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        comment.setAuthor(usersRepository.findById(userId).orElseThrow());
        comment.setPost(postRepository.findById(postId).orElseThrow());

        return new ResponseEntity<>(commentRepository.saveAndFlush(comment), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Comment>> postComments(long postId) {

        if (!postRepository.existsById(postId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentRepository.findAllByPostId(postId), HttpStatus.OK);
    }
}

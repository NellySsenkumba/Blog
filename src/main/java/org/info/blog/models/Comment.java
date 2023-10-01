package org.info.blog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private Post post;

    @OneToOne
    private Comment reply;

    @OneToMany(mappedBy = "comment")
    List<CommentLikes> likes;
}

package org.info.blog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;


    @ManyToOne(optional = false)
    private User author;

    @Column(
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            nullable = false,
            updatable = false
    )
    private Timestamp date;


    @ManyToMany
    @JoinTable(
            name = "post_categories",
            joinColumns = @JoinColumn(name = "post"),
            inverseJoinColumns = @JoinColumn(name = "category")
    )
    private List<Category> category;

    @OneToMany
    @JoinTable(
            name = "post_likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "likes_id")
    )
    private List<Likes> likes;


}

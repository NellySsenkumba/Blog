package org.info.blog.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CommentLikes{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @ManyToOne(optional = false)
    private User user;

    //post
    @ManyToOne(optional = false)
    private Comment comment;

}

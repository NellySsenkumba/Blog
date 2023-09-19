package org.info.blog.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true,nullable = false)
    private String url;
    private String caption;
    private String alt;
    private String title;
    private String description;

    @ManyToOne
    Post post;

}

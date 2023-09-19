package org.info.blog.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "category_sequence"

    )
    private String code;

    private String name;
    private String description;

    @ManyToMany
    private List<Category> categories;

}

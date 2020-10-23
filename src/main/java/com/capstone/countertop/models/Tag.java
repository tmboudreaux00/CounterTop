package com.capstone.countertop.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name="tags_recipes",
            joinColumns={@JoinColumn(name="tag_id")},
            inverseJoinColumns={@JoinColumn(name="recipe_id")}
    )
    private List<Recipe> tagsRecipes;

    public Tag(){}

    public Tag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

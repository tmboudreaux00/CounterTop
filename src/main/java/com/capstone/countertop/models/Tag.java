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

    //tags_recipes join table
    @ManyToMany
    @JoinTable(
            name="tags_recipes",
            joinColumns={@JoinColumn(name="tag_id")},
            inverseJoinColumns={@JoinColumn(name="recipe_id")}
    )
    private List<Recipe> tagsRecipes;

    public Tag(){}

    public Tag(long id, String name, List<Recipe> tagsRecipes) {
        this.id = id;
        this.name = name;
        this.tagsRecipes = tagsRecipes;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Recipe> getTagsRecipes() {
        return tagsRecipes;
    }

    public void setTagsRecipes(List<Recipe> tagsRecipes) {
        this.tagsRecipes = tagsRecipes;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

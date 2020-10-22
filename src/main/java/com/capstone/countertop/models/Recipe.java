package com.capstone.countertop.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @Size(min = 10, max = 10000)
    private String description;

    @Column(nullable = false)
    @Size(min = 4, max = 100)
    private String name;

    @Column(nullable = false)
    private String skillLevel;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "usersFavorites")
    private List<User> users;

    public Recipe() {}

    public Recipe(long id,
                  @Size(min = 10, max = 10000) String description,
                  @Size(min = 4, max = 100) String name,
                  String skillLevel,
                  String url,
                  User user) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.skillLevel = skillLevel;
        this.url = url;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

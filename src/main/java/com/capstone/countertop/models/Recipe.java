package com.capstone.countertop.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
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

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date datePublished;

    //user_id foreign key
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //foreign recipe_id key for comments table
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private List<Comment> comments;

    //foreign recipe_id key for likes table
    @OneToMany(cascade= CascadeType.ALL, mappedBy = "recipe")
    private List<Like> likes;

    //tags_recipes join table
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tagsRecipes")
    private List<Tag> tags;

    //users_favorites join table
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "usersFavorites")
    private List<User> users;

    //recipes_ingredients join table
    @ManyToMany
    @JoinTable(
            name="recipes_ingredients",
            joinColumns={@JoinColumn(name="recipe_id")},
            inverseJoinColumns={@JoinColumn(name="tag_id")}
    )
    private List<Ingredient> recipesIngredients;

    public Recipe() {}

    public Recipe(long id,
                  @Size(min = 10, max = 10000) String description,
                  @Size(min = 4, max = 100) String name,
                  String skillLevel,
                  String url,
                  Date datePublished,
                  User user,
                  List<Comment> comments,
                  List<User> users,
                  List<Like> likes,
                  List<Tag> tags,
                  List<Ingredient> recipesIngredients) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.skillLevel = skillLevel;
        this.url = url;
        this.datePublished = datePublished;
        this.user = user;
        this.comments = comments;
        this.users = users;
        this.likes = likes;
        this.tags = tags;
        this.recipesIngredients = recipesIngredients;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Ingredient> getRecipesIngredients() {
        return recipesIngredients;
    }

    public void setRecipesIngredients(List<Ingredient> recipesIngredients) {
        this.recipesIngredients = recipesIngredients;
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

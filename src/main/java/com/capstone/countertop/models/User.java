package com.capstone.countertop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 24)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    @Size(min = 8, max = 50)
    private String password;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date signupDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dob;

    private String url;

    //foreign user_id key for recipes table
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Recipe> recipes;

    //foreign user_id key for comments table
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;

    //foreign user_id key for likes table
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Like> likes;

    //join table for users_favorites
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="users_favorites",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="recipe_id")}
    )
    private List<Recipe> usersFavorites = new ArrayList<>();


    //join table for users_liked_recipes
    @ManyToMany
    @JoinTable(
            name="users_liked_recipes",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="like_id")}
    )
    private List<Like> usersLikedRecipes;

    //join table for users_liked_comments
    @ManyToMany
    @JoinTable(
            name="users_liked_comments",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns={@JoinColumn(name="comment_id")}
    )
    private List<Like> usersLikedComments;

    public User() {}

    public User(User copy) {
        this.id = copy.id;
        this.email = copy.email;
        this.username = copy.username;
        this.password = copy.password;
        this.signupDate = copy.signupDate;
        this.dob = copy.dob;
        this.url = copy.url;
        this.recipes = copy.recipes;
        this.comments = copy.comments;
        this.likes = copy.likes;
        this.usersFavorites = copy.usersFavorites;
        this.usersLikedRecipes = copy.usersLikedRecipes;
        this.usersLikedComments = copy.usersLikedComments;
    }

    public User(long id,
                String email,
                @Size(min = 3, max = 24) String username,
                @Size(min = 8, max = 50) String password,
                Date signupDate,
                Date dob,
                String url,
                List<Recipe> recipes,
                List<Comment> comments,
                List<Like> likes,
                List<Recipe> usersFavorites,
                List<Like> usersLikedRecipes,
                List<Like> usersLikedComments) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.signupDate = signupDate;
        this.dob = dob;
        this.url = url;
        this.recipes = recipes;
        this.comments = comments;
        this.likes = likes;
        this.usersFavorites = usersFavorites;
        this.usersLikedRecipes = usersLikedRecipes;
        this.usersLikedComments = usersLikedComments;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Recipe> getUsersFavorites() {
        return usersFavorites;
    }

    public void setUsersFavorites(List<Recipe> usersFavorites) {
        this.usersFavorites = usersFavorites;
    }

    public List<Like> getUsersLikedRecipes() {
        return usersLikedRecipes;
    }

    public void setUsersLikedRecipes(List<Like> usersLikedRecipes) {
        this.usersLikedRecipes = usersLikedRecipes;
    }

    public List<Like> getUsersLikedComments() {
        return usersLikedComments;
    }

    public void setUsersLikedComments(List<Like> usersLikedComments) {
        this.usersLikedComments = usersLikedComments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

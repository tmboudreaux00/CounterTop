package com.capstone.countertop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private boolean liked;

    //user_id foreign key
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //recipe_id foreign key
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    //comment_id foreign key
    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    //users_liked_recipes join table
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "usersLikedRecipes")
    private List<User> usersRecipesLiked;

    //users_liked_comments join table
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "usersLikedComments")
    private List<User> usersCommentsLiked;

    public Like (){}

    public Like(long id,
                boolean liked,
                User user,
                Recipe recipe,
                Comment comment,
                List<User> usersRecipesLiked,
                List<User> usersCommentsLiked) {
        this.id = id;
        this.liked = liked;
        this.user = user;
        this.recipe = recipe;
        this.comment = comment;
        this.usersRecipesLiked = usersRecipesLiked;
        this.usersCommentsLiked = usersCommentsLiked;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<User> getUsersRecipesLiked() {
        return usersRecipesLiked;
    }

    public void setUsersRecipesLiked(List<User> usersRecipesLiked) {
        this.usersRecipesLiked = usersRecipesLiked;
    }

    public List<User> getUsersCommentsLiked() {
        return usersCommentsLiked;
    }

    public void setUsersCommentsLiked(List<User> usersCommentsLiked) {
        this.usersCommentsLiked = usersCommentsLiked;
    }
}

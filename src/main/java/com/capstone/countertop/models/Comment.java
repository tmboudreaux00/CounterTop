package com.capstone.countertop.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @DateTimeFormat(pattern = "dd/MM/yyyy") // This is for bind Date with @ModelAttribute
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    @Size(min = 1, max = 500)
    private String commentBody;

    @Column
    private Boolean liked;

    //user_id foreign key
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //recipe_id foreign key
    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    //foreign comment_id key for likes table
    @OneToMany(cascade = CascadeType.ALL, mappedBy="comment")
    private List<Like> likes;

    public Comment() {}

    public Comment(long id,
                   Comment parentComment,
                   Date date,
                   @Size(min = 1, max = 500) String commentBody,
                   Boolean liked,
                   User user,
                   Recipe recipe,
                   List<Like> likes) {
        this.id = id;
        this.parentComment = parentComment;
        this.date = date;
        this.commentBody = commentBody;
        this.liked = liked;
        this.user = user;
        this.recipe = recipe;
        this.likes = likes;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
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
}

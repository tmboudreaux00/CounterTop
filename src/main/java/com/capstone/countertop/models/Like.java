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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "usersLiked")
    private List<User> users;

}

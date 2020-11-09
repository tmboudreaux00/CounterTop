package com.capstone.countertop.models;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "api_recipe", nullable = false)
    private boolean apiRecipe;

    @Column(name = "recipe_id", nullable = false)
    private long recipeId;

    public Favorite() {}

    public Favorite(long id, boolean apiRecipe, long recipeId) {
        this.id = id;
        this.apiRecipe = apiRecipe;
        this.recipeId = recipeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isApiRecipe() {
        return apiRecipe;
    }

    public void setApiRecipe(boolean apiRecipe) {
        this.apiRecipe = apiRecipe;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
}

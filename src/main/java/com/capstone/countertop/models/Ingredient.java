package com.capstone.countertop.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private long apiId;

    //recipes_ingredients join table
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "recipesIngredients")
    private List<Recipe> recipes;

    public Ingredient (){}

    public Ingredient(long id, String name, long apiId, List<Recipe> recipes) {
        this.id = id;
        this.name = name;
        this.apiId = apiId;
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getApiId() {
        return apiId;
    }

    public void setApiId(long apiId) {
        this.apiId = apiId;
    }
}

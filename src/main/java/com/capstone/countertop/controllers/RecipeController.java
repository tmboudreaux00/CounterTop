package com.capstone.countertop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecipeController {
    @GetMapping("/recipes")
    public String showRecipes() {
        return "recipes/index";
    }

    @GetMapping("/recipes/{id}")
    public String showRecipe(@PathVariable long id) {
        return "recipes/recipe";
    }

    @GetMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable long id) {
        return "recipes/delete";
    }

    @GetMapping("/recipes/create")
    public String createRecipeForm() {
        return "recipes/form";
    }

    @PostMapping("/recipes/create")
    public String createRecipe() {
        return "redirect:/recipes/";
    }

    @GetMapping("/recipes/edit/{id}")
    public String editRecipeForm() {
        return "recipes/form";
    }

    @PostMapping("/recipes/edit")
    public String editRecipe() {
        return "redirect:/recipes/";
    }

}

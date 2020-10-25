package com.capstone.countertop.controllers;

import com.capstone.countertop.models.Recipe;
import com.capstone.countertop.repositories.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RecipeController {
    //{tim}
    private final RecipeRepository recipeRepo;
    private final UserRepository userRepo;

    public RecipeController(RecipeRepository recipeRepo, UserRepository userRepo){
        this.recipeRepo = recipeRepo;
        this.userRepo = userRepo;
    }
    //{/tim}


//    @GetMapping("/recipes")
//    public String showRecipes() {
//        return "recipes/index";
//    }

    //{tim}}
    @GetMapping("/recipes")
    public String showRecipes(Model model) {
        List<Recipe> recipes = recipeRepo.findAll();
        model.addAttribute("recipes", recipes);
        return "recipes/index";
    }
    //{/tim}
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

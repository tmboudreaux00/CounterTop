package com.capstone.countertop.controllers;

import com.capstone.countertop.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
    private final RecipeRepository recipeRepository;

    public SearchController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes/search")
    public String searchReturn(@RequestParam(name="searchTerm") String searchTerm, Model model) {
        model.addAttribute("recipes", recipeRepository.searchRecipesByTerm(searchTerm));
        return "recipes/index";
    }
}

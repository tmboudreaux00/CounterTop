package com.capstone.countertop.controllers;

import com.capstone.countertop.models.ApiRecipe;
import com.capstone.countertop.repositories.RecipeRepository;
import com.capstone.countertop.services.Api;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private final RecipeRepository recipeRepository;

    public SearchController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes/search")
    public String searchReturn(@RequestParam(name="searchTerm") String searchTerm, Model model) {
        try {
            model.addAttribute("response", Api.getRecipes("https://api.spoonacular.com/recipes/complexSearch?query="+ searchTerm));
        } catch(Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("recipes", recipeRepository.searchRecipesByTerm(searchTerm));
        return "recipes/index";
    }
}

package com.capstone.countertop.controllers;

import com.capstone.countertop.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

@Controller
public class SearchController {
    private final RecipeRepository recipeRepository;

    public SearchController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping("/recipes/search")
    public String searchReturn(@RequestParam(name="searchTerm") String searchTerm, Model model) {
        try {
            get("https://api.spoonacular.com/food/products/search?query="+ searchTerm +"&apiKey=5af6024d199b481f99458dc8fc697543");
        } catch(Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("recipes", recipeRepository.searchRecipesByTerm(searchTerm));
        return "recipes/index";
    }

    public void get(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}

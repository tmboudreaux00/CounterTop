package com.capstone.countertop.controllers;

import com.capstone.countertop.repositories.CommentRepository;
import com.capstone.countertop.repositories.IngredientRepository;
import com.capstone.countertop.repositories.RecipeRepository;
import com.capstone.countertop.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroceryListController {
//    private final UserRepository userRepo;
//    private final IngredientRepository ingredientRepo;
//    private final RecipeRepository recipeRepo;

    @GetMapping("/users/grocery-list")
    public String getGroceryList() {
        return "/grocery-list";
    }
}

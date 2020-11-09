package com.capstone.countertop.controllers;

import com.capstone.countertop.models.Comment;
import com.capstone.countertop.models.Ingredient;
import com.capstone.countertop.models.Recipe;
import com.capstone.countertop.models.User;
import com.capstone.countertop.repositories.CommentRepository;
import com.capstone.countertop.repositories.IngredientRepository;
import com.capstone.countertop.repositories.RecipeRepository;
import com.capstone.countertop.repositories.UserRepository;
import com.capstone.countertop.services.Api;
import com.capstone.countertop.services.EmailService;
import com.capstone.countertop.services.Help;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import java.util.List;

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final CommentRepository commentRepository;
    private final IngredientRepository ingredientRepository;

    public RecipeController(RecipeRepository recipeRepository, UserRepository userRepository, EmailService emailService, CommentRepository commentRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.commentRepository = commentRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/recipes")
    public String showRecipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/index";
    }

    @GetMapping("/recipes/{id}")
    public String showRecipe(@PathVariable long id, Model model) {
//        User current = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("recipe", recipeRepository.getOne(id));
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentRepository.findAllByRecipe(recipeRepository.getOne(id)));
//        if(current != null)
//            model.addAttribute("favorited", current.getUsersFavorites().contains(recipeRepository.getOne(id)));
        return "recipes/recipe";
    }



    @GetMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable long id) {
        Recipe recipe = recipeRepository.getOne(id);
        if (recipe != null)
            recipeRepository.delete(recipe);
        return "redirect:/recipes";
    }

    @GetMapping("/recipes/create")
    public String createRecipeForm(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "recipes/form";
    }

    @PostMapping("/recipes/create")
    public String createRecipe(@ModelAttribute Recipe recipe) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recipe.setUser(user);
        LocalDate now = LocalDate.now();
        java.util.Date date = java.sql.Date.valueOf(now);
        recipe.setDatePublished(date);
//        recipe.setUrl("url"); // CHANGE
        recipeRepository.save(recipe);
//        emailService.prepareAndSend(recipe, "New Recipe Submitted!", "Congratulations on submitting your new recipe!");
        return "redirect:/recipes/";
    }

    //CHANGES
    @GetMapping("/recipes/edit/{id}")
    public String editRecipeForm(@PathVariable long id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Recipe recipe = recipeRepository.getOne(id);
        if (recipe == null)
            return "redirect:/recipes/index";
        model.addAttribute("user", userRepository.getOne(user.getId()));
        model.addAttribute("recipe", recipe);
        return "users/newProfile";
    }

    // This needs to be fleshed out more once form is fleshed out. CHANGE
    @PostMapping("/recipes/edit")
    public String editRecipe(
            @RequestParam(name="id") long id,
            @RequestParam(name="title") String name,
            @RequestParam(name="recipe-url") String url,
            @RequestParam(name="description") String description,
            @RequestParam(name="instructions") String instructions,
            @RequestParam(name="skill") String skillLevel,
            @RequestParam(name="ingredients") String ingredients

    ) {
        Recipe recipe = recipeRepository.getOne(id);
        if (recipe == null)
            return "redirect:/user/profile";
        List<Ingredient> list = Help.parseIngredients(ingredients);
        for(Ingredient ingredient : list) {
            ingredientRepository.save(ingredient);
        }
        recipe.setRecipesIngredients(list);
        recipe.setName(name);
        recipe.setUrl(url);
        recipe.setDescription(description);
        recipe.setInstructions(instructions);
        recipe.setSkillLevel(skillLevel);

        recipeRepository.save(recipe);
        return "redirect:/user/profile";
    }
    //CHANGES END


    @PostMapping("/recipes/favorite")
    public String favoriteRecipe(@RequestParam(name="recipeID") long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.getOne(user.getId());
        Recipe recipe = recipeRepository.getOne(id);

        if(user.getUsersFavorites().contains(recipe)) {
            System.out.println("Old Recipe");
            user.getUsersFavorites().remove(recipe);
        }
        else {
            System.out.println("New Recipe");
            user.getUsersFavorites().add(recipe);
            System.out.println(user.getUsersFavorites().toString());
        }
        userRepository.save(user);

        return "redirect:/recipes/" + id;
    }

    @GetMapping("/recipes/api/{id}")
    public String getApiRecipe(@PathVariable long id, Model model) {
        try {
            model.addAttribute("recipe",Api.getRecipe("https://api.spoonacular.com/recipes/"+ id +"/information?includeNutrition=false"));
            model.addAttribute("comment", new Comment());
            model.addAttribute("comments", commentRepository.findAllByRecipe(recipeRepository.getOne(id)));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return "recipes/api";
    }

}

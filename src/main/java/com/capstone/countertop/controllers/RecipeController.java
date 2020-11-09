package com.capstone.countertop.controllers;

import com.capstone.countertop.models.*;
import com.capstone.countertop.repositories.*;
import com.capstone.countertop.services.Api;
import com.capstone.countertop.services.EmailService;
import com.capstone.countertop.services.Help;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final CommentRepository commentRepository;
    private final FavoriteRepository favoriteRepository;

    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, UserRepository userRepository, EmailService emailService, CommentRepository commentRepository, FavoriteRepository favoriteRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.commentRepository = commentRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping("/recipes")
    public String showRecipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/index";
    }

    @GetMapping("/newRecipe")
    public String newRecipe(){
        return "recipes/newRecipe";
    }

    @GetMapping("/recipes/{id}")
    public String showRecipe(@PathVariable long id, Model model, Principal user) {

        Recipe featured = recipeRepository.getOne((long) 4);
        model.addAttribute("featured", featured);

        Recipe featured2 = recipeRepository.getOne((long) 5);
        model.addAttribute("featured2", featured2);

        model.addAttribute("recipe", recipeRepository.getOne(id));
        model.addAttribute("comment", new Comment());
        model.addAttribute("comments", commentRepository.findAllByRecipe(recipeRepository.getOne(id)));
        if(user != null) {
            User current = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            current = userRepository.getOne(current.getId());
            model.addAttribute("favorited", current.getUsersFavorites().contains(favoriteRepository.findFirstByRecipeIdEqualsAndApiRecipeEquals(id, false)));
        }

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
        model.addAttribute("recipe",new Recipe());
        return "recipes/form";
    }

    @PostMapping("/recipes/create")
    public String createRecipe(@ModelAttribute Recipe recipe, @RequestParam(name="trueIngredients") String ingredients, @RequestParam(name="instructions") String instructions) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recipe.setUser(user);

        List<Ingredient> list = Help.parseIngredients(ingredients);
        for(Ingredient ingredient : list) {
            ingredientRepository.save(ingredient);
        }
        recipe.setRecipesIngredients(list);

        LocalDate now = LocalDate.now();
        java.util.Date date = java.sql.Date.valueOf(now);
        recipe.setDatePublished(date);
//        recipe.setUrl("url"); // CHANGE
        recipeRepository.save(recipe);
//        emailService.prepareAndSend(recipe, "New Recipe Submitted!", "Congratulations on submitting your new recipe!");
        return "redirect:/recipes/";
    }

    @GetMapping("/recipes/edit/{id}")
    public String editRecipeForm(@PathVariable long id, Model model) {
        Recipe recipe = recipeRepository.getOne(id);
        if (recipe == null)
            return "redirect:/recipes/index";
        model.addAttribute("recipe",recipe);
        return "recipes/form";
    }

    // This needs to be fleshed out more once form is fleshed out.
    @PostMapping("/recipes/edit")
    public String editRecipe(
            @RequestParam(name="id") long id,
            @RequestParam(name="title") String name,
            @RequestParam(name="description") String description,
            Model model) {
        Recipe recipe = recipeRepository.getOne(id);
        if (recipe == null)
            return "redirect:/recipes/index";
        recipe.setName(name);
        recipe.setDescription(description);
        recipeRepository.save(recipe);
        return "redirect:/recipes/";
    }


    @PostMapping("/recipes/favorite")
    public String favoriteRecipe(@RequestParam(name="recipeID") long id, @RequestParam(name="apiRecipe") boolean apiRecipe, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userRepository.getOne(user.getId());
        Favorite favorite = favoriteRepository.findFirstByRecipeIdEqualsAndApiRecipeEquals(id, apiRecipe);

        if (favorite == null) {
            System.out.println("NO FAVORITE FOUND");
            favorite = new Favorite();
            favorite.setApiRecipe(apiRecipe);
            favorite.setRecipeId(id);
            favoriteRepository.save(favorite);
        }

        if(user.getUsersFavorites().contains(favorite)) {
            user.getUsersFavorites().remove(favorite);
        } else {
            user.getUsersFavorites().add(favorite);
        }
        userRepository.save(user);

        if (apiRecipe) {
            return "redirect:/recipes/api/" + id;
        } else {
            return "redirect:/recipes/" + id;
        }
    }

    @GetMapping("/recipes/api/{id}")
    public String getApiRecipe(@PathVariable long id, Model model, Principal user) {

        Recipe featured2 = recipeRepository.getOne((long) 5);
        Recipe featured = recipeRepository.getOne((long) 7);
        model.addAttribute("featured2", featured2);
        model.addAttribute("featured", featured);

        try {
            model.addAttribute("recipe",Api.getRecipe("https://api.spoonacular.com/recipes/"+ id +"/information?includeNutrition=false"));
            model.addAttribute("comment", new Comment());
            model.addAttribute("comments", commentRepository.findAllByRecipe(recipeRepository.getOne(id)));
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(user != null) {
            User current = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            current = userRepository.getOne(current.getId());
            model.addAttribute("favorited", current.getUsersFavorites().contains(favoriteRepository.findFirstByRecipeIdEqualsAndApiRecipeEquals(id, true)));
        }

        return "recipes/api";
    }

}

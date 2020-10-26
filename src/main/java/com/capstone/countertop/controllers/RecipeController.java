package com.capstone.countertop.controllers;

import com.capstone.countertop.models.Recipe;
import com.capstone.countertop.models.User;
import com.capstone.countertop.repositories.RecipeRepository;
import com.capstone.countertop.repositories.UserRepository;
import com.capstone.countertop.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public RecipeController(RecipeRepository recipeRepository, UserRepository userRepository, EmailService emailService) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @GetMapping("/recipes")
    public String showRecipes(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes/index";
    }

    @GetMapping("/recipes/{id}")
    public String showRecipe(@PathVariable long id, Model model) {
        model.addAttribute("recipe", recipeRepository.getOne(id));
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
    public String createRecipe(@ModelAttribute Recipe recipe) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        recipe.setUser(user);
        recipeRepository.save(recipe);
        emailService.prepareAndSend(recipe, "New Recipe Submitted!", "Congratulations on submitting your new recipe!");
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

}
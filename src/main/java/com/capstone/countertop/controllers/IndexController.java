package com.capstone.countertop.controllers;

import com.capstone.countertop.models.Recipe;
import com.capstone.countertop.repositories.RecipeRepository;
import com.capstone.countertop.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class IndexController {
    private final RecipeRepository recipeRepo;
    private final UserRepository userRepo;

    public IndexController(RecipeRepository recipeRepo, UserRepository userRepo) {
        this.recipeRepo = recipeRepo;
        this.userRepo = userRepo;
    }

    @GetMapping(path="/")
    public String home(Model model) {

        Recipe recipe = recipeRepo.getOne((long) 11);
        model.addAttribute("recipe", recipe);

        Recipe recipe2 = recipeRepo.getOne((long) 12);
        model.addAttribute("recipe2", recipe2);

        Recipe recipe3 = recipeRepo.getOne((long) 14);
        model.addAttribute("recipe3", recipe3);

        return "/index";
    }

//    @GetMapping(path="/")
//    public String home() {
//        return "/index";
//    }

}

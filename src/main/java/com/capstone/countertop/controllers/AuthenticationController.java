package com.capstone.countertop.controllers;

import com.capstone.countertop.models.Comment;
import com.capstone.countertop.models.Recipe;
import com.capstone.countertop.models.User;
import com.capstone.countertop.repositories.CommentRepository;
import com.capstone.countertop.repositories.RecipeRepository;
import com.capstone.countertop.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthenticationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RecipeRepository recipeRepository;
    private final CommentRepository commentRepository;

    public AuthenticationController(UserRepository userRepository, PasswordEncoder passwordEncoder, RecipeRepository recipeRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.recipeRepository = recipeRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, @RequestParam(name="date") String birth) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        user.setUsername(user.getUsername().toLowerCase());
        user.setEmail(user.getEmail().toLowerCase());
        user.setUrl(user.getUrl());
        LocalDate date = LocalDate.now();
        java.util.Date birthdate = java.sql.Date.valueOf(LocalDate.parse(birth));
        java.util.Date date1 = java.sql.Date.valueOf(date);
        user.setDob(birthdate);
        user.setSignupDate(date1);
        userRepository.save(user);
        return "redirect:/login";
    }

    @PostMapping("/comment")
    public String comment(@ModelAttribute Comment comment, @RequestParam(name="recipeID") long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        java.util.Date date = new java.util.Date();
        comment.setUser(userRepository.getOne(user.getId()));
        comment.setRecipe(recipeRepository.getOne(id));
        comment.setDate(date);

        commentRepository.save(comment);
        return "redirect:/recipes/" + id;
    }
}

package com.capstone.countertop.controllers;

import com.capstone.countertop.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.capstone.countertop.models.User;
import com.capstone.countertop.repositories.UserRepository;


@Controller
public class UserController {
//    private final UserRepository userDao;
//    private final PasswordEncoder passwordEncoder;

    @GetMapping("users/register")
    public String registerForm() {
        return "users/register";
    }

    @PostMapping("users/register")
    public String registerUser() {
        return "redirect:/login";
    }
}

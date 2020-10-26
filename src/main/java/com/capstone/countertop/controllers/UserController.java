package com.capstone.countertop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.capstone.countertop.models.User;


@Controller
public class UserController {

    @GetMapping("users/register")
    public String registerForm() {
        return "users/register";
    }

    @PostMapping("users/register")
    public String registerUser() {
        return "redirect:/login";
    }
}

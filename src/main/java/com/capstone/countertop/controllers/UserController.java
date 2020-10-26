package com.capstone.countertop.controllers;

import com.capstone.countertop.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.capstone.countertop.models.User;
import com.capstone.countertop.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;



@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("user/{id}")
    public String profilePage(@PathVariable long id, Model model){
        model.addAttribute("user", userDao.getOne(id));
        return "/profile";
    }

    @GetMapping("users/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("users/register")
    public String registerUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }
}

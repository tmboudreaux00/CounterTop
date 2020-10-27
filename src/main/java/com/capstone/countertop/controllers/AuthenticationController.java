package com.capstone.countertop.controllers;

import com.capstone.countertop.models.User;
import com.capstone.countertop.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class AuthenticationController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        LocalDate date = LocalDate.now();
        java.util.Date birthdate = java.sql.Date.valueOf(LocalDate.parse(birth));
        java.util.Date date1 = java.sql.Date.valueOf(date);
        user.setDob(birthdate);
        user.setSignupDate(date1);
        userRepository.save(user);
        return "redirect:/login";
    }
}

package com.capstone.countertop.controllers;

import com.capstone.countertop.repositories.RecipeRepository;
import com.capstone.countertop.repositories.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.capstone.countertop.models.User;
import com.capstone.countertop.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class UserController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;
    private final RecipeRepository recipeDao;


    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, RecipeRepository recipeDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.recipeDao = recipeDao;

    }


    //CHANGES

    @GetMapping("user/profile")
    public String showProfile(Model model, @PageableDefault(value=9) Pageable pageable) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDao.getOne(user.getId()));
        model.addAttribute("page", recipeDao.findAllByUser(user, pageable));
        return "users/newProfile";
    }




    @PostMapping("/user/update")
    public String updateUser(
            @RequestParam(name="id") long id,
            @RequestParam(name="email") String email,
            @RequestParam(name="username") String username,
            @RequestParam(name="password_old") String password_old,
            @RequestParam(name="password_new") String password_new,
            @RequestParam(name="date") String birth,
            @RequestParam(name="url") String url
    ) {
        User user = userDao.getOne(id);
        user.setUsername(username.toLowerCase());
        if (!password_old.isEmpty() && !password_new.isEmpty()) {
            if (passwordEncoder.encode(password_old).equals(user.getPassword()))
                user.setPassword(passwordEncoder.encode(password_new));
        }
        user.setEmail(email);
        user.setUrl(url);
        java.util.Date dob = java.sql.Date.valueOf(LocalDate.parse(birth));
        user.setDob(dob);

        userDao.save(user);

        return "redirect:/user/profile";
    }

    //CHANGES END

    @GetMapping("/users/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "/register";
    }

    @PostMapping("/users/register")
    public String registerUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/users/favorites")
    public String showFavorites(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.getOne(user.getId());
        System.out.println(user.getUsersFavorites());
        model.addAttribute("favorites", user.getUsersFavorites());

        return "users/favorites";
    }

    @GetMapping("/about")
    public String aboutUs(){
        return "aboutUs";
    }

}

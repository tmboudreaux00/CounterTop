package com.capstone.countertop.controllers;

import com.capstone.countertop.repositories.UserRepository;
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

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    //CHANGES
//    @GetMapping("users/{id}")
//    public String userProfilePage(@PathVariable long id, Model model){
//        model.addAttribute("user", userDao.getOne(id));
//        return "/users/profiles";
//    }
//
//    @GetMapping("profile")
//    public String profilePages(Model model){
//        String username = "username";
//        model.addAttribute("username", username);
//        return "/users/myprofile";
//    }

    @GetMapping("user/profile")
    public String showProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDao.getOne(user.getId()));

        return "users/newProfile";
    }

//<<<<<<< HEAD
//    @GetMapping("profile")
//    public String profilePages(Model model){
//        String username = "username";
//        model.addAttribute("username", username);
//        return "users/myprofile";
//=======
//    @GetMapping("user/{id}")
//    public String showUser(@PathVariable long id, Model model) {
//        model.addAttribute("user", userDao.getOne(id));
//
//        return "/users/newProfile";
//    }

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
            if(passwordEncoder.encode(password_old).equals(user.getPassword()))
                user.setPassword(passwordEncoder.encode(password_new));
        }
        user.setEmail(email);
        user.setUrl(url);
        java.util.Date dob = java.sql.Date.valueOf(LocalDate.parse(birth));
        user.setDob(dob);

        userDao.save(user);

        return "redirect:/user/profile";
//>>>>>>> newProfile
    }

    @Deprecated
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        userDao.save(user);
        model.addAttribute("user", userDao.findAll());
        return "redirect:/profile";
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
    public String userFavorites(){

        return "users/favorites";
    }
}

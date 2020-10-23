package com.capstone.countertop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GroceryListController {
    @GetMapping("/users/grocery-list")
    public String getGroceryList() {
        return "users/grocery-list";
    }
}

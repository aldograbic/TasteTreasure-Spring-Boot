package com.project.TasteTreasure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipesPageController {
    
    @GetMapping("/recipes")
    public String getRecipesPage() {
        return "recipes";
    }
}

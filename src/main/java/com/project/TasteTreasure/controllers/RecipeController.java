package com.project.TasteTreasure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecipeController {

    @GetMapping("/recipe")
    public String getRecipePage() {
        return "recipe";
    }
    
}

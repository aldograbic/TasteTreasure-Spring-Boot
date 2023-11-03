package com.project.TasteTreasure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.project.TasteTreasure.classes.Recipe;
import com.project.TasteTreasure.repositories.RecipeService;

@RestController
public class RecipeSearchController {
    
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/search")
    public RedirectView searchRecipes(@RequestParam String query, Model model) {
        List<Recipe> recipes = recipeService.getRecipes(query);
        model.addAttribute("recipes", recipes);
        return new RedirectView("/recipes?query=" + query);
    }
}

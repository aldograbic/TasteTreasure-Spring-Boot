package com.project.TasteTreasure.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.TasteTreasure.classes.Recipe;
import com.project.TasteTreasure.repositories.RecipeService;

@Controller
public class RecipesPageController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public String getRecipesPage(@RequestParam(value = "query", required = false) String query, Model model) {
        if (query != null) {
            List<Recipe> recipes = recipeService.getRecipes(query);
            model.addAttribute("recipes", recipes);
        }
        return "recipes";
    }
}

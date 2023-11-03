package com.project.TasteTreasure.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TasteTreasure.classes.Recipe;

@Service
public class RecipeService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${edamam.api.endpoint}")
    private String edamamApiEndpoint;
    @Value("${edamam.api.app_id}")
    private String edamamAppId;
    @Value("${edamam.api.app_key}")
    private String edamamAppKey;

    public List<Recipe> getRecipes(String query) {
        String apiUrl = edamamApiEndpoint + "?q=" + query + "&app_id=" + edamamAppId + "&app_key=" + edamamAppKey;
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        return parseRecipeData(response.getBody());
    }

    private List<Recipe> parseRecipeData(String responseBody) {
    List<Recipe> recipes = new ArrayList<>();

    try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseBody);

        // Assuming the response structure has a "hits" array
        JsonNode hits = root.path("hits");

        for (JsonNode hit : hits) {
            JsonNode recipeData = hit.path("recipe");

            Recipe recipe = new Recipe();
            recipe.setLabel(recipeData.path("label").asText());
            // recipe.setSource(recipeData.path("source").path("label").asText());
            recipe.setUri(recipeData.path("uri").asText());
            recipe.setImage(recipeData.path("image").asText());

            recipes.add(recipe);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return recipes;
}

}


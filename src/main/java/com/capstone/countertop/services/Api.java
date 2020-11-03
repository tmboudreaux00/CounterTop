package com.capstone.countertop.services;

import com.capstone.countertop.models.ApiRecipe;
import com.capstone.countertop.models.Ingredient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Api {
    private static final String key = "&apiKey=5af6024d199b481f99458dc8fc697543";

    public static JSONObject getResponse(String uri) throws IOException, InterruptedException, ParseException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + key))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        JSONParser parser = new JSONParser();

        return (JSONObject) parser.parse(response.body());
    }

    public static List<ApiRecipe> getRecipes(String uri) throws InterruptedException, ParseException, IOException {
        JSONObject object = getResponse(uri);
        JSONArray array = (JSONArray) object.get("results");

        List<ApiRecipe> list = new ArrayList<>();
        for (Object o : array) {
            JSONObject miniObject = (JSONObject) o;
            ApiRecipe recipe = new ApiRecipe();
            recipe.setId((long) miniObject.get("id"));
            recipe.setTitle((String) miniObject.get("title"));
            recipe.setImage((String) miniObject.get("image"));
            list.add(recipe);
        }

        return list;
    }

    public static ApiRecipe getRecipe(String uri) throws InterruptedException, ParseException, IOException {
        JSONObject object = getResponse(uri);
        System.out.println(object.get("status"));

        if(object.get("status") == null) {
            System.out.println("SUCCESS");
            ApiRecipe recipe = new ApiRecipe();
            recipe.setId((long) object.get("id"));
            recipe.setImage((String) object.get("image"));
            recipe.setTitle((String) object.get("title"));
            recipe.setDescription((String) object.get("summary"));
            recipe.setInstructions((String) object.get("instructions"));
            recipe.setReadyTime((long) object.get("readyInMinutes"));
            recipe.setServings((long) object.get("servings"));
            recipe.setSourceUrl((String) object.get("sourceUrl"));
            recipe.setIngredientList(getIngredients((JSONArray) object.get("extendedIngredients")));

            return recipe;
        } else {
            System.out.println("FAILURE");
            return null;
        }
    }

    public static List<Ingredient> getIngredients(JSONArray ingredients) {
        List<Ingredient> list = new ArrayList<>();
        for (Object o : ingredients) {
            JSONObject miniObject = (JSONObject) o;
            Ingredient ingredient = new Ingredient();
            ingredient.setId((long) miniObject.get("id"));
            ingredient.setName((String) miniObject.get("name"));
            list.add(ingredient);
        }

        return list;
    }
}

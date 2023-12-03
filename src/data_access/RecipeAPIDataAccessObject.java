package data_access;

import entity.Recipe;
import use_case.APIpull.RecipeAPIDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.util.UUID;


public class RecipeAPIDataAccessObject implements RecipeAPIDataAccessInterface {

    private final Map<UUID, Recipe> recipeMap = new HashMap<UUID, Recipe>();


    @Override
    public boolean existsByID(UUID identifier) {
        return recipeMap.containsKey(identifier);
    }

    @Override
    public void save(Recipe recipe) {

    }

    public void apiCall(String search){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.api-ninjas.com/v1/recipe?query=" + search))
                .header("X-Api-Key", "zgSAUkuV/RadzPi5Zmg/YQ==3CRbkHrEG4ZKiyhQ")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        assert response != null;
        System.out.println(response.body());
    }
}
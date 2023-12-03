package data_access;

import entity.Recipe;
import entity.RecipeFactory;
import use_case.APIpull.RecipeDataAccessInterface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryRecipeAPIDataAccessObject implements RecipeDataAccessInterface {

    private final Map<UUID, Recipe> recipeMap = new HashMap<UUID, Recipe>();
    private RecipeFactory recipeFactory;

    public InMemoryRecipeAPIDataAccessObject() throws IOException{
        this.recipeFactory = new RecipeFactory();
    }

    @Override
    public boolean existsByID(UUID identifier) {
        return recipeMap.containsKey(identifier);
    }


    @Override
    public void save(Recipe recipe) {

    }

    public String apiCall(String search){
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
        return response.body();
    }


}

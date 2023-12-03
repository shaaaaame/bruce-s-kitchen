package data_access;

import entity.Recipe;
import use_case.APIpull.RecipeAPIDataAccessInterface;

import java.util.HashMap;
import java.util.Map;


public class RecipeAPIDataAccessObject implements RecipeAPIDataAccessInterface {

    private final Map<String, Recipe> recipeMap = new HashMap<>();



    @Override
    public boolean existsByName(String identifier) {
        return false;
    }

    @Override
    public void save(Recipe recipe) {

    }
}

package use_case.recipe_search;

import entity.Recipe;

import java.util.UUID;

public interface RecipeSearchDataAccessInterface {

    public boolean existsByID(UUID identifier);

    void save(Recipe recipe);


}
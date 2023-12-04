package use_case.recipe_browse;

import entity.Recipe;

import java.util.Map;
import java.util.UUID;

public interface RecipeBrowseDataAccessInterface {
    void save(Map<UUID, Recipe> recipeMap);
}

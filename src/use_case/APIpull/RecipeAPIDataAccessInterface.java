package use_case.APIpull;

import entity.Recipe;
import entity.User;

public interface RecipeAPIDataAccessInterface {
    boolean existsByName(String identifier);
    void save(Recipe recipe);
}

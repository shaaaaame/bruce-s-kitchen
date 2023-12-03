package use_case.APIpull;

import entity.Recipe;

import java.util.UUID;

public interface RecipeDataAccessInterface {
    boolean existsByID(UUID identifier);
    void save(Recipe recipe);
}

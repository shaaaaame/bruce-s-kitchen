package use_case.APIpull;

import entity.Recipe;
import entity.User;

import java.util.UUID;

public interface RecipeAPIDataAccessInterface {
    boolean existsByID(UUID identifier);
    void save(Recipe recipe);
}

package use_case.recipe_create;

import entity.Recipe;
import java.util.UUID;

public interface CreateRecipeDataAccessInterface {

    boolean exitById(UUID id);
    void save(Recipe recipe);
}

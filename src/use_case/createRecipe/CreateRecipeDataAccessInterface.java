package use_case.createRecipe;

import entity.Recipe;
import java.util.UUID;

public interface CreateRecipeDataAccessInterface {

    boolean existsbyID(UUID id);
    void save(Recipe recipe);
}

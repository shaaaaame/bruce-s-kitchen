package interface_adapter.recipe_create;

import use_case.recipe_create.CreateRecipeInputBoundary;
import java.util.UUID;
import entity.Tag;
import use_case.recipe_create.RecipeInputData;

import java.util.List;

public class RecipeCreatorController {

    final CreateRecipeInputBoundary createRecipeInteractor;

    public RecipeCreatorController(CreateRecipeInputBoundary createRecipeInputBoundary){
        this.createRecipeInteractor = createRecipeInputBoundary;
    }

    public void execute(UUID user_id, String name, String servings, List<String> ingredients, Tag[] tags, String instructions){
        RecipeInputData recipeInputData = new RecipeInputData(user_id, name, servings, ingredients, tags, instructions);

        createRecipeInteractor.execute(recipeInputData);
    }
}

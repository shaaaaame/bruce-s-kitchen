package interface_adapter.recipeCreator;

import entity.Recipe;
import entity.RecipeFactory;
import use_case.createRecipe.CreateRecipeInputBoundary;
import java.util.UUID;
import entity.Tag;
import use_case.createRecipe.RecipeInputData;

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

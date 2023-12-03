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

    public RecipeCreatorController(CreateRecipeInputBoundary createRecipeInteractor){
        this.createRecipeInteractor = createRecipeInteractor;
    }

    public void execute(String name, List<String> ingredients, String instructions, String servings, UUID user_id, Tag[] tags){
        RecipeInputData recipeInputData = new RecipeInputData(name, servings, ingredients, instructions, user_id, tags);

        createRecipeInteractor.execute(recipeInputData);
    }
}

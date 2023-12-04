package use_case.recipe_create;

import entity.Recipe;
import entity.RecipeFactory;
import java.time.LocalDateTime;

public class CreateRecipeInteractor implements CreateRecipeInputBoundary{

    final CreateRecipeDataAccessInterface createRecipeDataAccessObject;
    final RecipeFactory recipeFactory;
    final CreateRecipeOutputBoundary createRecipePresenter;

    public CreateRecipeInteractor(CreateRecipeDataAccessInterface createRecipeDataAccessObject,
                                  RecipeFactory recipeFactory,
                                  CreateRecipeOutputBoundary createRecipeOutputBoundary){
        this.createRecipeDataAccessObject = createRecipeDataAccessObject;
        this.recipeFactory = recipeFactory;
        this.createRecipePresenter = createRecipeOutputBoundary;
    }
    @Override
    public void execute(RecipeInputData recipeInputData) {
        Recipe recipe = recipeFactory.create(
                recipeInputData.getUser_id(),
                recipeInputData.getName(),
                recipeInputData.getServings(),
                recipeInputData.getIngredients(),
                recipeInputData.getTags(),
                recipeInputData.getInstructions(),
                LocalDateTime.now()
        );
        createRecipeDataAccessObject.save(recipe);

        CreateRecipeOutputDta createRecipeOutputDta = new CreateRecipeOutputDta(
                recipe.getName(),
                recipe.getDate()
        );
        createRecipePresenter.prepareSuccessView(createRecipeOutputDta);
    }

}

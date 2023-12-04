package interface_adapter.recipe_create;
import interface_adapter.ViewManagerModel;
import use_case.recipe_create.CreateRecipeOutputBoundary;
import use_case.recipe_create.CreateRecipeOutputDta;

public class RecipeCreatePresenter implements CreateRecipeOutputBoundary{

    private final RecipeCreatorViewModel recipeCreatorViewModel;
    private ViewManagerModel viewManagerModel;

    public RecipeCreatePresenter(ViewManagerModel viewManagerModel, RecipeCreatorViewModel recipeCreatorViewModel){
        this.recipeCreatorViewModel = recipeCreatorViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(CreateRecipeOutputDta createRecipeOutputDta){

    }
    @Override
    public void prepareFailView(String error){

    }
}

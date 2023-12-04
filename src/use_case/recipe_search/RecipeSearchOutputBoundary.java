package use_case.recipe_search;

public interface RecipeSearchOutputBoundary {
    public void prepareSuccessView(RecipeSearchOutputData recipeOutputData);
    public void prepareFailView(String error);
}
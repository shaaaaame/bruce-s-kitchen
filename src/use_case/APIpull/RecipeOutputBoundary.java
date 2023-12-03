package use_case.APIpull;

public interface RecipeOutputBoundary {
    public void prepareSuccessView(RecipeOutputData recipeOutputData);
    public void prepareFailView(String error);
}

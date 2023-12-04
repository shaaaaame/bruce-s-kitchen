package use_case.recipe_create;

public interface CreateRecipeOutputBoundary {
    public void prepareSuccessView(CreateRecipeOutputDta createRecipeOutputDta);
    public void prepareFailView(String error);
}

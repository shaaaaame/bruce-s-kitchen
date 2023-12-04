package use_case.createRecipe;

public interface CreateRecipeOutputBoundary {
    public void prepareSuccessView(CreateRecipeOutputDta createRecipeOutputDta);
    public void prepareFailView(String error);
}

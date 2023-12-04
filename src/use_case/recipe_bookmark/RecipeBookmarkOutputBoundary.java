package use_case.recipe_bookmark;


public interface RecipeBookmarkOutputBoundary {
    public void prepareSuccessView();
    public void prepareFailView(String error);
}

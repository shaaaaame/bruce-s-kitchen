package use_case.recipe_browse;

public interface RecipeBrowseOutputBoundary {
    public void prepareSuccessView(RecipeBrowseOutputData recipeBrowseOutputData);
    public void prepareFailView(String error);
}

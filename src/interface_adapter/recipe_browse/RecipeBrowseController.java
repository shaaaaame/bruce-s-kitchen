package interface_adapter.recipe_browse;

import use_case.recipe_browse.RecipeBrowseInputBoundary;

public class RecipeBrowseController {
    final RecipeBrowseInputBoundary recipeBrowseInteractor;

    public RecipeBrowseController(RecipeBrowseInputBoundary recipeBrowseInputBoundary) {
        this.recipeBrowseInteractor = recipeBrowseInputBoundary;
    }

    public void execute(){
        recipeBrowseInteractor.execute();
    }
}

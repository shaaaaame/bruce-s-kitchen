package use_case.get_bookmarked;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryRecipeAPIDataAccessObject;
import entity.Recipe;
import entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetBookmarkedInteractor implements GetBookmarkedInputBoundary {
    final FileUserDataAccessObject userDataAccessObject;
    final InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject;
    final GetBookmarkOutputBoundary getBookmarkPresenter;
    public GetBookmarkedInteractor(FileUserDataAccessObject userDataAccessObject,
                                  InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject,
                                   GetBookmarkOutputBoundary getBookmarkPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.recipeAPIDataAccessObject = recipeAPIDataAccessObject;
        this.getBookmarkPresenter = getBookmarkPresenter;
    }

    @Override
    public void execute(GetBookmarkedInputData getBookmarkedInputData) {
            User user = userDataAccessObject.get(getBookmarkedInputData.getUsername());

            if (user == null){
                getBookmarkPresenter.prepareFailView("Not logged in!");
            } else {
                List<UUID> recipeIds = user.getBookmarkedRecipes();
                List<Recipe> recipes = new ArrayList<>();

                GetBookmarkedOutputData getBookmarkedOutputData;

                for(UUID id : recipeIds){
                    if (recipeAPIDataAccessObject.existsByID(id)){
                        Recipe r = recipeAPIDataAccessObject.getById(id);
                        recipes.add(r);
                    }
                }

                getBookmarkedOutputData = new GetBookmarkedOutputData(recipes);
                getBookmarkPresenter.prepareSuccessView(getBookmarkedOutputData);

            }

    }
}

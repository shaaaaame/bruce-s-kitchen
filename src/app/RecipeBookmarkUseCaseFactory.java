package app;

import data_access.FileUserDataAccessObject;
import data_access.InMemoryRecipeAPIDataAccessObject;
import data_access.RecipeFileDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_bookmark.GetBookmarkedController;
import interface_adapter.recipe_bookmark.GetBookmarkedPresenter;
import interface_adapter.recipe_bookmark.GetBookmarkedViewModel;
import interface_adapter.recipe_bookmark.RecipeBookmarkController;
import interface_adapter.recipe_browse.RecipeBrowseController;
import interface_adapter.recipe_browse.RecipeBrowsePresenter;
import interface_adapter.recipe_browse.RecipeBrowseViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.get_bookmarked.GetBookmarkOutputBoundary;
import use_case.get_bookmarked.GetBookmarkedInputBoundary;
import use_case.get_bookmarked.GetBookmarkedInteractor;
import use_case.recipe_bookmark.RecipeBookmarkInputBoundary;
import use_case.recipe_bookmark.RecipeBookmarkInteractor;
import use_case.recipe_browse.RecipeBrowseInputBoundary;
import use_case.recipe_browse.RecipeBrowseInteractor;
import use_case.recipe_browse.RecipeBrowseOutputBoundary;
import view.RecipeBookmarkView;
import view.RecipeBrowseView;

import javax.swing.*;
import java.io.IOException;

public class RecipeBookmarkUseCaseFactory {
    public static RecipeBookmarkView create(ViewManagerModel viewManagerModel,
                                            GetBookmarkedViewModel getBookmarkedViewModel,
                                            InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject, FileUserDataAccessObject userDataAccessObject){
        try{
            GetBookmarkedController getBookmarkedController = recipeBookmarkUseCaseFactory(viewManagerModel, getBookmarkedViewModel, recipeAPIDataAccessObject, userDataAccessObject);
            return new RecipeBookmarkView(viewManagerModel, getBookmarkedViewModel, getBookmarkedController);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Cannot open recipes");
        }
        return null;
    }

    private static GetBookmarkedController recipeBookmarkUseCaseFactory(ViewManagerModel viewManagerModel,
                                                                     GetBookmarkedViewModel getBookmarkedViewModel,
                                                                     InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject,
                                                                         FileUserDataAccessObject userDataAccessObject) throws IOException {
        GetBookmarkOutputBoundary getBookmarkPresenter = new GetBookmarkedPresenter(getBookmarkedViewModel, viewManagerModel);
        GetBookmarkedInputBoundary getBookmarkedInputBoundary = new GetBookmarkedInteractor(userDataAccessObject, recipeAPIDataAccessObject, getBookmarkPresenter);

        return new GetBookmarkedController(getBookmarkedInputBoundary);
    }
}

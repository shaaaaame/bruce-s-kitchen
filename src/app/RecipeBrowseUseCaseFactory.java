package app;

import data_access.InMemoryRecipeAPIDataAccessObject;
import data_access.RecipeFileDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_browse.RecipeBrowseController;
import interface_adapter.recipe_browse.RecipeBrowsePresenter;
import interface_adapter.recipe_browse.RecipeBrowseViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.recipe_browse.RecipeBrowseInputBoundary;
import use_case.recipe_browse.RecipeBrowseInteractor;
import use_case.recipe_browse.RecipeBrowseOutputBoundary;
import view.RecipeBrowseView;

import javax.swing.*;
import java.io.IOException;

public class RecipeBrowseUseCaseFactory {
    public static RecipeBrowseView create(ViewManagerModel viewManagerModel,
                                          RecipeBrowseViewModel recipeBrowseViewModel, RecipeSearchViewModel recipeSearchViewModel, RecipeFileDataAccessObject recipeFileDataAccessObject, InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject){
        try{
            RecipeBrowseController recipeBrowseController = recipeBrowseUseCaseFactory(viewManagerModel, recipeBrowseViewModel, recipeFileDataAccessObject, recipeAPIDataAccessObject);
            return new RecipeBrowseView(recipeBrowseViewModel, recipeSearchViewModel, recipeBrowseController);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Cannot open recipes");
        }
        return null;
    }

    private static RecipeBrowseController recipeBrowseUseCaseFactory(ViewManagerModel viewManagerModel,
                                                                     RecipeBrowseViewModel recipeBrowseViewModel,
                                                                     RecipeFileDataAccessObject recipeFileDataAccessObject,
                                                                     InMemoryRecipeAPIDataAccessObject recipeAPIDataAccessObject) throws IOException {
        RecipeBrowseOutputBoundary recipeBrowseOutputBoundary = new RecipeBrowsePresenter(recipeBrowseViewModel, viewManagerModel);
        RecipeBrowseInputBoundary recipeBrowseInteractor = new RecipeBrowseInteractor(recipeFileDataAccessObject, recipeAPIDataAccessObject, recipeBrowseOutputBoundary);

        return new RecipeBrowseController(recipeBrowseInteractor);
    }
}

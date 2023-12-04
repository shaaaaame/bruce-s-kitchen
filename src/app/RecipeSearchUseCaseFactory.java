package app;

import data_access.InMemoryRecipeAPIDataAccessObject;
import entity.RecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_search.RecipeSearchController;
import interface_adapter.recipe_search.RecipeSearchPresenter;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInteractor;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import view.GroceryListView;
import view.RecipeSearchView;

import javax.swing.*;
import java.io.IOException;

public class RecipeSearchUseCaseFactory {

    public static RecipeSearchView create(ViewManagerModel viewManagerModel,
                                         RecipeSearchViewModel recipeSearchViewModel,
                                         InMemoryRecipeAPIDataAccessObject recipeSearchDataAccessObject){
        RecipeSearchController recipeSearchController = createRecipeSearchUseCase(viewManagerModel, recipeSearchViewModel, recipeSearchDataAccessObject);
        return new RecipeSearchView(recipeSearchViewModel, recipeSearchController);
    }

    private static RecipeSearchController createRecipeSearchUseCase(ViewManagerModel viewManagerModel,
                                                                    RecipeSearchViewModel recipeSearchViewModel,
                                                                    InMemoryRecipeAPIDataAccessObject recipeSearchDataAccessObject){
        RecipeSearchOutputBoundary recipeSearchOutputBoundary = new RecipeSearchPresenter(recipeSearchViewModel, viewManagerModel);
        RecipeFactory recipeFactory = new RecipeFactory();
        RecipeSearchInputBoundary recipeSearchInteractor = new RecipeSearchInteractor(recipeSearchDataAccessObject, recipeFactory, recipeSearchOutputBoundary);

        return new RecipeSearchController(recipeSearchInteractor);
    }
}

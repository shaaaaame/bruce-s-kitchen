package app;

import data_access.RecileFileDataAccessObject;
import data_access.RecipeFileDataAccessObject;
import entity.RecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.recipe_create.RecipeCreatePresenter;
import interface_adapter.recipe_create.RecipeCreatorController;
import interface_adapter.recipe_create.RecipeCreatorViewModel;
import use_case.recipe_create.CreateRecipeDataAccessInterface;
import use_case.recipe_create.CreateRecipeInputBoundary;
import use_case.recipe_create.CreateRecipeInteractor;
import use_case.recipe_create.CreateRecipeOutputBoundary;
import view.RecipeCreatorView;

import javax.swing.*;
import java.io.IOException;

public class CreateRecipeUseCaseFactory {

    public static RecipeCreatorView create(
            ViewManagerModel viewManagerModel,
            RecipeCreatorViewModel recipeCreatorViewModel,
            RecileFileDataAccessObject recipeFileDataAccessObject) {
        try {
            RecipeCreatorController recipeCreatorController = createRecipeCreateUseCase(viewManagerModel, recipeCreatorViewModel, recipeFileDataAccessObject);
            return new RecipeCreatorView(recipeCreatorController, recipeCreatorViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "no open");
        }
        return null;

    }
    private static RecipeCreatorController createRecipeCreateUseCase(ViewManagerModel viewManagerModel, RecipeCreatorViewModel recipeCreatorViewModel, RecileFileDataAccessObject recipeFileDataAccessObject) throws IOException {

        CreateRecipeOutputBoundary createRecipeOutputBoundary = new RecipeCreatePresenter(viewManagerModel, recipeCreatorViewModel);
        RecipeFactory recipeFactory = new RecipeFactory();
        CreateRecipeInputBoundary recipeInputBoundary = new CreateRecipeInteractor(recipeFileDataAccessObject, recipeFactory, createRecipeOutputBoundary);

        return new RecipeCreatorController(recipeInputBoundary);

    }


}

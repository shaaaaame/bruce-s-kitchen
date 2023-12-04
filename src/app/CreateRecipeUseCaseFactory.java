package app;

import entity.RecipeFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.recipeCreator.RecipeCreatePresenter;
import interface_adapter.recipeCreator.RecipeCreatorController;
import interface_adapter.recipeCreator.RecipeCreatorViewModel;
import use_case.createRecipe.CreateRecipeDataAccessInterface;
import use_case.createRecipe.CreateRecipeInputBoundary;
import use_case.createRecipe.CreateRecipeInteractor;
import use_case.createRecipe.CreateRecipeOutputBoundary;
import view.HomePageView;
import view.RecipeCreatorView;

import javax.swing.*;
import java.io.IOException;

public class CreateRecipeUseCaseFactory {
    private CreateRecipeUseCaseFactory() {}

    public static RecipeCreatorView create(
            ViewManagerModel viewManagerModel,
            RecipeCreatorViewModel recipeCreatorViewModel,
            HomePageViewModel homePageViewModel,
            CreateRecipeDataAccessInterface recipeDataAccessInterface) {
        try {
            RecipeCreatorController recipeCreatorController = createRecipeCreateUseCase(viewManagerModel, recipeCreatorViewModel, homePageViewModel, recipeDataAccessInterface);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "no open");
        }
        return null;

    }
    private static RecipeCreatorController createRecipeCreateUseCase(
            ViewManagerModel viewManagerModel,
            RecipeCreatorViewModel recipeCreatorViewModel,
            HomePageViewModel homePageViewModel,
            CreateRecipeDataAccessInterface createRecipeDataAccessInterface) throws IOException {

        CreateRecipeOutputBoundary createRecipeOutputBoundary = new RecipeCreatePresenter(viewManagerModel, recipeCreatorViewModel);
        RecipeFactory recipeFactory = new RecipeFactory();
        CreateRecipeInputBoundary recipeInputBoundary = new CreateRecipeInteractor(createRecipeDataAccessInterface, recipeFactory, createRecipeOutputBoundary);

        return new RecipeCreatorController(recipeInputBoundary);

    }


}

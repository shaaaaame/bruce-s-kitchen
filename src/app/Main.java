package app;

import data_access.FileGroceryListDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.InMemoryRecipeAPIDataAccessObject;
import data_access.RecipeFileDataAccessObject;
import entity.UserFactory;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recipe_bookmark.GetBookmarkedController;
import interface_adapter.recipe_bookmark.GetBookmarkedViewModel;
import interface_adapter.recipe_browse.RecipeBrowseViewModel;
import interface_adapter.show_grocery_list.ShowGroceryListViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import view.*;
import interface_adapter.homepage.HomePageViewModel;
import view.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame app = new JFrame("Bruce's Kitchen");
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        MenuBar menuBar = new MenuBar(viewManagerModel);

        JPanel views = new JPanel(cardLayout);
        app.setLayout(new BorderLayout());
        app.add(menuBar, BorderLayout.NORTH);
        app.add(views, BorderLayout.CENTER);

        new ViewManager(views, cardLayout, viewManagerModel);

        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        GroceryListViewModel groceryListViewModel = new GroceryListViewModel();
        HomePageViewModel homePageViewModel = new HomePageViewModel();
        ShowGroceryListViewModel showGroceryListViewModel = new ShowGroceryListViewModel();
        RecipeSearchViewModel recipeSearchViewModel = new RecipeSearchViewModel();
        RecipeBrowseViewModel recipeBrowseViewModel = new RecipeBrowseViewModel();
        GetBookmarkedViewModel getBookmarkedViewModel = new GetBookmarkedViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.json", new UserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileGroceryListDataAccessObject groceryListDataAccessObject;
        try {
            groceryListDataAccessObject = new FileGroceryListDataAccessObject();
        } catch (IOException e){
            throw new RuntimeException("Unable to read groceryList.json");
        }

        InMemoryRecipeAPIDataAccessObject recipeSearchDataAccessObject;
        try{
            recipeSearchDataAccessObject = new InMemoryRecipeAPIDataAccessObject();
        } catch (IOException e){
            throw new RuntimeException("Unable to pull from API");
        }

        RecipeFileDataAccessObject recipeFileDataAccessObject;
        try{
            recipeFileDataAccessObject = new RecipeFileDataAccessObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, homePageViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        HomePageView homePageView = new HomePageView(viewManagerModel, homePageViewModel);
        views.add(homePageView, homePageView.viewName);

        GroceryListView groceryListView = GroceryListUseCaseFactory.create(viewManagerModel, groceryListViewModel, groceryListDataAccessObject);
        views.add(groceryListView, groceryListView.viewName);
      
        RecipeSearchView recipeSearchView = RecipeSearchUseCaseFactory.create(viewManagerModel, recipeSearchViewModel, recipeSearchDataAccessObject, userDataAccessObject);
        views.add(recipeSearchView, recipeSearchView.viewName);

        ShowGroceryListView showGroceryListView = ShowGroceryListUseCaseFactory.create(viewManagerModel, showGroceryListViewModel, groceryListDataAccessObject);
        views.add(showGroceryListView, showGroceryListView.viewName);

        RecipeBrowseView recipeBrowseView = RecipeBrowseUseCaseFactory.create(viewManagerModel, recipeBrowseViewModel, recipeSearchViewModel, recipeFileDataAccessObject, recipeSearchDataAccessObject);
        views.add(recipeBrowseView, showGroceryListView.viewName);

        RecipeBookmarkView recipeBookmarkView = RecipeBookmarkUseCaseFactory.create(viewManagerModel, getBookmarkedViewModel, recipeSearchDataAccessObject, userDataAccessObject);
        views.add(recipeBookmarkView, recipeBookmarkView.viewName);

        viewManagerModel.setActiveView(loginView.viewName);
        viewManagerModel.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getSource().equals(viewManagerModel)){
                    if (viewManagerModel.isLoggedIn()){
                        menuBar.show();
                    }else{
                        menuBar.hide();
                    }
                }
                if(evt.getSource().equals(recipeSearchViewModel)){
                    recipeBookmarkView.firePropertyChanged();
                }
            }
        });


        viewManagerModel.setActiveView(recipeBookmarkView.viewName);
        viewManagerModel.firePropertyChanged();

        app.pack();
        app.setVisible(true);
    }
}

package app;

import data_access.FileGroceryListDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import view.GroceryListView;
import interface_adapter.homepage.HomePageViewModel;
import view.HomePage;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import view.ViewManager;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame app = new JFrame("Example: Login");
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        app.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SignupViewModel signupViewModel = new SignupViewModel();
        GroceryListViewModel groceryListViewModel = new GroceryListViewModel();
        HomePageViewModel homePageViewModel = new HomePageViewModel();

        InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

        FileGroceryListDataAccessObject groceryListDataAccessObject;
        try{
            groceryListDataAccessObject = new FileGroceryListDataAccessObject();
        }catch (IOException e ){
            throw new RuntimeException("Unable to read groceryList.json");
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, userDataAccessObject);
        HomePage homePage = new HomePage(homePageViewModel);
        views.add(signupView, signupView.viewName);
        views.add(homePage, homePage.viewName);

        GroceryListView groceryListView = GroceryListUseCaseFactory.create(viewManagerModel, groceryListViewModel, groceryListDataAccessObject);
        views.add(groceryListView, groceryListView.viewName);

        viewManagerModel.setActiveView(groceryListView.viewName);
        viewManagerModel.firePropertyChanged();

        app.pack();
        app.setVisible(true);
    }
}

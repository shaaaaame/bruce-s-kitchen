package app;

import data_access.FileGroceryListDataAccessObject;
import data_access.FileUserDataAccessObject;
import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
import view.*;
import interface_adapter.homepage.HomePageViewModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame app = new JFrame("Bruce's Kitchen");
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        app.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        GroceryListViewModel groceryListViewModel = new GroceryListViewModel();
        HomePageViewModel homePageViewModel = new HomePageViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new UserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileGroceryListDataAccessObject groceryListDataAccessObject;
        try {
            groceryListDataAccessObject = new FileGroceryListDataAccessObject();
        } catch (IOException e){
            throw new RuntimeException("Unable to read groceryList.json");
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        HomePageView homePageView = new HomePageView(homePageViewModel);
        views.add(homePageView, homePageView.viewName);

        GroceryListView groceryListView = GroceryListUseCaseFactory.create(viewManagerModel, groceryListViewModel, groceryListDataAccessObject);
        views.add(groceryListView, groceryListView.viewName);

        viewManagerModel.setActiveView(homePageView.viewName);
        viewManagerModel.firePropertyChanged();

        app.pack();
        app.setVisible(true);
    }
}

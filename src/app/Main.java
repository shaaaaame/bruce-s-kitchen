package app;

import data_access.InMemoryUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.ViewManagerModel;
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

        InMemoryUserDataAccessObject userDataAccessObject;
        userDataAccessObject = new InMemoryUserDataAccessObject();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        app.pack();
        app.setVisible(true);
    }
}

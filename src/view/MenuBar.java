package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar{

    public MenuBar (ViewManagerModel viewManagerModel){

        JMenu menu = new JMenu("Menu");

        JMenuItem search = new JMenuItem("Search");
        CardLayout cardLayout = new CardLayout();

        JMenu userAccount = new JMenu("User");
        JMenuItem signUp = new JMenuItem("Sign up");
        JMenuItem logIn = new JMenuItem("Log in");
        JMenuItem bookmarked = new JMenuItem("Bookmarked recipes");

        userAccount.add(signUp);
        userAccount.add(logIn);
        userAccount.add(bookmarked);

        JMenu recipe = new JMenu("Recipe");
        JMenuItem searchRecipe = new JMenuItem("Search existing recipes");
        JMenuItem bookmarkRecipe = new JMenuItem("Bookmark recipe");
        JMenuItem createRecipe = new JMenuItem("Create new recipe");

        recipe.add(searchRecipe);
        recipe.add(bookmarkRecipe);
        recipe.add(createRecipe);

        JMenu groceryList = new JMenu("GroceryList");
        JMenuItem createGroceryList = new JMenuItem("Create Grocery List");
        JMenuItem searchGroceryList = new JMenuItem("Search Grocery List");

        groceryList.add(createGroceryList);
        groceryList.add(searchGroceryList);

        menu.add(userAccount);
        menu.add(recipe);
        menu.add(groceryList);

        this.add(menu);

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Login");
                viewManagerModel.firePropertyChanged();
            }
        });

        groceryList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Grocery List");
                viewManagerModel.firePropertyChanged();
            }
        });

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Sign Up");
                viewManagerModel.firePropertyChanged();
            }
        });


    }
}
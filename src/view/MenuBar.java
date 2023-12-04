package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuBar extends JMenuBar  {

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
        JMenuItem browseRecipe = new JMenuItem("Browse recipes");
        JMenuItem createRecipe = new JMenuItem("Create new recipe");

        recipe.add(searchRecipe);
        recipe.add(browseRecipe);
        recipe.add(createRecipe);

        JMenu groceryList = new JMenu("GroceryList");
        JMenuItem createGroceryList = new JMenuItem("Create Grocery List");
        JMenuItem showGroceryLists = new JMenuItem("Show Grocery Lists");

        groceryList.add(createGroceryList);
        groceryList.add(showGroceryLists);

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

        createGroceryList.addActionListener(new ActionListener() {
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

        searchRecipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Recipe Search");
                viewManagerModel.firePropertyChanged();
            }
        });

        browseRecipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Recipe Browse");
                viewManagerModel.firePropertyChanged();
            }
        });

        showGroceryLists.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Show Grocery List");
                viewManagerModel.firePropertyChanged();
            }
        });

        createRecipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Create Recipe");
                viewManagerModel.firePropertyChanged();
            }
        });

    }
}

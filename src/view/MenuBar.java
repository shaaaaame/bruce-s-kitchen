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
        JMenuItem groceryList = new JMenuItem("Grocery List");
        JMenuItem bookmarked = new JMenuItem("Bookmarked");
        JMenuItem createRecipe = new JMenuItem("Create recipe");
        JMenuItem signUp = new JMenuItem("Signup");
        JMenuItem login = new JMenuItem("Login");
        CardLayout cardLayout = new CardLayout();

        menu.add(groceryList);
        menu.add(bookmarked);
        menu.add(createRecipe);
        menu.add(signUp);
        menu.add(login);
        this.add(menu);

        login.addActionListener(new ActionListener() {
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

        createRecipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewManagerModel.setActiveView("Recipe creator");
                viewManagerModel.firePropertyChanged();
            }
        });

    }
}

package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.homepage.HomePageState;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupViewModel;

public class HomePage extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "homePage";
    public final HomePageViewModel homePageViewModel;
    public HomePage(HomePageViewModel homePageViewModel){

        this.homePageViewModel = homePageViewModel;
        homePageViewModel.addPropertyChangeListener(this);

        JFrame jFrame = new JFrame("Bruce's Kitchen");
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(1600, 1200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel searchLabel = new JLabel(HomePageViewModel.SEARCH_LABEL);
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();

            }
        });
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem groceryList = new JMenuItem("Grocery List");
        JMenuItem bookmarked = new JMenuItem("BookMarked");
        JMenuItem createRecipe = new JMenuItem("Create recipe");
        JMenuItem userProfile = new JMenuItem("User profile");

        menu.add(groceryList);
        menu.add(bookmarked);
        menu.add(createRecipe);
        menu.add(userProfile);
        menuBar.add(menu);

        jFrame.setJMenuBar(menuBar);
        jFrame.add(searchLabel);
        jFrame.add(searchField);
        jFrame.add(searchButton);

        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

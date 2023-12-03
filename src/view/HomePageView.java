package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.homepage.HomePageViewModel;

public class HomePageView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "homePage";
    public final HomePageViewModel homePageViewModel;
    public HomePageView(HomePageViewModel homePageViewModel){

        this.homePageViewModel = homePageViewModel;
        homePageViewModel.addPropertyChangeListener(this);

        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.PAGE_AXIS));

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

        this.setLayout(new BorderLayout());
        this.add(menuBar, BorderLayout.NORTH);
        this.add(mainContent, BorderLayout.CENTER);

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

        mainContent.add(searchLabel);
        mainContent.add(searchField);
        mainContent.add(searchButton);

        ImageIcon duck = new ImageIcon(getClass().getResource("/Resources/peckingduck.jpeg"));
        JLabel img = new JLabel(duck);
        mainContent.add(img);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomePageViewModel;

public class HomePageView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "homePage";
    public final HomePageViewModel homePageViewModel;
    public HomePageView(ViewManagerModel viewManagerModel, HomePageViewModel homePageViewModel){

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



        ImageIcon duck = new ImageIcon(getClass().getResource("/Resources/peckingduck.jpeg"));
        JLabel img = new JLabel(duck);
        mainContent.add(searchField);

        mainContent.add(searchButton);
        mainContent.add(img);

        this.add(mainContent);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

package view;

import interface_adapter.grocery_list.GroceryListController;
import interface_adapter.grocery_list.GroceryListViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GroceryListView  extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Grocery List";
    private final GroceryListViewModel groceryListViewModel;
    private final GroceryListController groceryListController;

    public GroceryListView(GroceryListController groceryListController, GroceryListViewModel groceryListViewModel) {
        this.groceryListViewModel = groceryListViewModel;
        this.groceryListController = groceryListController;

        // TODO: uncomment once implemented
        // implement button function, send data
        // groceryListViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(GroceryListViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField();
        JComboBox<JTextField> ingredientBox = new JComboBox<>();
        JPanel buttons = new JPanel();
        JButton backButton = new JButton("back");
        JButton submitButton = new JButton("submit");

        buttons.add(backButton);
        buttons.add(submitButton);

        this.add(title);
        this.add(nameField);
        this.add(ingredientBox);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

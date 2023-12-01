package view;

import interface_adapter.grocery_list.GroceryListController;
import interface_adapter.grocery_list.GroceryListState;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.signup.SignupState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(submitButton)) {
                            GroceryListState currentState = groceryListViewModel.getState();

                            groceryListController.execute(
                                    currentState.getName(),
                                    currentState.getUserId(),
                                    currentState.getIngredients()
                            );
                        }
                    }
                }
        );

        nameField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        GroceryListState currentState = groceryListViewModel.getState();
                        String text = nameField.getText() + e.getKeyChar();
                        currentState.setName(text);
                        groceryListViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        //Build the UI

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

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

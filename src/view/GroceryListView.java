package view;

import interface_adapter.grocery_list.GroceryListController;
import interface_adapter.grocery_list.GroceryListState;
import interface_adapter.grocery_list.GroceryListViewModel;
import interface_adapter.signup.SignupState;
import view.grocery_list.AddIngredientPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.beans.JavaBean;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Stack;

public class GroceryListView  extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Grocery List";
    private final GroceryListViewModel groceryListViewModel;
    private final GroceryListController groceryListController;
    JList<String> ingredientListPanel = new JList<String>();
    JTextField nameField = new JTextField();



    public GroceryListView(GroceryListController groceryListController, GroceryListViewModel groceryListViewModel) {
        this.groceryListViewModel = groceryListViewModel;
        this.groceryListController = groceryListController;

        // implement button function, send data
         groceryListViewModel.addPropertyChangeListener(this);


        // create title
        JLabel title = new JLabel(GroceryListViewModel.TITLE_LABEL);
        JPanel titleSpace = new JPanel();

        LabelTextPanel nameTextPanel = new LabelTextPanel(new JLabel("name:"), nameField);

        JLabel ingredientText = new JLabel("ingredients:");
        JScrollPane ingredientScrollPane = new JScrollPane(ingredientListPanel);
        JPanel ingredientBox = new JPanel();

        JPanel buttons = new JPanel();
        JButton backButton = new JButton("back");
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JButton submitButton = new JButton("submit");
        JButton removeIngredientButton = new JButton("remove ingredient");
        JButton addIngredientButton = new JButton("add ingredient");

        // Add listeners
        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(submitButton)) {

                            if (validateData()){
                                GroceryListState currentState = groceryListViewModel.getState();

                                groceryListController.execute(
                                        currentState.getName(),
                                        currentState.getUserId(),
                                        currentState.getIngredients()
                                );

                                JOptionPane.showMessageDialog(null, "Added Grocery List \"" + currentState.getName());

                                currentState.clear();
                                clearFields();
                                groceryListViewModel.setState(currentState);



                            } else {
                                JOptionPane.showMessageDialog(null, "some fields are blank!");
                            }



                        }
                    }
                }
        );

        removeIngredientButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(removeIngredientButton)){

                            GroceryListState currentState = groceryListViewModel.getState();
                            List<String> currentIngredients = currentState.getIngredients();

                            if (ingredientListPanel.isSelectionEmpty()){
                                JOptionPane.showMessageDialog(null, "make a selection!");
                            } else{
                                currentIngredients.removeAll(ingredientListPanel.getSelectedValuesList());

                                ingredientListPanel.setListData(currentIngredients.toArray(new String[0]));
                                currentState.setIngredients(currentIngredients);
                                groceryListViewModel.setState(currentState);

                            }



                        }
                    }
                }
        );

        addIngredientButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addIngredientButton)){

                            JTextField ingredientField = new JTextField();
                            JTextField amountField = new JTextField();

                            AddIngredientPanel addIngredientPanel = new AddIngredientPanel(ingredientField, amountField);

                            String[] options = { "add", "cancel" };
                            int result = JOptionPane.showOptionDialog(null,
                                    addIngredientPanel, "add new ingredient",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null,options , null);

                            if (result == JOptionPane.YES_OPTION){
                                if (ingredientField.getText().equals("")){
                                    JOptionPane.showMessageDialog(null, "ingredient must be filled!");
                                }else{
                                    GroceryListState currentState = groceryListViewModel.getState();
                                    List<String> currentIngredients = currentState.getIngredients();
                                    currentIngredients.add(ingredientField.getText() + ": " + amountField.getText());
                                    currentState.setIngredients(currentIngredients);
                                    groceryListViewModel.setState(currentState);

                                    ingredientListPanel.setListData(currentIngredients.toArray(new String[0]));
                                }
                            }
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

        // Edit layout

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        titleSpace.setMaximumSize(new Dimension(9999, 100));
        titleSpace.setLayout(new BorderLayout());
        titleSpace.add(title, BorderLayout.LINE_START);
        titleSpace.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        ingredientText.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientText.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        ingredientBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingredientBox.setLayout(new BoxLayout(ingredientBox, BoxLayout.PAGE_AXIS));
        ingredientBox.add(ingredientText);
        ingredientBox.add(ingredientScrollPane);
        ingredientBox.setPreferredSize(new Dimension(300, 300));
        ingredientBox.setMaximumSize(new Dimension(9999, 500));
        ingredientScrollPane.setBackground(new Color(255, 255, 255));
        ingredientScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttons.setLayout(new BorderLayout());

        backButton.setBackground(Colors.highlight1);
        submitButton.setBackground(Colors.highlight1);
        addIngredientButton.setBackground(Colors.highlight1);
        removeIngredientButton.setBackground(Colors.highlight1);

        backButton.setPreferredSize(new Dimension(150, 30));
        submitButton.setPreferredSize(new Dimension(150, 30));
        addIngredientButton.setPreferredSize(new Dimension(150, 30));
        removeIngredientButton.setPreferredSize(new Dimension(150, 30));


        // Build the UI

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        rightPanel.add(removeIngredientButton);
        rightPanel.add(addIngredientButton);
        rightPanel.add(submitButton);
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        leftPanel.add(backButton);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        buttons.add(leftPanel, BorderLayout.LINE_START);
        buttons.add(rightPanel, BorderLayout.LINE_END);

        this.add(titleSpace);
        this.add(nameTextPanel);
        this.add(ingredientBox);
        this.add(buttons);
    }

    private void clearFields(){
        this.nameField.setText("");
        this.ingredientListPanel.setListData(new String[0]);
    }

    private boolean validateData(){
        GroceryListState currentState = groceryListViewModel.getState();

        boolean nameCheck = !currentState.getName().isEmpty();
        boolean ingredientCheck = !currentState.getIngredients().isEmpty();

        return nameCheck && ingredientCheck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

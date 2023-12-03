package view.grocery_list;

import view.LabelTextPanel;

import javax.swing.*;
import java.awt.*;

public class AddIngredientPanel extends JPanel {
    JTextField ingredientField;
    JTextField amountField;
    public AddIngredientPanel(JTextField ingredientField, JTextField amountField){
        this.ingredientField = ingredientField;
        this.amountField = amountField;

        JLabel addIngredientTitle = new JLabel("add ingredient");
        JPanel inputSection = new JPanel();
        inputSection.add(new LabelTextPanel(new JLabel("ingredient"), ingredientField));
        inputSection.add(new LabelTextPanel(new JLabel("amount"), amountField));

        addIngredientTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        addIngredientTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout());
        this.add(addIngredientTitle, BorderLayout.PAGE_START);
        this.add(inputSection);
    }
}

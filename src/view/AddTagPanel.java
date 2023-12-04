package view;

import javax.swing.*;
import java.awt.*;

public class AddTagPanel extends JPanel {
    JTextField tagField;


    public AddTagPanel(JTextField tagField) {
        this.tagField = tagField;

        JLabel addIngredientTitle = new JLabel("add tag");
        JPanel inputSection = new JPanel();
        inputSection.add(new LabelTextPanel(new JLabel("tag"), tagField));

        addIngredientTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        addIngredientTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout());
        this.add(addIngredientTitle, BorderLayout.PAGE_START);
        this.add(inputSection);
    }
}
package view.grocery_list;

import entity.GroceryList;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class SingleGroceryList extends JPanel {
    String name;
    List<String> ingredients;
    LocalDateTime dateCreated;
    public SingleGroceryList(GroceryList groceryList){
        this.name = groceryList.getName();
        this.ingredients = groceryList.getIngredients();
        this.dateCreated = groceryList.getDate();

        JLabel titleLabel = new JLabel(this.name);
        JLabel dateCreatedLabel = new JLabel("date created: " + this.dateCreated.toLocalDate().toString());
        JPanel ingredientPanel = new JPanel();
        JLabel ingredientText = new JLabel("ingredients:");
        JList<String> ingredientListPanel = new JList<String>();
        JScrollPane ingredientScrollPane = new JScrollPane(ingredientListPanel);

        ingredientListPanel.setListData(this.ingredients.toArray(new String[0]));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
        titleLabel.setFont(new Font("Monospace", Font.PLAIN, 20));
        dateCreatedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateCreatedLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        dateCreatedLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        ingredientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));


        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.PAGE_AXIS));
        ingredientText.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientText.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        ingredientText.setFont(new Font("Roboto", Font.PLAIN, 15));

        ingredientScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ingredientPanel.add(ingredientText);
        ingredientPanel.add(ingredientScrollPane);
        this.add(titleLabel);
        this.add(dateCreatedLabel);
        this.add(ingredientPanel);

    }


}

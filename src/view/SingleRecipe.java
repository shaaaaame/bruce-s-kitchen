package view;

import entity.Recipe;
import entity.Tag;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SingleRecipe extends JPanel {
    String name;
    List<String> ingredients;
    LocalDateTime dateCreated;
    String instructions;
    String servings;
    Tag[] tags;


    public SingleRecipe(Recipe recipe){
        this.name = recipe.name;
        this.ingredients = recipe.ingredients;
        this.dateCreated = recipe.getDate();
        this.instructions = recipe.instructions;
        this.servings = recipe.servings;
        this.tags = recipe.tags;



        JLabel titleLabel = new JLabel(this.name);
        JLabel dateCreatedLabel = new JLabel("Date Created: " + this.dateCreated.toLocalDate().toString());
        JLabel servingLabel = new JLabel(this.servings);

        JPanel ingredientPanel = new JPanel();
        JLabel ingredientText = new JLabel("Ingredients:");
        JList<String> ingredientListPanel = new JList<String>();
        JScrollPane ingredientScrollPane = new JScrollPane(ingredientListPanel);
        ingredientListPanel.setListData(this.ingredients.toArray(new String[0]));

        List<String> tagStringList = new ArrayList<String>();
        for (Tag tag : this.tags){
            tagStringList.add(tag.tagName);
        }
        JPanel tagPanel = new JPanel();
        JLabel tagText = new JLabel("Tags:");
        JList<String> tagListPanel = new JList<String>();
        JScrollPane tagScrollPlane = new JScrollPane(tagListPanel);
        tagListPanel.setListData(tagStringList.toArray(new String[0]));

        JTextArea instructionLabel = new JTextArea(this.instructions);
        instructionLabel.setLineWrap(true);


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
        titleLabel.setFont(new Font("Monospace", Font.PLAIN, 20));
        dateCreatedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        dateCreatedLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        dateCreatedLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
        ingredientPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));
        tagPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        tagPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 20, 20));


        ingredientPanel.setLayout(new BoxLayout(ingredientPanel, BoxLayout.PAGE_AXIS));
        ingredientText.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientText.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        ingredientText.setFont(new Font("Roboto", Font.PLAIN, 15));

        tagPanel.setLayout(new BoxLayout(tagPanel, BoxLayout.PAGE_AXIS));
        tagText.setAlignmentX(Component.LEFT_ALIGNMENT);
        tagText.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        tagText.setFont(new Font("Roboto", Font.PLAIN, 15));

        ingredientScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ingredientScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientListPanel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ingredientPanel.add(ingredientText);
        ingredientPanel.add(ingredientScrollPane);
        tagPanel.add(tagText);
        tagPanel.add(tagScrollPlane);

        this.add(titleLabel);
        this.add(dateCreatedLabel);
        this.add(servingLabel);
        this.add(ingredientPanel);
        this.add(tagListPanel);
        this.add(instructionLabel);

    }
}

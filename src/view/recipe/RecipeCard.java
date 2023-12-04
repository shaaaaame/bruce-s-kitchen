package view.recipe;

import entity.Recipe;
import entity.Tag;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class RecipeCard extends JPanel {

    public RecipeCard(Recipe recipe){

        JLabel nameLabel = new JLabel(recipe.getName());
        JLabel servingsLabel = new JLabel(recipe.servings);

        JLabel ingredientLabel = new JLabel("Ingredients: ");
        JList<String> ingredientJList = new JList<String>();
        JScrollPane ingredientScrollPane = new JScrollPane(ingredientJList);

        JLabel tagLabel = new JLabel(buildTagString(recipe.tags));
        JTextArea instructionTextArea = new JTextArea(recipe.getInstructions());
        JLabel dateCreatedLabel = new JLabel(recipe.getDate().toLocalDate().toString());
        JPanel content = new JPanel();


        content.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));

        nameLabel.setFont(new Font("Dialog", Font.PLAIN, 15));
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));


        ingredientJList.setListData(recipe.ingredients.toArray(new String[0]));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        servingsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        tagLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        instructionTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        instructionTextArea.setLineWrap(true);
        dateCreatedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameLabel.setPreferredSize(new Dimension(900, 50));

        servingsLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        dateCreatedLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        ingredientLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        instructionTextArea.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));


        content.add(nameLabel);
        content.add(dateCreatedLabel);
        content.add(servingsLabel);
        content.add(ingredientLabel);
        content.add(ingredientScrollPane);

        // TODO add tags
        content.add(instructionTextArea);

        this.add(content);
    }

    private String buildTagString(Tag[] tags){
        StringBuilder sb = new StringBuilder();

        for ( Tag tag : tags){
            sb.append(tag.toString() + ", ");
        }

        if (tags.length > 0){
            sb.delete(sb.length() - 2, sb.length());

        }


        return sb.toString();
    }
}

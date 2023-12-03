package view;

import interface_adapter.recipeCreator.RecipeCreatorController;
import interface_adapter.recipeCreator.RecipeCreatorState;
import interface_adapter.recipeCreator.RecipeCreatorViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;


public class RecipeCreatorView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "recipeCreator";
    private final RecipeCreatorViewModel recipeCreatorViewModel;
    private final RecipeCreatorController recipeCreatorController;

    public RecipeCreatorView(RecipeCreatorController recipeCreatorController, RecipeCreatorViewModel recipeCreatorViewModel) {

        this.recipeCreatorViewModel = recipeCreatorViewModel;
        this.recipeCreatorController = recipeCreatorController;
        recipeCreatorViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RecipeCreatorViewModel.TITLE_LABEL);
        JPanel titlespace = new JPanel();

        JTextField name = new JTextField();
        LabelTextPanel nameP = new LabelTextPanel(new JLabel("Name:"), name);

        JList<String> ingredientsP = new JList<>();
        JLabel ingredientText = new JLabel("Ingredients: ");
        JScrollPane ingredientScroll = new JScrollPane(ingredientsP);
        JPanel ingredientBox = new JPanel();

        JPanel buttons = new JPanel();
        JButton doneButton = new JButton("Done!");
        JButton removeIng = new JButton("Remove ingredient");
        JButton addIng = new JButton("Add ingredient");

        doneButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(doneButton)){
                            RecipeCreatorState curr = recipeCreatorViewModel.getState();
                            recipeCreatorController.execute(
                                curr.getName(),
                                    curr.getIngredients(),
                                    curr.getInstructions(),
                                    curr.getServings(),
                                    curr.getUser_id(),
                                    curr.getTags()
                            );
                        }
                    }
                }
        );
        /*

        JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        panel.add(nameLabel);
        panel.add(nameField);

        JLabel ingredientsLabel = new JLabel("Ingredients:");
        JTextField ingredientsField = new JTextField(20);
        panel.add(ingredientsLabel);
        panel.add(ingredientsField);

        JLabel servingsLabel = new JLabel("Servings:");
        JTextField servingsField = new JTextField(20);
        panel.add(servingsLabel);
        panel.add(servingsField);

        JLabel timeLabel = new JLabel("Time (mins):");
        JTextField timeField = new JTextField(20);
        panel.add(timeLabel);
        panel.add(timeField);

        JLabel tagsLabel = new JLabel("Tags:");
        JTextField tagsField = new JTextField(20);
        panel.add(tagsLabel);
        panel.add(tagsField);

        JLabel instructionsLabel = new JLabel("Instructions:");
        JTextArea instructionsArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(instructionsArea);
        panel.add(instructionsLabel);
        panel.add(scrollPane);

        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);
        meow.add(panel);
        meow.setSize(400, 300);
        meow.setVisible(true);
        */
    }
        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }

}

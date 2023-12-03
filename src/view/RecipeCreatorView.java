package view;

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

    public RecipeCreatorView(RecipeCreatorViewModel recipeCreatorViewModel) {

        this.recipeCreatorViewModel = recipeCreatorViewModel;
        recipeCreatorViewModel.addPropertyChangeListener(this);

        JFrame meow = new JFrame("Create your own Recipe");
        meow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    }
        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }

}

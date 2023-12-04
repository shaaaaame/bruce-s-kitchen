package view;

import interface_adapter.grocery_list.GroceryListState;
import interface_adapter.recipeCreator.RecipeCreatorController;
import interface_adapter.recipeCreator.RecipeCreatorState;
import interface_adapter.recipeCreator.RecipeCreatorViewModel;
import view.grocery_list.AddIngredientPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;


public class RecipeCreatorView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Create Recipe";
    private final RecipeCreatorViewModel recipeCreatorViewModel;
    private final RecipeCreatorController recipeCreatorController;

    public RecipeCreatorView(RecipeCreatorController recipeCreatorController, RecipeCreatorViewModel recipeCreatorViewModel) {

        this.recipeCreatorViewModel = recipeCreatorViewModel;
        this.recipeCreatorController = recipeCreatorController;
        recipeCreatorViewModel.addPropertyChangeListener(this);

        //title
        JLabel title = new JLabel(RecipeCreatorViewModel.TITLE_LABEL);
        JPanel titlespace = new JPanel();
        title.setFont(new Font("Serif", Font.BOLD, 20));
        titlespace.add(title);
        titlespace.setAlignmentX(Component.CENTER_ALIGNMENT);


        //name
        JTextField nameField = new JTextField();
        LabelTextPanel nameP = new LabelTextPanel(new JLabel("Name:"), nameField);

        //insturctions
        JTextField instructionsField = new JTextField();
        LabelTextPanel instructionsPannel = new LabelTextPanel(new JLabel("Instructions: "), instructionsField);

        //ingredients
        JList<String> ingredientsP = new JList<>();
        JLabel ingredientText = new JLabel("Ingredients: ");
        JScrollPane ingredientScroll = new JScrollPane(ingredientsP);
        JPanel ingredientBox = new JPanel();

        //Tags
        JList<String> tagsPannel = new JList<>();
        JLabel tagsText = new JLabel("Tags: ");
        JScrollPane tagsScroll = new JScrollPane(tagsPannel);
        JPanel tagsBox = new JPanel();

        JTextField servingsField = new JTextField();
        LabelTextPanel servingsPannel = new LabelTextPanel(new JLabel("Servings: "), servingsField);

        //buttons
        JButton doneButton = new JButton("Done!");
        JButton removeIng = new JButton("Remove ingredient");
        JButton addIng = new JButton("Add ingredient");

        JButton addTag = new JButton("Add tag");
        JButton removeTag = new JButton("Remove tag");

        JPanel buttons2 = new JPanel();

        JPanel buttons = new JPanel();
        buttons.setLayout(new BorderLayout());

        addIng.setBackground(Colors.highlight1);
        removeIng.setBackground(Colors.highlight1);
        addIng.setPreferredSize(new Dimension(150, 30));
        removeIng.setPreferredSize(new Dimension(150, 30));

        addTag.setBackground(Colors.highlight1);
        removeTag.setBackground(Colors.highlight1);
        addTag.setPreferredSize(new Dimension(150, 30));
        removeTag.setPreferredSize(new Dimension(150, 30));
        JPanel leftP = new JPanel();

        doneButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(doneButton)) {

                            if (validateData()) {
                                RecipeCreatorState curr = recipeCreatorViewModel.getState();
                                recipeCreatorController.execute(
                                        curr.getUser_id(),
                                        curr.getName(),
                                        curr.getServings(),
                                        curr.getIngredients(),
                                        curr.getTags(),
                                        curr.getInstructions()
                                );
                            } else {
                                JOptionPane.showMessageDialog(null, "Incorrect fields");
                            }
                        }
                    }
                }

        );

        addTag.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addTag)){

                            JTextField tagField = new JTextField();

                            AddTagPanel addTagPanel = new AddTagPanel(tagField);

                            String[] options = { "add", "cancel" };
                            int result = JOptionPane.showOptionDialog(null,
                                    addTagPanel, "add new tag",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null,options , null);

                            if (result == JOptionPane.YES_OPTION){
                                if (tagField.getText().equals("")){
                                    JOptionPane.showMessageDialog(null, "ingredient must be filled!");
                                }else{
                                    RecipeCreatorState currentState = recipeCreatorViewModel.getState();
                                    List<String> currentIngredients = currentState.getIngredients();
                                    currentIngredients.add(tagField.getText());
                                    currentState.setIngredients(currentIngredients);
                                    recipeCreatorViewModel.setState(currentState);
                                    tagsPannel.setListData(currentIngredients.toArray(new String[0]));
                                }
                            }
                        }
                    }
                }
        );
        
        removeTag.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(removeTag)){

                            RecipeCreatorState currentState = recipeCreatorViewModel.getState();
                            List<String> currentIngredients = currentState.getIngredients();

                            if (tagsPannel.isSelectionEmpty()){
                                JOptionPane.showMessageDialog(null, "make a selection!");
                            } else{
                                currentIngredients.removeAll(tagsPannel.getSelectedValuesList());

                                tagsPannel.setListData(currentIngredients.toArray(new String[0]));
                                currentState.setIngredients(currentIngredients);
                                recipeCreatorViewModel.setState(currentState);
                            }



                        }
                    }
                }
        );

        removeIng.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(removeIng)) {
                            RecipeCreatorState curr = recipeCreatorViewModel.getState();
                            List<String> currIngredients = curr.getIngredients();

                            if (ingredientsP.isSelectionEmpty()) {
                                JOptionPane.showMessageDialog(null, "make a selection");
                            } else {
                                currIngredients.removeAll(ingredientsP.getSelectedValuesList());
                                ingredientsP.setListData(currIngredients.toArray(new String[0]));
                                curr.setIngredients(currIngredients);
                                recipeCreatorViewModel.setState(curr);
                            }
                        }
                    }
                }
        );

        addIng.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(addIng)){

                            JTextField ingredientField = new JTextField();
                            JTextField amt = new JTextField();

                            AddIngredientPanel addIngredientPanel = new AddIngredientPanel(ingredientField, amt);

                            String[] options = { "add", "cancel" };
                            int result = JOptionPane.showOptionDialog(null,
                                    addIngredientPanel, "add new ingredient",
                                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                                    null,options , null);

                            if (result == JOptionPane.YES_OPTION){
                                if (ingredientField.getText().equals("")){
                                    JOptionPane.showMessageDialog(null, "ingredient must be filled!");
                                }else{
                                    RecipeCreatorState curr = recipeCreatorViewModel.getState();
                                    List<String> currentIngredients = curr.getIngredients();
                                    currentIngredients.add(ingredientField.getText() + ": " + amt.getText());
                                    curr.setIngredients(currentIngredients);
                                    recipeCreatorViewModel.setState(curr);

                                    ingredientsP.setListData(currentIngredients.toArray(new String[0]));
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
                        RecipeCreatorState curr = recipeCreatorViewModel.getState();
                        String txt = nameField.getText() + e.getKeyChar();
                        curr.setName(txt);
                        recipeCreatorViewModel.setState(curr);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        instructionsField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RecipeCreatorState curr = recipeCreatorViewModel.getState();
                        String txt = instructionsField.getText() + e.getKeyChar();
                        curr.setName(txt);
                        recipeCreatorViewModel.setState(curr);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        instructionsField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RecipeCreatorState currentState = recipeCreatorViewModel.getState();
                        String text = nameField.getText() + e.getKeyChar();
                        currentState.setName(text);
                        recipeCreatorViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        servingsField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RecipeCreatorState currentState = recipeCreatorViewModel.getState();
                        String text = nameField.getText() + e.getKeyChar();
                        currentState.setName(text);
                        recipeCreatorViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        tagsText.setAlignmentX(Component.LEFT_ALIGNMENT);
        tagsText.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        tagsBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        tagsBox.add(tagsText);
        tagsBox.add(tagsScroll);
        tagsBox.setPreferredSize(new Dimension(700,100));
        tagsScroll.setBackground(new Color(255,255,255));
        tagsScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        ingredientText.setAlignmentX(Component.LEFT_ALIGNMENT);
        ingredientText.setBorder(BorderFactory.createEmptyBorder(300, 5, 5, 5));
        ingredientBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingredientBox.add(ingredientText);
        ingredientBox.add(ingredientScroll);
        ingredientBox.setPreferredSize(new Dimension(700, 100));
        ingredientScroll.setBackground(new Color(255,255,255));
        ingredientScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel rightP = new JPanel();
        rightP.add(removeTag);
        rightP.add(addTag);

        leftP.add(removeIng);
        leftP.add(addIng);
        leftP.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttons.add(leftP, BorderLayout.LINE_START);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        nameField.add(Box.createRigidArea(new Dimension(0,200)));

        buttons2.add(rightP, BorderLayout.LINE_START);
        buttons2.setLayout(new BoxLayout(buttons2, BoxLayout.LINE_AXIS));

        this.add(nameP);
        this.add(tagsBox);
        this.add(buttons2);
        this.add(servingsPannel);
        this.add(ingredientBox);
        this.add(buttons);
        this.add(instructionsPannel);

        ingredientBox.revalidate();

        }
        private boolean validateData(){
            RecipeCreatorState curr = recipeCreatorViewModel.getState();
            boolean bame = !curr.getName().isEmpty();
            boolean bing = !curr.getIngredients().isEmpty();
            boolean binstructions = !curr.getInstructions().isEmpty();
            boolean bervings = !curr.getServings().isEmpty();

            return bervings && binstructions && bame && bing;
        }
        private void deleteIng(int start, int end){
            RecipeCreatorState curr = recipeCreatorViewModel.getState();
            List<String> currIng = curr.getIngredients();

            for (int i = end; i >= start; i--){
                currIng.remove(i);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {

        }

}

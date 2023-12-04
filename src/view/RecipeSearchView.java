package view;

import com.fasterxml.jackson.core.JsonProcessingException;
import entity.Recipe;
import interface_adapter.recipe_search.RecipeSearchController;
import interface_adapter.recipe_search.RecipeSearchState;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import view.recipe.RecipeCard;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class RecipeSearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Recipe Search";
    private final RecipeSearchViewModel recipeSearchViewModel;
    private final RecipeSearchController recipeSearchController;
    JPanel recipeCards = new JPanel();


    public RecipeSearchView(RecipeSearchViewModel recipeSearchViewModel, RecipeSearchController recipeSearchController) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.recipeSearchController = recipeSearchController;

        recipeSearchViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(RecipeSearchViewModel.TITLE_LABEL);
        JPanel titleSpace = new JPanel();

        JTextField searchField = new JTextField();
        LabelTextPanel searchTextPanel = new LabelTextPanel(new JLabel("Search:"), searchField);

        JPanel buttons = new JPanel();
        JButton backButton = new JButton("Back");
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JButton searchButton = new JButton("Search");
        JScrollPane mainContent = new JScrollPane(recipeCards);
        recipeCards.setLayout(new BoxLayout(recipeCards, BoxLayout.PAGE_AXIS));
        recipeCards.setBackground(new Color(255, 255, 255));

        searchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(searchButton)) {

                            if (validateData()){
                                RecipeSearchState currentState = recipeSearchViewModel.getState();
                                try {
                                    recipeSearchController.execute(currentState.getSearch());
                                } catch (JsonProcessingException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "some fields are blank!");
                            }
                        }
                    }
                }
        );
        searchField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        RecipeSearchState currentState = recipeSearchViewModel.getState();
                        String text = searchField.getText() + e.getKeyChar();
                        currentState.setSearch(text);
                        recipeSearchViewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {

                    }
                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );


        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        titleSpace.setMaximumSize(new Dimension(9999, 100));
        titleSpace.setLayout(new BorderLayout());
        titleSpace.add(title, BorderLayout.LINE_START);
        titleSpace.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainContent.setPreferredSize(new Dimension(800, 400));
        buttons.setLayout(new BorderLayout());

        backButton.setBackground(Colors.highlight1);
        searchButton.setBackground(Colors.highlight1);

        backButton.setPreferredSize(new Dimension(150, 30));
        searchButton.setPreferredSize(new Dimension(150, 30));

        // Build the UI

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));


        rightPanel.add(searchButton);
        rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        leftPanel.add(backButton);
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        buttons.add(leftPanel, BorderLayout.LINE_START);
        buttons.add(rightPanel, BorderLayout.LINE_END);

        this.add(titleSpace);
        this.add(searchTextPanel);
        this.add(mainContent);
        this.add(buttons);
    }

    private boolean validateData(){
        RecipeSearchState currentState = recipeSearchViewModel.getState();
        boolean searchCheck = !currentState.getSearch().isEmpty();
        return searchCheck;
    }

    private void updateListData(List<Recipe> recipes){
        this.recipeCards.removeAll();

        for(int i = 0; i < recipes.size(); i++){
            this.recipeCards.add(new RecipeCard(recipes.get(i)));
        }

        this.recipeCards.revalidate();
        this.recipeCards.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(recipeSearchViewModel)){
            RecipeSearchState currentState = recipeSearchViewModel.getState();
            List<Recipe> recipes = currentState.getRecipeList();
            updateListData(recipes);
        }
    }
}

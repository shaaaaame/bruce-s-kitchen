package view;

import entity.Recipe;
import interface_adapter.recipe_browse.RecipeBrowseController;
import interface_adapter.recipe_browse.RecipeBrowseState;
import interface_adapter.recipe_browse.RecipeBrowseViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.util.List;

public class RecipeBrowseView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Recipe Browse";
    private final RecipeBrowseViewModel recipeBrowseViewModel;
    private final RecipeSearchViewModel recipeSearchViewModel;
    private final RecipeBrowseController recipeBrowseController;

    private Map<UUID, Recipe> recipeMap = new HashMap<UUID, Recipe>();
    private List<String> namesList = new ArrayList<>();
    private List<Recipe> recipeList = new ArrayList<>();
    CardLayout singleItemCard = new CardLayout();
    JList<String> recipeNamesJList = new JList<>(namesList.toArray(new String[0]));
    JPanel singleItem = new JPanel(singleItemCard);


    public RecipeBrowseView(RecipeBrowseViewModel recipeBrowseViewModel,
                            RecipeSearchViewModel recipeSearchViewModel,
                            RecipeBrowseController recipeBrowseController) {
        this.recipeBrowseViewModel = recipeBrowseViewModel;
        this.recipeBrowseController = recipeBrowseController;
        this.recipeSearchViewModel = recipeSearchViewModel;
        recipeBrowseViewModel.addPropertyChangeListener(this);

        this.recipeBrowseController.execute();
        RecipeBrowseState recipeBrowseState = this.recipeBrowseViewModel.getState();

        recipeMap = recipeBrowseState.getRecipeMap();

        for (Recipe recipe : recipeMap.values()){
            recipeList.add(recipe);
            namesList.add(recipe.name);
        }

        JLabel title = new JLabel(RecipeBrowseViewModel.TITLE_LABEL);
        JPanel titleSpace = new JPanel();

        JScrollPane recipeListScrollPane = new JScrollPane(recipeNamesJList);
        JPanel mainContent = new JPanel();
        JLabel emptyText = new JLabel("Browse Recipes :)");
        JPanel emptyCard = new JPanel();
        for (Integer i = 0; i < recipeList.size(); i++){
            singleItem.add(new SingleRecipe(recipeList.get(i)));
        }

        JPanel select = new JPanel();
        select.add(new JLabel("Select from list!"));

        JPanel buttons = new JPanel();
        JButton back = new JButton("Back");

        emptyCard.setLayout(new BorderLayout());
        emptyCard.add(emptyText, BorderLayout.CENTER);
        emptyCard.setBackground(new Color(255, 255, 255));
        emptyCard.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setAlignmentY(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.PLAIN, 20));
        titleSpace.setMaximumSize(new Dimension(9999, 100));
        titleSpace.setLayout(new BorderLayout());
        titleSpace.add(title, BorderLayout.LINE_START);
        titleSpace.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        recipeListScrollPane.setMinimumSize(new Dimension(300, 600));
        singleItem.setMinimumSize(new Dimension(300, 600));
        recipeListScrollPane.setBorder(BorderFactory.createLineBorder(Colors.dark));
        singleItem.setBorder(BorderFactory.createLineBorder(Colors.dark));
        recipeNamesJList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        recipeNamesJList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        if (e.getSource().equals(recipeNamesJList)){
                            singleItemCard.show(singleItem, String.valueOf(recipeNamesJList.getSelectedIndex()));
                        }
                    }
                }
        );


        mainContent.setLayout(new GridLayout(1, 2, 15, 15));
        mainContent.setMinimumSize(new Dimension(700, 300));
        mainContent.add(recipeListScrollPane);
        mainContent.add(singleItem);

        singleItem.add(emptyCard, "Empty");
        singleItemCard.show(singleItem, "Empty");

        buttons.add(back, FlowLayout.LEFT);

        this.add(titleSpace);
        this.add(mainContent);
        this.add(buttons);

    }

    public void update(){

        this.recipeBrowseController.execute();
        recipeBrowseViewModel.firePropertyChanged();

        RecipeBrowseState currentState = recipeBrowseViewModel.getState();
        updateRecipeList(currentState.getRecipeMap());
        revalidate();
        repaint();
    }

    private void updateRecipeList(Map<UUID, Recipe> recipeMap){
        namesList.clear();
        recipeList.clear();
        recipeNamesJList.removeAll();
        singleItem.removeAll();
        List<Recipe> l = new ArrayList<>(recipeMap.values());
        for(int i = 0; i < recipeMap.size(); i++){
            namesList.add(l.get(i).name);
            recipeList.add(l.get(i));
            singleItem.add(new SingleRecipe(l.get(i)), String.valueOf(i));
        }

        recipeNamesJList.setListData(namesList.toArray(new String[0]));

        revalidate();
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(recipeBrowseViewModel) || evt.getSource().equals(recipeSearchViewModel)){
            RecipeBrowseState currentState = recipeBrowseViewModel.getState();
            Map<UUID, Recipe> recipes = currentState.getRecipeMap();
            System.out.println(recipes.size());
            updateRecipeList(recipes);
        }
    }
}

package view;

import entity.Recipe;
import interface_adapter.ViewManagerModel;
import interface_adapter.recipe_bookmark.GetBookmarkedController;
import interface_adapter.recipe_bookmark.GetBookmarkedState;
import interface_adapter.recipe_bookmark.GetBookmarkedViewModel;
import interface_adapter.recipe_bookmark.RecipeBookmarkController;
import interface_adapter.recipe_search.RecipeSearchState;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import view.recipe.RecipeCard;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class RecipeBookmarkView extends JPanel implements PropertyChangeListener {
    public final String viewName = "Recipe Bookmark";
    private final ViewManagerModel viewManagerModel;
    private final GetBookmarkedController getBookmarkedController;
    private final GetBookmarkedViewModel getBookmarkedViewModel;
    JPanel recipeCards = new JPanel();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public RecipeBookmarkView(ViewManagerModel viewManagerModel, GetBookmarkedViewModel getBookmarkedViewModel, GetBookmarkedController getBookmarkedController) {
        this.viewManagerModel = viewManagerModel;
        this.getBookmarkedController = getBookmarkedController;
        this.getBookmarkedViewModel = getBookmarkedViewModel;

        this.getBookmarkedController.execute(viewManagerModel.getCurentUser());

        JLabel title = new JLabel(GetBookmarkedViewModel.TITLE_LABEL);
        JPanel titleSpace = new JPanel();

        JPanel buttons = new JPanel();
        JButton backButton = new JButton("Back");
        JScrollPane mainContent = new JScrollPane(recipeCards);
        recipeCards.setLayout(new BoxLayout(recipeCards, BoxLayout.PAGE_AXIS));
        recipeCards.setBackground(new Color(255, 255, 255));

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

        backButton.setPreferredSize(new Dimension(150, 30));
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(titleSpace);
        this.add(mainContent);
        this.add(buttons);
    }

    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.viewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    private void updateListData(List<Recipe> recipes){
        this.recipeCards.removeAll();
        String username = viewManagerModel.getCurentUser();

        System.out.println(recipes.size());
        for(int i = 0; i < recipes.size(); i++){
            this.recipeCards.add(new RecipeCard(recipes.get(i), null, username));
        }

        this.recipeCards.revalidate();
        this.recipeCards.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource().equals(getBookmarkedViewModel)){
            GetBookmarkedState currentState = getBookmarkedViewModel.getState();
            List<Recipe> recipes = currentState.getRecipeList();
            updateListData(recipes);
            this.getBookmarkedController.execute(viewManagerModel.getCurentUser());
            System.out.println("view");
        }
    }
}

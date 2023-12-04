package interface_adapter.recipe_search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipeSearchViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Search a recipe!";

    private RecipeSearchState state = new RecipeSearchState();

    public void setState(RecipeSearchState state) {this.state = state;}
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RecipeSearchViewModel(){
        super("Recipe Search");
    }

    public RecipeSearchState getState() {return state;}

}

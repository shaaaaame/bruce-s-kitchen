package interface_adapter.recipe_browse;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipeBrowseViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Show saved recipes";
    private RecipeBrowseState state = new RecipeBrowseState();

    public void setState(RecipeBrowseState state){this.state = state;
    }
    public RecipeBrowseState getState() {return state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RecipeBrowseViewModel(){
        super("Show Saved Recipes");
    }
}

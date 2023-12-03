package interface_adapter.recipeCreator;
import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RecipeCreatorViewModel extends ViewModel{

    public static final String TITLE_LABEL = "Recipe creator";
    public static final String DONE_LABEL = "Done";
    private RecipeCreatorState state = new RecipeCreatorState();
    public void setState(RecipeCreatorState state) {
        this.state = state;
        firePropertyChanged();
    }
    public RecipeCreatorViewModel() {
        super(TITLE_LABEL);
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RecipeCreatorState getState() {
        return state;
    }
}

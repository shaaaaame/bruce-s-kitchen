package interface_adapter.homepage;
import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
public class HomePageViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Bruce's Kitchen";
    public static final String SEARCH_LABEL = "Search recipes";
    private HomePageState state = new HomePageState();

    public void setState(HomePageState state) {
        this.state = state;
        firePropertyChanged();  // notifies when the state changes
    }

    public HomePageViewModel() {
            super(TITLE_LABEL);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public HomePageState getState() {
        return state;
    }

}

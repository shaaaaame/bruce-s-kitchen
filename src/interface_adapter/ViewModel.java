package interface_adapter;

import java.beans.PropertyChangeListener;

public class ViewModel {
    private String viewName;
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }

    public void firePropertyChanged() {
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }
}

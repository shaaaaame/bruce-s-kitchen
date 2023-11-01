package interface_adapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    private String activeViewName;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getViewName() {
        return activeViewName;
    }

    public void setViewName(String meow) {
        this.activeViewName = meow;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener meow) {
        support.addPropertyChangeListener(meow);
    }
}

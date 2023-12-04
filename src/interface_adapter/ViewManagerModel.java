package interface_adapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewManagerModel {
    private String activeViewName;
    //    TODO change this
    private boolean isLoggedIn = true;
    private String curentUser;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public String getActiveView() {
        return activeViewName;
    }

    public String getCurentUser(){ return this.curentUser; }
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }
    public void setLoggedIn(boolean loggedIn) { this.isLoggedIn = loggedIn; }
    public void setCurentUser(String username) { this.curentUser = username; }

    public boolean isLoggedIn() { return this.isLoggedIn; }
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}

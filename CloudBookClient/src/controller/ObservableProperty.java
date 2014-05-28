package controller;

import java.util.Observer;

/**
 * Observer
 * A Javafx property which is also observable by observers.
 * @author Gwendal
 */
public interface ObservableProperty {
    
    /**
     * Adds an observer to this observable property.
     * @param obs observer to be added.
     */
    void addObserver(Observer obs);
}

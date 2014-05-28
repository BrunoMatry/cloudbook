package controller;

import java.util.Observer;

/**
 * Observer
 * A Javafx property which is also observable by observers.
 */
public interface ObservableProperty {
    
    /**
     * Adds an observer to this observable property.
     * @param obs observer to be added.
     */
    void addObserver(Observer obs);
}

package controller;

import java.util.Observable;

/**
 * An observable which change state can be set from outside the class
 */
public class OpenObservable extends Observable {

    /**
     * Constructor
     */
    public OpenObservable() {
        super();
    }
    
    /**
     * Sets the state as "changed"
     */
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
     
}

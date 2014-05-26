/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.node;

import controller.ObservableProperty;
import controller.OpenObservable;
import java.util.Observer;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Observable property
 * @author Gwendal
 */
public class CloudProperty extends SimpleObjectProperty<Cloud> implements ObservableProperty {

    private final OpenObservable o;

    public CloudProperty() {
        super();
        o = new OpenObservable();
    }

    public CloudProperty(Cloud t) {
        super(t);
        o = new OpenObservable();
    }
    
    @Override
    public void addObserver(Observer obs) {
        o.addObserver(obs);
        o.setChanged();
        o.notifyObservers(get());
    }

    @Override
    public void set(Cloud t) {
        super.set(t);
        o.setChanged();
        o.notifyObservers(t);
    }
    
}

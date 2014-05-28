package model.node;

import controller.ObservableProperty;
import controller.OpenObservable;
import java.util.Observer;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Observable property
 */
public class CloudProperty extends SimpleObjectProperty<Cloud> implements ObservableProperty {

    private final OpenObservable o;

    /**
     * CloudProperty Constructor
     * Instanciate an empty CloudProperty
     */
    public CloudProperty() {
        super();
        o = new OpenObservable();
    }

    /**
     * CloudProperty Constructor
     * Instanciate a CloudProperty binding a Cloud enum
     * @param c the Cloud to bind
     */
    public CloudProperty(Cloud c) {
        super(c);
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
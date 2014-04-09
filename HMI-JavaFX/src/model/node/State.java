package model.node;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class State implements Information {
    
    /* Attributs serialisables */
    protected Cloud _cloud;
    protected Date _from;
    protected Date _to;
    protected boolean _current;
    
    /* Proprietes non serialisables */
    protected transient IntegerProperty cloud;
    protected transient ObjectProperty<Date> from;
    protected transient ObjectProperty<Date> to;
    protected transient BooleanProperty current;
    
    /**
     * Empty constructor for serialization
     */
    protected State() {
        cloud = new SimpleIntegerProperty(_cloud.ordinal());
        from = new SimpleObjectProperty<>(_from);
        to = new SimpleObjectProperty<>(_to);
        current = new SimpleBooleanProperty(_current);
    }
    
    /**
     * Main constructor
     * @param c cloud associate to the node state
     */
    public State(Cloud c) {
        cloud = new SimpleIntegerProperty(c.ordinal());
        from = new SimpleObjectProperty<>(new Date());
        to = new SimpleObjectProperty<>(new Date());
        current = new SimpleBooleanProperty(true);
    }
    
    /* Properties' getters */
    public IntegerProperty cloudProperty() { return cloud; }
    public ObjectProperty<Date> fromProperty() { return from; }
    public ObjectProperty<Date> toProperty() { return to; }
    public BooleanProperty currentProperty() { return current; }
    
    /**
     * Method
     * @return the Enum Cloud associate to the cloud property
     */
    public Cloud getCloud() {
        return Cloud.values()[cloud.get()];
    }

    /**
     * Modify the state of the instance
     * Set the currente property to false
     */
    public void notCurrentAnymore() {
        current.set(false);
        to.set(new Date());
    }
    
    @Override
    public void saveProperties() {
        _current = current.get();
        _cloud = getCloud();
        _from = from.get();
        _to = to.get();
    }

    @Override
    public void restoreProperties() {
        /* TODO Verifier la necessite de cette methode ou / et du constructeur vide */
        cloud = new SimpleIntegerProperty(_cloud.ordinal());
        from = new SimpleObjectProperty<>(_from);
        to = new SimpleObjectProperty<>(_to);
        current = new SimpleBooleanProperty(_current);
    }
}

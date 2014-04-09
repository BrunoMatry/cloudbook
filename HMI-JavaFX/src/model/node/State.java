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
    
    public IntegerProperty cloudProperty() { return cloud; }
    public ObjectProperty<Date> fromProperty() { return from; }
    public ObjectProperty<Date> toProperty() { return to; }
    public BooleanProperty currentProperty() { return current; }
    
    protected State() {
        cloud = new SimpleIntegerProperty(_cloud.ordinal());
        from = new SimpleObjectProperty<>(_from);
        to = new SimpleObjectProperty<>(_to);
        current = new SimpleBooleanProperty(_current);
    }
    
    public State(Cloud c) {
        cloud = new SimpleIntegerProperty(c.ordinal());
        from = new SimpleObjectProperty<>(new Date());
        to = new SimpleObjectProperty<>(new Date());
        current = new SimpleBooleanProperty(true);
    }
    
    public void notCurrentAnymore() {
        current.set(false);
        to.set(new Date());
    }
    
    public Cloud getCloud() {
        /* TODO verifier la validite de ce code */
        return Cloud.values()[cloud.get()];
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

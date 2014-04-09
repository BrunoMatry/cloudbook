package model.node;

import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
        _to = new Date();
        to = new SimpleObjectProperty<>(_to);
    }
    
    public Cloud getCloud() {
        /* TODO verifier la validite de ce code */
        return Cloud.values()[cloud.get()];
    }

    @Override
    public void saveProperties() {
        _current = current.get();
        _cloud = getCloud();
        // les attributs _from et _to sont necessairement a jour avec cette implementation
    }

    @Override
    public void restoreProperties() {
        /* TODO Verifier la necessite de cette methode ou / et du constructeur vide */
        cloud = new SimpleIntegerProperty(_cloud.ordinal());
        from = new SimpleStringProperty(_from.toString());
        to = new SimpleStringProperty(_to.toString());
        current = new SimpleBooleanProperty(_current);
    }
}

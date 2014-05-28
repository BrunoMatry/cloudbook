package model.node;

import model.network.interfaces.Information;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

public class State implements Information {
    
    /* Attributs serialisables */
    protected Cloud _cloud;
    protected Date _from;
    protected Date _to;
    protected boolean _current;
    
    /* Proprietes non serialisables */
    protected transient ObjectProperty<Cloud> cloud;
    protected transient ObjectProperty<Date> from;
    protected transient ObjectProperty<Date> to;
    protected transient BooleanProperty current;
    
    /**
     * Main constructor
     * @param c cloud associate to the node state
     */
    public State(Cloud c) {
        cloud = new SimpleObjectProperty<>(c);
        from = new SimpleObjectProperty<>(new Date());
        to = new SimpleObjectProperty<>(new Date());
        current = new SimpleBooleanProperty(true);
    }
    
    /* Properties' getters */
    public ObjectProperty<Cloud> cloudProperty() { return cloud; }
    public ObjectProperty<Date> fromProperty() { return from; }
    public ObjectProperty<Date> toProperty() { return to; }
    public BooleanProperty currentProperty() { return current; }
    
    /**
     * Method
     * @return the Enum Cloud associate to the cloud property
     */
    public Cloud getCloud() {
        return cloud.get();
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
        cloud = new SimpleObjectProperty<>(_cloud);
        from = new SimpleObjectProperty<>(_from);
        to = new SimpleObjectProperty<>(_to);
        current = new SimpleBooleanProperty(_current);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.cloud);
        hash = 53 * hash + Objects.hashCode(this.from);
        hash = 53 * hash + Objects.hashCode(this.to);
        hash = 53 * hash + Objects.hashCode(this.current);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        Date myFrom = (Date) from.get();
        Date myTo = (Date) to.get();
        Date otherFrom = (Date) other.from.get();
        Date otherTo= (Date) other.to.get();
        return cloud.get() == other.cloud.get()
                && myFrom.equals(otherFrom)
                && myTo.equals(otherTo)
                && current.get() == other.current.get();
    }

    @Override
    public String toString() {
        return "State{" + "cloud=" + cloud.get() + ", from=" + from.get() + ", to=" + to.get() + ", current=" + current.get() + '}';
    }   
}
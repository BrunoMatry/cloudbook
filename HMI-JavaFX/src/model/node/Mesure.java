package model.node;

import model.network.interfaces.Information;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Mesure implements Information {
    
    /* Attributs serialisables */
    protected Date _date;
    protected int _mesure1;
    protected int _mesure2;
    protected int _mesure3;
    
    /* Proprietes non serialisables */
    protected transient StringProperty date;
    protected transient IntegerProperty mesure1; 
    protected transient IntegerProperty mesure2;
    protected transient IntegerProperty mesure3;
    
    public Mesure(int mes1, int mes2, int mes3) {
        mesure1 = new SimpleIntegerProperty(mes1);
        mesure2 = new SimpleIntegerProperty(mes2);
        mesure3 = new SimpleIntegerProperty(mes3);
        date = new SimpleStringProperty();
        actualizeDate();
    }
    
    private void actualizeDate() {
        _date = new Date();
        date.set(_date.toString());
    }

    @Override
    public void saveProperties() {
        _mesure1 = mesure1.get();
        _mesure2 = mesure2.get();
        _mesure3 = mesure3.get();
        // l'attribut _date est necessairement a jour avec cette implementation
    }

    @Override
    public void restoreProperties() {
        mesure1 = new SimpleIntegerProperty(_mesure1);
        mesure2 = new SimpleIntegerProperty(_mesure2);
        mesure3 = new SimpleIntegerProperty(_mesure3);
        date = new SimpleStringProperty(_date.toString());
    }
    
    public IntegerProperty mesure1Property() { return mesure1; }
    public IntegerProperty mesure2Property() { return mesure2; }
    public IntegerProperty mesure3Property() { return mesure3; }
    public StringProperty dateProperty() { return date; }

    /**
     * hashCode
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this._date);
        hash = 47 * hash + Objects.hashCode(this.mesure1);
        hash = 47 * hash + Objects.hashCode(this.mesure2);
        hash = 47 * hash + Objects.hashCode(this.mesure3);
        return hash;
    }

    /**
     * equals
     * @param obj object to be compared with
     * @return true if the two objects have equals fields
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Mesure other = (Mesure) obj;
        return _date.equals(other._date)
                && mesure1.equals(other.mesure1)
                && mesure2.equals(other.mesure2)
                && mesure3.equals(other.mesure3);
    }
}

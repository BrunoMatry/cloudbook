package modele.node;

import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.friendmanager.Information;

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
    
    /**
     * Contructeur
     * Initialise les proprietes
     */
    protected Mesure() {
        date = new SimpleStringProperty(_date.toString());
        mesure1 = new SimpleIntegerProperty(_mesure1);
        mesure2 = new SimpleIntegerProperty(_mesure2);
        mesure3 = new SimpleIntegerProperty(_mesure3);
    }
    
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
        /* TODO Verifier la necessite de cette methode ou / et du constructeur vide */
        mesure1 = new SimpleIntegerProperty(_mesure1);
        mesure2 = new SimpleIntegerProperty(_mesure2);
        mesure3 = new SimpleIntegerProperty(_mesure3);
        date = new SimpleStringProperty(_date.toString());
    }
    
    public IntegerProperty mesure1Property() { return mesure1; }
    public IntegerProperty mesure2Property() { return mesure2; }
    public IntegerProperty mesure3Property() { return mesure3; }
    public StringProperty dateProperty() { return date; }
}

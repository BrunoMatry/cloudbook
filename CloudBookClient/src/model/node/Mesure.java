package model.node;

import model.network.interfaces.Information;
import java.util.Date;
import java.util.Objects;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public final class Mesure implements Information {
    
    /* Attributs serialisables */
    protected String _applicationName;
    protected Cloud _cloud;
    protected Date _date;
    protected int _mesure1;
    protected int _mesure2;
    protected int _mesure3;
    
    /* Proprietes non serialisables */
    protected transient StringProperty applicationName;
    protected transient ObjectProperty<Cloud> cloud;
    protected transient StringProperty date;
    protected transient IntegerProperty mesure1; 
    protected transient IntegerProperty mesure2;
    protected transient IntegerProperty mesure3;
    
    /**
     * Constructor
     */
    public Mesure() {
        applicationName = new SimpleStringProperty();
        cloud = new SimpleObjectProperty<>();
        date = new SimpleStringProperty();
        mesure1 = new SimpleIntegerProperty(0);
        mesure2 = new SimpleIntegerProperty(0);
        mesure3 = new SimpleIntegerProperty(0);
    }
    
    /**
     * Constructor
     * @param application producer of the mesure
     * @param mes1 mesure1 property value
     * @param mes2 mesure2 property value
     * @param mes3 mesure3 property value
     */
    public Mesure(MyNode application, int mes1, int mes2, int mes3) {
        if(application != null) {
            String name = application.nameProperty().get();
            applicationName = new SimpleStringProperty(name);
            Cloud c = application.platformProperty().get();
            cloud = new SimpleObjectProperty<>(c);
        } else {
            applicationName = new SimpleStringProperty("void");
            cloud = new SimpleObjectProperty<>(Cloud.DEFAULT);
        }
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
        _applicationName = applicationName.get();
        _mesure1 = mesure1.get();
        _mesure2 = mesure2.get();
        _mesure3 = mesure3.get();
        // l'attribut _date est necessairement a jour avec cette implementation
    }

    @Override
    public void restoreProperties() {
        applicationName = new SimpleStringProperty(_applicationName);
        mesure1 = new SimpleIntegerProperty(_mesure1);
        mesure2 = new SimpleIntegerProperty(_mesure2);
        mesure3 = new SimpleIntegerProperty(_mesure3);
        date = new SimpleStringProperty(_date.toString());
        cloud = new SimpleObjectProperty<>(_cloud);
    }
    
    public IntegerProperty mesure1Property() { return mesure1; }
    public IntegerProperty mesure2Property() { return mesure2; }
    public IntegerProperty mesure3Property() { return mesure3; }
    public StringProperty dateProperty() { return date; }
    public StringProperty applicationNameProperty() { return applicationName; }
    public ObjectProperty<Cloud> cloudProperty() { return cloud; }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.date);
        hash = 47 * hash + Objects.hashCode(this.mesure1);
        hash = 47 * hash + Objects.hashCode(this.mesure2);
        hash = 47 * hash + Objects.hashCode(this.mesure3);
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
        Mesure other = (Mesure) obj;
        return this.date.get().equals(other.date.get())
                && mesure1.get() == other.mesure1.get()
                && mesure2.get() == other.mesure2.get()
                && mesure3.get() == other.mesure3.get();
    }

    @Override
    public String toString() {
        return "Mesure{" + "mesure1=" + mesure1.get() + ", mesure2=" + mesure2.get() + ", mesure3=" + mesure3.get() + '}';
    }
    
}

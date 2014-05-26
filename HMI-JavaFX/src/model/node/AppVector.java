package model.node;

import java.util.ArrayList;
import java.util.Objects;
import model.network.interfaces.Information;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AppVector implements Information {
    
    private final static int nbMesuresForCalcul = 20;
    
    /* Attributs serialisables */
    protected int _appType;
    protected int _performance;
    protected int _speed;
    protected InformationBox<Mesure> mesures;
    
    /* Proprietes non serialisables */
    protected transient IntegerProperty appType; 
    protected transient IntegerProperty performance;
    protected transient IntegerProperty speed;

    /**
     * Constructor
     * The type, performance and speed are generated randomly in this implementation
     * @param mes mesures on which should be calculated the vector
     */
    public AppVector(InformationBox<Mesure> mes) {     
        mesures = mes;
        appType = new SimpleIntegerProperty(0);
        performance = new SimpleIntegerProperty(0);
        speed = new SimpleIntegerProperty(0);
        recalculateVector();
    }
    
    /**
     * equals
     * @param o object to be compared with
     * @return true if the two objects are equivalents
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppVector v = (AppVector) o;
 
        return appType.get() == v.appType.get()
                && performance.get() == v.performance.get()
                && speed.get() == v.speed.get();
    }
    
    public AppVector copy(){
        return new AppVector(mesures);
    }
    
    public IntegerProperty appTypeProperty() { return appType; }
    public IntegerProperty performanceProperty() { return performance; }
    public IntegerProperty speedProperty() { return speed; }

    @Override
    public void saveProperties() {
        _appType = appType.get();
        _performance = performance.get();
        _speed = speed.get();
    }

    @Override
    public void restoreProperties() {
        appType = new SimpleIntegerProperty(_appType);
        performance = new SimpleIntegerProperty(_performance);
        speed = new SimpleIntegerProperty(_speed);
    }

    /**
     * hashCode
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.appType);
        hash = 19 * hash + Objects.hashCode(this.performance);
        hash = 19 * hash + Objects.hashCode(this.speed);
        return hash;
    }

    /**
     * toString
     * @return string representation
     */
    @Override
    public String toString() {
        return "AppVector{" + "appType=" + appType.get() + ", performance=" + performance.get() + ", speed=" + speed.get() + '}';
    }

    public void recalculateVector() {
        ArrayList<Mesure> lastMesures = mesures.getLastValues(nbMesuresForCalcul);
        int totalM1 = 0;
        int totalM2 = 0;
        int totalM3 = 0;
        for(Mesure m : lastMesures) {
            totalM1 += m.mesure1Property().get();
            totalM2 += m.mesure2Property().get();
            totalM3 += m.mesure3Property().get();
        }
        if(lastMesures == null || lastMesures.isEmpty()) {
            appType.set((totalM1+totalM2+totalM3));
            performance.set(totalM2);
            speed.set(totalM3);
        } else {
            appType.set((totalM1+totalM2+totalM3)/lastMesures.size());
            performance.set(totalM2/lastMesures.size());
            speed.set(totalM3/lastMesures.size());
        }
    }
}
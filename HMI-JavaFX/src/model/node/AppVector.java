package model.node;

import model.network.interfaces.Information;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AppVector implements Information {
    
    /* Attributs serialisables */
    protected int _appType;
    protected int _performance;
    protected int _speed;
    
    /* Proprietes non serialisables */
    protected transient IntegerProperty appType; 
    protected transient IntegerProperty performance;
    protected transient IntegerProperty speed;
    
    protected AppVector() {
        appType = new SimpleIntegerProperty(_appType);
        performance = new SimpleIntegerProperty(_performance);
        speed = new SimpleIntegerProperty(_speed);
    }
    
    public AppVector(int appTyp, int perf, int spd) {
        appType = new SimpleIntegerProperty(appTyp);
        performance = new SimpleIntegerProperty(perf);
        speed = new SimpleIntegerProperty(spd);
    }
            
    public AppVector copy(){
        return new AppVector(this.appType.get(), this.performance.get(), this.speed.get());
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
        /* TODO Verifier la necessite de cette methode ou / et du constructeur vide */
        appType = new SimpleIntegerProperty(_appType);
        performance = new SimpleIntegerProperty(_performance);
        speed = new SimpleIntegerProperty(_speed);
    }
}
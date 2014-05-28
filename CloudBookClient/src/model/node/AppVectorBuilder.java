package model.node;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Builder.
 * Builds a features vector.
 
 */
public class AppVectorBuilder {
    
    private final StringProperty appType;
    /**
     * Application type.
     * @return application type.
     */
    public StringProperty appTypeProperty() {
        return appType;
    }
    
    private final StringProperty perf;
    /**
     * Application performance need.
     * @return Application performance need.
     */
    public StringProperty perfProperty() {
        return perf;
    }
    
    private final StringProperty speed;
    /**
     * Application speed need.
     * @return Application speed need.
     */
    public StringProperty speedProperty() {
        return speed;
    }
    
    /**
     * Constructor.
     */
    public AppVectorBuilder() {
        appType = new SimpleStringProperty();
        perf = new SimpleStringProperty();
        speed = new SimpleStringProperty();
    }
    
    /**
     * Builds a features vector with the properties values of the current instance.
     * @return a vector with the properties values of the current instance.
     */
    public AppVector build() {
        int resType = Integer.parseInt(appType.get());
        int resPerf = Integer.parseInt(perf.get());
        int resSpeed = Integer.parseInt(speed.get());
        AppVector res = new AppVector(resType, resPerf, resSpeed);
        return res;
    }
}
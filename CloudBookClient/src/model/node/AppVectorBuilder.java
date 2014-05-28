package model.node;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AppVectorBuilder {
    
    private final StringProperty appType;
    public StringProperty appTypeProperty() {
        return appType;
    }
    
    private final StringProperty perf;
    public StringProperty perfProperty() {
        return perf;
    }
    
    private final StringProperty speed;
    public StringProperty speedProperty() {
        return speed;
    }
    
    public AppVectorBuilder() {
        appType = new SimpleStringProperty();
        perf = new SimpleStringProperty();
        speed = new SimpleStringProperty();
    }
    
    public AppVector build() {
        int resType = Integer.parseInt(appType.get());
        int resPerf = Integer.parseInt(perf.get());
        int resSpeed = Integer.parseInt(speed.get());
        AppVector res = new AppVector(resType, resPerf, resSpeed);
        return res;
    }
}
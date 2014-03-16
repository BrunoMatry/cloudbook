/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Bruno
 */
public class AppVector {
    
    private final IntegerProperty appType;
    private final IntegerProperty performance;
    private final IntegerProperty speed;
    
    public AppVector(int appType, int performance, int speed) {
        this.appType = new SimpleIntegerProperty(appType);
        this.performance = new SimpleIntegerProperty(performance);
        this.speed = new SimpleIntegerProperty(speed);
    }
            
    public AppVector copy(){
        return new AppVector(this.appType.get(), this.performance.get(), this.speed.get());
    }
    
    public IntegerProperty getAppType() { return appType; }
    public IntegerProperty getPerformance() { return performance; }
    public IntegerProperty getSpeed() { return speed; }
    
    public void setAppType(int appType) { this.appType.set(appType); }
    public void setPerformance(int performance) { this.performance.set(performance); }
    public void setSpeed(int speed) { this.speed.set(speed); }
}

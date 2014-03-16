/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

/**
 *
 * @author Bruno
 */
public class AppVector {
    
    private int appType;
    private int performance;
    private int speed;
    
    public AppVector(int appType, int performance, int speed) {
        this.appType = appType;
        this.performance = performance;
        this.speed = speed;
    }
            
    public AppVector copy(){
        return new AppVector(this.appType, this.performance, this.speed);
    }
    
    public int getAppType() { return appType; }
    public int getPerformance() { return performance; }
    public int getSpeed() { return speed; }
    
    public void setAppType(int appType) { this.appType = appType; }
    public void setPerformance(int performance) { this.performance = performance; }
    public void setSpeed(int speed) { this.speed = speed; }
}

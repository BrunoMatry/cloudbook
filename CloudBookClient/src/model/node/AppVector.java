package model.node;

import java.util.Objects;
import model.network.interfaces.Information;

public class AppVector implements Information {
    
    protected int appType;
    protected int perfNeed;
    protected int spdNeed;
    

    /**
     * Constructor
     * The type, performance and speed are generated randomly in this implementation
     * @param type the type of the application
     * @param perf the performance need of the application
     * @param spd the speed need of the application
     */
    public AppVector(int type, int perf, int spd) {     
        appType = type;
        perfNeed = perf;
        spdNeed = spd;
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
 
        return appType == v.appType
                && perfNeed == v.perfNeed
                && spdNeed == v.spdNeed;
    }
    
    public AppVector copy(){
        return new AppVector(appType, perfNeed, spdNeed);
    }
    
    public int getAppType() { return appType; }
    public int getPerformanceNeed() { return perfNeed; }
    public int getSpeedNeed() { return spdNeed; }
    
    public void setAppType(int at) { appType = at; }
    public void setPerformanceNeed(int p) { perfNeed = p; }
    public void setSpeedNeed(int s) { spdNeed = s; }

    @Override
    public void saveProperties() {}

    @Override
    public void restoreProperties() {}

    /**
     * hashCode
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.appType);
        hash = 19 * hash + Objects.hashCode(this.perfNeed);
        hash = 19 * hash + Objects.hashCode(this.spdNeed);
        return hash;
    }

    /**
     * toString
     * @return string representation
     */
    @Override
    public String toString() {
        return "AppVector{" + "appType=" + appType + ", performance=" + perfNeed + ", speed=" + spdNeed + '}';
    }
}
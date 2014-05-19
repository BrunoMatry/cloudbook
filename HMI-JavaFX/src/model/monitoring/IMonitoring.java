package model.monitoring;

public interface IMonitoring {
    /**
     * Update the current node with measurements of the block measures
     */
    void pushInformation(); 
}

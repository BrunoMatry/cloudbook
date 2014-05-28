package model.monitoring;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.engine.Engine;
import model.node.Mesure;
import model.node.MyNode;

public class Monitoring implements IMonitoring {
    
    protected List<Mesure> mesures;
    protected MyNode node;
    
    // monitoring thread
    private MonitoringThread thread;
    
    // logs containing information on sent objects
    protected StringProperty logs;
    // getter
    public final StringProperty logsProperty() { return logs; }
    
    /**
     * Monitoring constructor
     * @param n the current node
     */
    public Monitoring(MyNode n) {
        mesures = new ArrayList<>();
        logs = new SimpleStringProperty("===== LOGS =====\n\n");
        node = n;
    }

    /**
     * Pushes information on the current node
     */
    @Override
    public synchronized void pushInformation() {
        node.addMesures(mesures);
        mesures.clear(); 
    }

    /**
     * Generates a random mesure
     * @return Random generated mesure
     */
    protected Mesure genererMesure() {
        return new Mesure(node,
                            getRandomInteger(0, 100),
                            getRandomInteger(0, 100), 
                            getRandomInteger(0, 100));
    }
    
    protected int getRandomInteger(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }
    
    /**
     * Starts the monitoring thread
     * If the thread is running, it stops it befor creating and starting a new one
     * @throws InterruptedException problem when stopping the thread
     */
    public void start() throws InterruptedException {
        if(thread != null)
            stop();
        thread = new MonitoringThread(this);
        thread.start();
    }
    
    /**
     * Stops the monitoring thread
     * @throws InterruptedException problem when waiting the thread ending
     */
    public void stop() throws InterruptedException {
        thread.setStopFlag(true);
    }
    
}
package model.monitoring;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.engine.Engine;
import model.node.Mesure;

public class Monitoring extends Thread implements IMonitoring {
    
    private final static long TIME = 5000;
    
    protected List<Mesure> mesures;
    protected Engine engine;
    protected boolean stopFlag;
    
    //logs containing information on sent objects
    protected StringProperty logs;
    public final StringProperty logsProperty() {
        return logs;
    }
    
    public Monitoring(Engine engine) {
        this.mesures = new ArrayList<>();
        this.logs = new SimpleStringProperty("===== LOGS =====\n\n");
        this.engine = engine;
    }

    /**
     * Pushes information on the current node
     */
    @Override
    public synchronized void pushInformation() {
        engine.getNode().addMesures(mesures);
        mesures.clear(); 
    }
    
    /**
     * Each 5s, generates new information.
     * When there is enough information, it is sent on the network.
     */
    @Override
    public void run() {
        while(!stopFlag) {
            this.mesures.add(genererMesure());
            try {
                sleep(TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Generates a random mesure
     * @return Random generated mesure
     */
    protected Mesure genererMesure() {
        return new Mesure(engine.getNode(),
                getRandomInteger(0, 100),
                            getRandomInteger(0, 100), 
                            getRandomInteger(0, 100));
    }
    
    protected int getRandomInteger(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }
    
    /**
     * Getter
     * @return stopFlag field
     */
    public final boolean isStopFlag() {
        return stopFlag;
    }

    /**
     * Setter
     * @param stopFlag stopFlag field
     */
    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }
}
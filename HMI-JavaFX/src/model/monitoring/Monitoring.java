package model.monitoring;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.node.Mesure;

/**
 *
 * @author Bruno
 */
public class Monitoring extends Thread implements IMonitoring {
    
    protected List<Mesure> mesures;
    
    //logs containing information on sent objects
    protected StringProperty logs;
    public final StringProperty logsProperty() {
        return logs;
    }
    
    private final static long TIME = 5000;
    
    public Monitoring() {
        this.mesures = new ArrayList<>();
        this.logs = new SimpleStringProperty("===== LOGS =====");
    }

    @Override
    public synchronized void pushInformation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void run() {
        this.mesures.add(genererMesure());
        try {
            sleep(TIME);
        } catch (InterruptedException ex) {
            Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected Mesure genererMesure() {
        return new Mesure(  getRandomInteger(0, 100), 
                            getRandomInteger(0, 100), 
                            getRandomInteger(0, 100));
    }
    
    protected int getRandomInteger(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }
}

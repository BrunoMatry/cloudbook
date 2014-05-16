package model.monitoring;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.network.interfaces.Information;
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
        this.logs = new SimpleStringProperty("===== LOGS =====\n\n");
    }

    /**
     * Sends generated information on the network.
     * Completes the logs.
     */
    @Override
    public synchronized void pushInformation() {
        String content = logs.get();
        content += "I sent " + this.mesures.size() + " informations to my friends :\n";
        for(Information info : this.mesures) {
            content += info + "\n"; 
        }
        content += '\n';
        this.mesures.clear();
        logs.set(content);
    }
    
    /**
     * Each 5s, generates new information.
     * When there is enough information, it is sent on the network.
     */
    @Override
    public void run() {
        while(true) {
            this.mesures.add(genererMesure());
            if(this.mesures.size() == 3) {
                pushInformation();
            }
            try {
                sleep(TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
            }
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

package model.monitoring;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.engine.Engine;
import model.network.interfaces.RemoteClient;
import model.node.Mesure;

public class Monitoring extends Thread implements IMonitoring {
    
    protected List<Mesure> mesures;
    
    protected Engine engine;
    
    protected boolean stopFlag;
    
    //logs containing information on sent objects
    protected StringProperty logs;
    public final StringProperty logsProperty() {
        return logs;
    }
    
    private final static long TIME = 5000;
    
    public Monitoring(Engine engine) {
        this.mesures = new ArrayList<>();
        this.logs = new SimpleStringProperty("===== LOGS =====\n\n");
        this.engine = engine;
    }

    /**
     * Sends generated information on the network.
     * Completes the logs.
     * !!!!!!! This method does not do that : it push information on the current node !
     */
    @Override
    public synchronized void pushInformation() {
        // JE CROYAIS QUE LE MONITORING NE COMMUNIQUAIT PAS DIRECTEMENT AVEC LE NETWORK !!!!
        
        
        
        /*
        String content = logs.get();
        RemoteClient network = engine.getNetwork();
        content += "I sent " + this.mesures.size() + " informations to my friends :\n";
        for(Information info : this.mesures) {
            content += info + "\n";
        }
        content += '\n';
        this.mesures.clear();
        logs.set(content);
        */
    }
    
    /**
     * Each 5s, generates new information.
     * When there is enough information, it is sent on the network.
     */
    @Override
    public void run() {
        while(!stopFlag) {
            try {
                this.mesures.add(genererMesure());
                RemoteClient network = engine.getNetwork();
                if(this.mesures.size() >= 3 && network.getStub() != null) {
                    // CE N'EST PAS A CETTE METHODE DE PRENDRE SES DECISIONS TOUTE SEULE !!!!!!
                    // pushInformation();
                }
                try {
                    sleep(TIME);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (RemoteException ex) {
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
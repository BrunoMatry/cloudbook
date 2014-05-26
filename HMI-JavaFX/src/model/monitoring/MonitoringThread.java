/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.monitoring;

import static java.lang.Thread.sleep;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.node.Mesure;

/**
 * 
 * @author Gwendal
 */
public class MonitoringThread extends Thread {
    
    private final static long TIME = 5000;
    
    private final List<Mesure> mesures;
    private boolean stopFlag;
    
    //monitoring instance responsible of the thread
    private final Monitoring owner;
    
    public MonitoringThread(Monitoring owner) {
        stopFlag = false;
        this.owner = owner;
        this.mesures = owner.mesures;
    }
    
    /**
     * Each TIME seconds, generates new information.
     * When there is enough information, it is sent on the network.
     */
    @Override
    public void run() {
        while(!stopFlag) {
            this.mesures.add(owner.genererMesure());
            try {
                sleep(TIME);
            } catch (InterruptedException ex) {
                Logger.getLogger(Monitoring.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

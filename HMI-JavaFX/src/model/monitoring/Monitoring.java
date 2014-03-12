/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.monitoring;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.node.Mesure;

/**
 *
 * @author Bruno
 */
public class Monitoring extends Thread implements IMonitoring {
    
    protected List<Mesure> mesures;
    private final static long TIME = 5000;
    
    public Monitoring() {
        this.mesures = new ArrayList<>();
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
                            getRandomInteger(0, 100),
                            getRandomInteger(0, 100),
                            getRandomInteger(0, 100));
    }
    
    protected int getRandomInteger(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }
}

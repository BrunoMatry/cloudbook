package thecloudbook.example;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import thecloudbook.implementation.Scheduler;
import thecloudbook.implementation.Servant;
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.IServant;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gwendal
 */
public class SchedulerExample extends Scheduler {

    /**
     * Constructor
     * constructs a scheduler based on a ServantExampleFactory
     */
    public SchedulerExample() {
        super(new ServantExampleFactory());
    }

    @Override
    public void dispatch(ISendCommand command) throws RemoteException {
        IServant s = sFactory.makeServant();
        command.call(s);
        try {
            ((Servant)s).join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SchedulerExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

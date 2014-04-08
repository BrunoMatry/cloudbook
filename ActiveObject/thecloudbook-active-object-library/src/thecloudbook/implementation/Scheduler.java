/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.implementation;

import java.rmi.RemoteException;
import thecloudbook.interfaces.IClientService;
import thecloudbook.interfaces.IScheduler;
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.IServant;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public class Scheduler implements IScheduler {
    
    protected ServantFactory sFactory;

    /**
     * Constructor
     * @param sf factory to be used when a new servant must be created
     */
    public Scheduler(ServantFactory sf) {
        sFactory = sf;
    }
    
    @Override
    public void dispatch(ISendCommand command) throws RemoteException {
        IServant s = sFactory.makeServant();
        command.call(s);
    }

    @Override
    public void onReceived(IClientService sender, int offset) throws RemoteException {
        ISendCommand command = sender.getSent(offset);
        if(command.guard())
            dispatch(command);
    }
    
}

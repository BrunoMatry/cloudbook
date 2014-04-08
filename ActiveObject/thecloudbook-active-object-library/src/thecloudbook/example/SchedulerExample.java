package thecloudbook.example;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import thecloudbook.implementation.ProxyClientService;
import thecloudbook.implementation.Scheduler;
import thecloudbook.implementation.Servant;
import thecloudbook.interfaces.IClientService;
import thecloudbook.interfaces.IScheduler;
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.ServantFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gwendal
 */
public class SchedulerExample implements IScheduler {

    protected Scheduler guarded;
    
    public SchedulerExample(Scheduler sched) throws RemoteException, AlreadyBoundException, MalformedURLException {
        guarded = sched;
    }

    @Override
    public void dispatch(ISendCommand command) throws RemoteException {
        IClientService s = guarded.getsFactory().makeServant();
        command.call(s);
        try {
            ((Servant)s).join();
        } catch (InterruptedException ex) {
            Logger.getLogger(SchedulerExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void onReceived(ProxyClientService sender, int offset) throws RemoteException {
        guarded.onReceived(sender, offset);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.implementation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thecloudbook.interfaces.IClientService;
import thecloudbook.interfaces.IScheduler;
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public class ProxyClientService extends UnicastRemoteObject implements IClientService {

    //distant scheduler, which must be called using RMI
    protected IScheduler scheduler;
    
    protected List<ISendCommand> sent;
    
    public ProxyClientService(String serverUrl) throws NotBoundException,
            MalformedURLException,
            RemoteException {
        scheduler = (IScheduler)Naming.lookup(serverUrl);
        sent = new ArrayList<>();
    }
    
    @Override
    public void send(Sendable sendable) throws RemoteException {
        ISendCommand command = new SendCommand(sendable);
        sent.add(command);
        scheduler.onReceived(this, sent.indexOf(command));
    }

    public ISendCommand getSent(int i) {
        return sent.get(i);
    }
    
}

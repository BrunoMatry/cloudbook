/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.implementation;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import thecloudbook.interfaces.IClientService;
import thecloudbook.interfaces.IScheduler;
import thecloudbook.interfaces.ISchedulerProxy;
import thecloudbook.interfaces.ISendCommand;

/**
 *
 * @author Gwendal
 * proxy
 */
public class SchedulerProxy extends UnicastRemoteObject implements ISchedulerProxy {

    //InetAddress
    protected String address;
    
    //Port
    protected int port;
    
    //name
    protected String name;
    
    //The scheduler which is guarded by this proxy
    protected IScheduler guarded;
    
    public SchedulerProxy(IScheduler sched, String address, int port, String name)
            throws RemoteException,
            AlreadyBoundException,
            MalformedURLException {
        super(port);
        guarded = sched;
        this.address = address;
        this.port = port;
        this.name = name;
        LocateRegistry.createRegistry(port);
    }
    
    @Override
    public void dispatch(ISendCommand command) throws RemoteException {
        guarded.dispatch(command);
    }
    
    public void setAddress(String a) {
        address = a;
    }
    
    public String getUrl() {
        return "rmi://" + address + ":" + port + "/" + name;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void onReceived(IClientService sender, int offset) throws RemoteException {
        guarded.onReceived(sender, offset);
    }
    
}

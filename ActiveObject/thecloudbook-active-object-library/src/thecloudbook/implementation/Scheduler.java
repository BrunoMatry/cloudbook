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
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public class Scheduler extends UnicastRemoteObject implements IScheduler {

    protected ServantFactory sFactory;
    
    //InetAddress
    protected String address;
    
    //Port
    protected int port;
    
    //name
    protected String name;
    
    public Scheduler(ServantFactory sf, String address, int port, String name)
            throws RemoteException,
            AlreadyBoundException,
            MalformedURLException {
        super(port);
        sFactory = sf;
        this.address = address;
        this.port = port;
        this.name = name;
        LocateRegistry.createRegistry(port);
    }
    
    @Override
    public void dispatch(ISendCommand command) throws RemoteException {
        IClientService s = sFactory.makeServant();
        command.call(s);
    }

    @Override
    public void onReceived(ISendCommand command) throws RemoteException {
        if(command.guard())
            dispatch(command);
    }
    
    public void setAddress(String a) {
        address = a;
    }
    
    public String getUrl() {
        return "rmi://" + address + ":" + port + "/" + name;
    }

    public ServantFactory getsFactory() {
        return sFactory;
    }

    public void setsFactory(ServantFactory sFactory) {
        this.sFactory = sFactory;
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
    
}

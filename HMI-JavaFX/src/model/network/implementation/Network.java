/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.engine.Engine;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.RemoteServer;
import model.request.Sendable;

/**
 * 
 * @author Gwendal
 * 
 * Network management root
 */
public class Network extends UnicastRemoteObject implements RemoteClient {
    
    private String ip;
    private RemoteServer stub;
    
    /**
     * Constructor
     * @param ip ip of 
     * @throws RemoteException required by RMI
     */
    public Network(String ip) throws RemoteException {
        this.ip = ip;
    }
    
    @Override
    public void handleRequest(Sendable request) throws RemoteException {
        Engine.INSTANCE.handleRequest(request);
    }
    
    @Override
    public void send(Sendable request, String receiver) throws RemoteException {
        request.getInfo().saveProperties();
        stub.send(request, receiver);
    }

    @Override
    public String getIp() throws RemoteException {
        return ip;
    }

    @Override
    public RemoteServer getStub() throws RemoteException {
        return stub;
    }

    @Override
    public void connect(String url) throws RemoteException {
        try {
            stub = (RemoteServer)Naming.lookup(url);
            stub.connect(this);
        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

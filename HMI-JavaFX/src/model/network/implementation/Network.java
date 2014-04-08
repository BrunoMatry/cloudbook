/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.engine.Engine;
import model.network.interfaces.RemoteOperations;
import model.request.Request;
import thecloudbook.interfaces.Sendable;

/**
 * 
 * @author Gwendal
 * 
 * Network management root
 */
public class Network extends UnicastRemoteObject implements RemoteOperations {
    
    /**
     * Constructor
     * @throws RemoteException required by RMI
     */
    public Network() throws RemoteException {
        
    }
    
    @Override
    public void handleRequest(Sendable request) throws RemoteException {
        Engine.INSTANCE.handleRequest((Request)request);
    }
    
    @Override
    public void send(Sendable request) throws RemoteException {
        //sender.send(request);
    }
}

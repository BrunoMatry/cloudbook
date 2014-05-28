/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Server reachable from outside.
 * @author Gwendal
 */
public interface RemoteServer extends Remote, RemoteOperations {
    
    //Name used in the rmi address.
    public static final String NAME = "Server";
    
    /**
     * Accepts the connection request of a client.
     * @param rc client to be accepted.
     * @throws RemoteException remote connection problem.
     */
    void connect(RemoteClient rc) throws RemoteException;
    
    /**
     * Accepts the disconnection request of a client.
     * @param rc client to be disconnected.
     * @throws RemoteException remote connection problem.
     */
    void disconnect(RemoteClient rc) throws RemoteException;
    
    /**
     * Gets a client using its identifier.
     * @param client client identifier.
     * @return the identified client.
     * @throws RemoteException remote connection problem.
     */
    RemoteClient getClient(String client) throws RemoteException;
    
    /**
     * Makes the server be visible on the network.
     * @throws RemoteException remote connection problem.
     */
    void binding() throws RemoteException;
    
    /**
     * Gets the url allowing to get a local delegate of the server.
     * @return the url allowing to get a local delegate of the server.
     * @throws RemoteException remote connection problem.
     */
    String getUrl() throws RemoteException;
}

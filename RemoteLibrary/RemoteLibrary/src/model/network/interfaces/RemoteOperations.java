/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Network operations shared by clients and servers.
 * @author Gwendal
 */
public interface RemoteOperations extends Remote {
    
    /**
     * Sends a request to a receiver.
     * @param request request to be sent.
     * @param receiver receiver of the request.
     * @throws RemoteException problem with remote connection.
     */
    public void send(Sendable request, String receiver) throws RemoteException;
    
    /**
     * Sends a request to all the clients on the network.
     * @param request request to be sent.
     * @throws RemoteException remote connection problem.
     */
    public void broadcast(Sendable request) throws RemoteException;
    
    /**
     * Gets the identifier of the client/server on the network.
     * @return the identifier of the client/server on the network.
     * @throws RemoteException remote connection problem.
     */
    public String getId() throws RemoteException;
}

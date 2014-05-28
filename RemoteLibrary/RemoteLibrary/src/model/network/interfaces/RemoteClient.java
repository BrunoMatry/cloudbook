package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A client reachable from outside.
 * @author Gwendal
 */
public interface RemoteClient extends Remote, RemoteOperations {
    
    /**
     * Gets the server.
     * @return the server.
     * @throws RemoteException remote connetion problem. 
     */
    RemoteServer getStub() throws RemoteException;
    
    /**
     * Connects the client to its stub (its server delegate).
     * @throws RemoteException remote connection problem.
     */
    void connect() throws RemoteException;
    
    /**
     * Disconnects the client from its stub (its server delegate).
     * @throws RemoteException remote connection problem.
     */
    void disconnect() throws RemoteException;
    
    /**
     * Called method to give a request to the client.
     * @param request request to deal with.
     * @throws RemoteException remote connection problem.
     */
    public void handleRequest(Sendable request) throws RemoteException;
}

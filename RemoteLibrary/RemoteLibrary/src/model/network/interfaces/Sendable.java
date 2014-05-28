package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * A communicable object.
 * @author Gwendal
 */
public interface Sendable extends Remote {
    
    public RemoteClient getClient() throws RemoteException;
    
    public void setClient(RemoteClient client) throws RemoteException;
    
    /**
     * Gets the author.
     * @return the author.
     * @throws RemoteException 
     */
    public Sender getSender() throws RemoteException;

    /**
     * Gets the generation date.
     * @return the generation date.
     * @throws RemoteException remote connection problem.
     */
    public Date getDate() throws RemoteException;

    /**
     * Gets the information stored in the request.
     * @return the information stored in the request.
     * @throws RemoteException 
     */
    public Information getInfo() throws RemoteException;

    /**
     * Gets the number of accepted rebounds necessary to carry the request through a distributed network.
     * @return the number of accepted rebounds necessary to carry the request through a distributed network.
     * @throws RemoteException remote connection problem.
     */
    public int getRebounds() throws RemoteException;

    /**
     * Gets the target of the request.
     * @return the recipent identifier.
     * @throws RemoteException remote connection problem.
     */
    public int getRecipent() throws RemoteException;
    
    /**
     * Gets the identifier of the request.
     * @return the identifier of the request.
     * @throws RemoteException remote connection problem.
     */
    public int getId() throws RemoteException;
}

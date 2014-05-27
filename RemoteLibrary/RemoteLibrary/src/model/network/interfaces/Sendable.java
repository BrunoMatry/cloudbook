package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface Sendable extends Remote {
    
    public RemoteClient getClient() throws RemoteException;
    
    public void setClient(RemoteClient client) throws RemoteException;
    
    public Sender getSender() throws RemoteException;

    public Date getDate() throws RemoteException;

    public Information getInfo() throws RemoteException;

    public int getRebounds() throws RemoteException;

    public int getRecipent() throws RemoteException;
    
    public int getId() throws RemoteException;
}

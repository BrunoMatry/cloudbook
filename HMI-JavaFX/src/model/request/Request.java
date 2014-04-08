package model.request;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import model.node.Information;

public class Request<Inf extends Information> extends UnicastRemoteObject implements Sendable {
    protected Sender sender;
    protected Date date;
    protected Inf info;
    protected int rebounds;
    protected int recipent;

    /**
     * Constructor
     * @param inf information to send
     * @throws java.rmi.RemoteException required for distant call
     */
    public Request(Inf inf) throws RemoteException {
        info = inf;
    }
    
    @Override
    public Sender getSender() {
        return sender;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Inf getInfo() {
        return info;
    }

    @Override
    public int getRebounds() {
        return rebounds;
    }

    @Override
    public int getRecipent() {
        return recipent;
    }

    @Override
    public int getId() throws RemoteException {
        return 0;
    }
    
    
}

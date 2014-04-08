package model.request;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import model.network.interfaces.factory.RequestHandler;
import model.node.Information;
import thecloudbook.interfaces.Sendable;

public class Request<Inf extends Information> extends UnicastRemoteObject implements Sendable {
    protected Sender sender;
    protected Date date;
    protected Inf info;
    protected int rebounds;
    protected RequestHandler recipent;

    /**
     * Constructor
     * @param inf information to send
     * @param sched scheduler of the recipient
     * @throws java.rmi.RemoteException required for distant call
     */
    public Request(Inf inf, RequestHandler sched) throws RemoteException {
        info = inf;
        recipent = sched;
    }
    
    public Sender getSender() {
        return sender;
    }

    public Date getDate() {
        return date;
    }

    public Inf getInfo() {
        return info;
    }

    public int getRebounds() {
        return rebounds;
    }

    public RequestHandler getRecipent() {
        return recipent;
    }
    
    
}

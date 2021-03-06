package model.request;

import java.net.InetAddress;
import java.net.UnknownHostException;
import model.network.interfaces.Sendable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import model.network.interfaces.Information;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.Sender;
import model.node.AppVector;
import model.node.Cloud;
import model.node.MyNode;
import model.node.friend.Member;

public class Request<Inf extends Information> extends UnicastRemoteObject implements Sendable {
    
    protected RemoteClient client;
    protected Sender sender;
    protected Date date;
    protected Inf info;
    protected int rebounds;
    protected int recipent;

    /**
     * Constructor
     * @throws RemoteException
     */
    public Request() throws RemoteException {
        sender = null;
        date = new Date();
        info = null;
        rebounds = 0;
        recipent = 0;
    }
    
    /**
     * Constructor
     * @param inf information to send
     * @param manager request manager which creates the request
     * @throws java.rmi.RemoteException required for distant call
     * @throws java.net.UnknownHostException
     */
    public Request(Inf inf, RequestManager manager) throws RemoteException, UnknownHostException {
        info = inf;
        date = new Date();
        MyNode node = manager.getEngine().getNode();
        AppVector senderVector = node.getVector();
        String id = node.nameProperty().get()
                + "@" 
                + InetAddress.getLocalHost().getHostAddress() + ":"
                + node.getNodePort();
        Cloud cloud = node.platformProperty().get();
        sender = new Member(id, senderVector, cloud);
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

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setInfo(Inf info) {
        this.info = info;
    }

    public void setRebounds(int rebounds) {
        if(rebounds >= 0)
            this.rebounds = rebounds;
        else
            this.rebounds = 0;
    }

    public void setRecipent(int recipent) {
        this.recipent = recipent;
    }

    @Override
    public RemoteClient getClient() throws RemoteException {
        return this.client;
    }

    /**
     * Setter
     * @param client client newtork
     */
    @Override
    public void setClient(RemoteClient client) {
        this.client = client;
    }
    
}

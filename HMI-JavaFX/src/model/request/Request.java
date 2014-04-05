package model.request;

import java.net.InetAddress;
import java.util.Date;
import model.friendmanager.Information;
import thecloudbook.interfaces.Sendable;

public class Request<Inf extends Information> implements Sendable {
    protected Sender sender;
    protected Date date;
    protected Inf info;
    protected int rebounds;
    protected InetAddress recipent;

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

    public InetAddress getRecipent() {
        return recipent;
    }
    
    
}

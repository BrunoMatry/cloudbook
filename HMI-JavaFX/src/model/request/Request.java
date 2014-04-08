package model.request;

import java.util.Date;
import model.network.interfaces.factory.RequestHandler;
import model.node.Information;
import thecloudbook.interfaces.Sendable;

public class Request<Inf extends Information> implements Sendable {
    protected Sender sender;
    protected Date date;
    protected Inf info;
    protected int rebounds;
    protected RequestHandler recipent;

    /**
     * Constructor
     * @param inf information to send
     * @param sched scheduler of the recipient
     */
    public Request(Inf inf, RequestHandler sched) {
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

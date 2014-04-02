/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.request;

import java.net.InetAddress;
import java.util.Date;
import model.friendmanager.Information;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Bruno
 */
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

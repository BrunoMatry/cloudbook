/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation;

import model.engine.Engine;
import model.network.implementation.clientServer.ClientServerFactory;
import model.network.interfaces.factory.NetworkFactory;
import model.network.interfaces.factory.RequestHandler;
import model.network.interfaces.factory.RequestSender;
import model.request.Request;

/**
 * 
 * @author Gwendal
 * 
 * Network management root
 */
public final class Network {
    
    protected NetworkFactory factory;
    protected RequestHandler receiver;
    protected RequestSender sender;
    
    /**
     * Default constructor
     * Change the first line in order to change the used implmentation
     */
    public Network() {
        factory = new ClientServerFactory(this);
        receiver = factory.makeRequestHandler();
        sender = factory.makeRequestSender();
    }
    
    public void handleRequest(Request request) {
        Engine.INSTANCE.handleRequest(request);
    }
    
    public void send(Request request) {
        sender.send(request);
    }
}

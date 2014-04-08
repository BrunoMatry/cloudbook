/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation.clientServer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import model.network.interfaces.factory.RequestHandler;
import thecloudbook.implementation.Scheduler;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public class ClientServerHandler extends Scheduler implements RequestHandler {

    /**
     * Constructor
     * @param sf factory to be used to instanciate servants
     */
    public ClientServerHandler(ServantFactory sf) {
        super(sf);
        //Naming.bind(getUrl(), this);
    }
 
}

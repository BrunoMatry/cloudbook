/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation.clientServer;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.interfaces.factory.NetworkFactory;
import model.network.interfaces.factory.RequestHandler;
import model.network.interfaces.factory.RequestSender;
import thecloudbook.example.ServantExampleFactory;

/**
 *
 * @author Gwendal
 */
public class ClientServerFactory implements NetworkFactory {

    //default port that must be used by all the clients
    public static final int PORT  = 8888;
    
    //default url name field that must be used by all the clients
    public static final String NAME = "ClientServerHandler";
    
    @Override
    public RequestHandler makeRequestHandler() {
        try {
            return new ClientServerHandler(new ClientServantFactory(),
                    InetAddress.getLocalHost().getHostAddress(),
                    PORT,
                    NAME);
        } catch (RemoteException | AlreadyBoundException | MalformedURLException | UnknownHostException ex) {
            Logger.getLogger(ClientServerFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public RequestSender makeRequestSender() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

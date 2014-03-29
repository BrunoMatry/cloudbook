/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation.clientServer;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import model.network.interfaces.factory.RequestSender;
import thecloudbook.implementation.ProxyClientService;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public class ClientServerSender extends ProxyClientService implements RequestSender {

    public ClientServerSender(String serverUrl)
            throws NotBoundException, MalformedURLException, RemoteException {
        super(serverUrl);
    }

    @Override
    public void send(Sendable sendable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import thecloudbook.implementation.ProxyClientService;

/**
 *
 * @author Gwendal
 * singleton
 */
public class ServerForwarder extends ProxyClientService {

    /**
     * Constructor
     * @param serverUrl url with which we must communicate
     * @throws NotBoundException the server at the server url can't be accessed
     * @throws MalformedURLException url is malformed
     * @throws RemoteException 
     */
    public ServerForwarder(String serverUrl) throws NotBoundException, MalformedURLException, RemoteException {
        super(serverUrl);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation;

import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.interfaces.RemoteOperations;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public final class Server extends UnicastRemoteObject implements RemoteOperations {

    public static final Server INSTANCE = makeServerScheduler();
    
    private Server() throws RemoteException {
        
    }
    
    private static Server makeServerScheduler() {
        try {        
            Server res = new Server();
            //Naming.bind(res.getUrl(), res);
            return res;
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void handleRequest(Sendable request) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void send(Sendable request) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

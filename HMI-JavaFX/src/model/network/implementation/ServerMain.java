/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gwendal
 * Main programm at the server side
 */
public class ServerMain {
    
    /**
     * Main programm
     * @param args [<address>] [<port>]
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        try {
            Server.INSTANCE.binding();
            System.out.println("Server launched");
            while(true);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

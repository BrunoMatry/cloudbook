/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudbookserver;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gwendal
 */
public class CloudBookServer {

    /**
     * Main programm
     * @param args <port>
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);
            Server.INSTANCE.setPort(port);
            Server.INSTANCE.binding();
            System.out.println("The server is running");
            System.out.println("RMI address : " + Server.INSTANCE.getUrl());
            while(true);
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

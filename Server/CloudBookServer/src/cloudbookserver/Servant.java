/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cloudbookserver;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.network.interfaces.RemoteClient;
import model.network.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public class Servant extends Thread {

    protected Sendable request;
    protected String receiver;
    
    public Servant(Sendable sendable, String target) {
        request = sendable;
        receiver = target;
    }
    
    @Override
    public void run() {
        try {
            RemoteClient client = Server.INSTANCE.getClient(receiver);
            if(client != null)
                client.handleRequest(request);
            else
                System.out.println("A client tries to send a request to a not registered client");
        } catch (RemoteException ex) {
            Logger.getLogger(Servant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

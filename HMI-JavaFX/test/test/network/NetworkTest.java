/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import model.engine.Engine;
import model.network.implementation.Network;
import model.network.implementation.Server;
import model.node.Message;
import model.request.Request;
import model.request.RequestManager;
import org.junit.Test;

/**
 *
 * @author Gwendal
 */
public class NetworkTest {

    @Test
    public void mainTest() {
        try {
            //settings
            String localhost = InetAddress.getLocalHost().getHostAddress();
            final Network me = new Network(localhost);
            String coucou = "Salut Bob, c'est Alice";
            final Message msg = new Message(coucou);
            final RequestManager rm = (RequestManager)Engine.INSTANCE.getRequestManager();
            
            //connecting
            Server.INSTANCE.binding();
            final String url = Server.INSTANCE.getUrl();
            
            Thread alice = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    try {
                            me.connect(url);
                            Request<Message> req = new Request(msg);
                            me.send(req, me.getIp());
                        } catch (RemoteException ex) {
                            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                
                
            });
            Thread bob = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    synchronized(rm) {
                        try {
                            me.connect(url);
                        } catch (RemoteException ex) {
                            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        while(rm.getInbox().isEmpty()) {
                            try {
                                rm.wait();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                
            });
            bob.start();
            alice.start();
            bob.join();
            alice.join();
            Message received = (Message)rm.getInbox(0);
            System.out.println("Expected : " + coucou);
            System.out.println("Got : " + received.descriptionProperty().get());
            Assert.assertEquals(coucou, received.descriptionProperty().get());
        } catch (InterruptedException | UnknownHostException | RemoteException ex) {
            Assert.fail("exception");
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

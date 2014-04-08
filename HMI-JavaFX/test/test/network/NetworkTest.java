/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.network;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import model.engine.Engine;
import model.network.implementation.Network;
import model.node.Message;
import model.request.RequestManager;
import org.junit.Test;

/**
 *
 * @author Gwendal
 */
public class NetworkTest {
    
    private final Network me;
    private final Message msg;
    
    public NetworkTest() throws RemoteException {
        me = new Network();
        msg = new Message();
    }

    @Test
    public void mainTest() {
        try {
            final Object lock = new Object();
            Thread alice = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    synchronized(lock) {/*
                        Request<Message> req = new Request(msg, me.getReceiver());
                        me.send(req);
                        lock.notify();*/
                    }
                }
                
            });
            Thread bob = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    synchronized(lock) {
                        RequestManager rm = (RequestManager)Engine.INSTANCE.getRequestManager();
                        while(rm.getInbox().isEmpty()) {
                            try {
                                lock.wait();
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
            //Assert.
        } catch (InterruptedException ex) {
            Assert.fail("exception");
            Logger.getLogger(NetworkTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

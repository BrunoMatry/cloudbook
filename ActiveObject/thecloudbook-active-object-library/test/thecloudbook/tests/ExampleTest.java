/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.tests;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Assert;
import org.junit.Test;
import thecloudbook.example.SchedulerExample;
import thecloudbook.example.SendableExample;
import thecloudbook.example.ServantExampleFactory;
import thecloudbook.example.ServerExample;
import thecloudbook.implementation.ProxyClientService;
import thecloudbook.implementation.Scheduler;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public class ExampleTest {
    
    @Test
    public void testRMIActiveObject() {
        SchedulerExample myScheduler = null;
        try {
            Scheduler sched = new Scheduler(new ServantExampleFactory(),
                    InetAddress.getLocalHost().getHostAddress(),
                    8888,
                    "myScheduler");
            myScheduler = new SchedulerExample(sched);
            String url = sched.getUrl();
            System.out.println(url);
            Naming.bind(url, myScheduler);
            ProxyClientService pcs = new ProxyClientService(url);
            Sendable mySendable = new SendableExample(21111992);
            pcs.send(mySendable);
            Assert.assertEquals(21111992, ServerExample.INSTANCE.getMessage());
        } catch (UnknownHostException | RemoteException | AlreadyBoundException |
                MalformedURLException | NotBoundException ex) {
            Assert.fail(ex.getMessage());
            Logger.getLogger(ExampleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

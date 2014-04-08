/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import thecloudbook.implementation.Scheduler;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public final class ServerScheduler extends Scheduler {

    public static final ServerScheduler INSTANCE = makeServerScheduler();
    
    private ServerScheduler(ServantFactory sf) {
        super(sf);
    }
    
    private static ServerScheduler makeServerScheduler() {
        
            ServerScheduler res = new ServerScheduler(new ServerServantFactory());
            //Naming.bind(res.getUrl(), res);
            return res;
        
    }
}

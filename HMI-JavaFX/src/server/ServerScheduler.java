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
    
    public ServerScheduler(ServantFactory sf, String address, int port, String name) throws RemoteException, AlreadyBoundException, MalformedURLException {
        super(sf, address, port, name);
    }
    
    private static ServerScheduler makeServerScheduler() {
        try {
            return new ServerScheduler(new ServerServantFactory(),
                    InetAddress.getLocalHost().getHostAddress(),
                    8888,
                    "myScheduler");
        } catch (RemoteException | AlreadyBoundException | MalformedURLException | UnknownHostException ex) {
            Logger.getLogger(ServerScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

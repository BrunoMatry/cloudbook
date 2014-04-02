/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation.clientServer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import model.network.interfaces.factory.RequestHandler;
import thecloudbook.implementation.Scheduler;
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public class ClientServerHandler extends Scheduler implements RequestHandler {

    /**
     * Constructor
     * @param sf factory to be used to instanciate servants
     * @param address IP addrress
     * @param port port on which the client listen
     * @param name name of the application
     * @throws RemoteException
     * @throws AlreadyBoundException
     * @throws MalformedURLException 
     */
    public ClientServerHandler(ServantFactory sf, String address, int port, String name)
            throws RemoteException, AlreadyBoundException, MalformedURLException {
        super(sf, address, port, name);
    }
 
}

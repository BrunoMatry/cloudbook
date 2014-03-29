/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation.clientServer;

import java.rmi.RemoteException;
import model.network.interfaces.factory.RequestHandler;
import thecloudbook.interfaces.ISendCommand;

/**
 *
 * @author Gwendal
 */
public class ClientServerHandler implements RequestHandler {

    @Override
    public void dispatch(ISendCommand command) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onReceived(ISendCommand command) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

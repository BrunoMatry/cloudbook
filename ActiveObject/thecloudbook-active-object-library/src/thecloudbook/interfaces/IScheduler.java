/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import thecloudbook.implementation.ProxyClientService;

/**
 *
 * @author Gwendal
 * receiving object specification
 */
public interface IScheduler extends Remote {
    public void dispatch(ISendCommand command) throws RemoteException;
    
    /**
     * method which should be called with rmi
     * manages a received command
     * @param sender sender of a Sendable object
     * @param offset index of the sent object
     * @throws java.rmi.RemoteException necessary for the remote to 
     */
    public void onReceived(ProxyClientService sender, int offset) throws RemoteException;
}

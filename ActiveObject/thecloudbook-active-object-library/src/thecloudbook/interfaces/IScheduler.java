/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Gwendal
 * receiving object specification
 */
public interface IScheduler extends Remote {
    void dispatch(ISendCommand command) throws RemoteException;
    
    /**
     * method which should be called with rmi
     * manages a received command
     * @param command command to manage
     * @throws java.rmi.RemoteException necessary for the remote to 
     */
    void onReceived(ISendCommand command) throws RemoteException;
}

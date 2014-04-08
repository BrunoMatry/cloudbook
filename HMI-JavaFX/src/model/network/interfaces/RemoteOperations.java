/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public interface RemoteOperations extends Remote {
    public void handleRequest(Sendable request) throws RemoteException;
    public void send(Sendable request) throws RemoteException;
}

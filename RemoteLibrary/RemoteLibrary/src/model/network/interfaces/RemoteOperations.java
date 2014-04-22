/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Gwendal
 */
public interface RemoteOperations extends Remote {
    public void send(Sendable request, String receiver) throws RemoteException;
    public String getId() throws RemoteException;
}

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
public interface RemoteServer extends Remote, RemoteOperations {
    void connect(RemoteClient rc) throws RemoteException;
    void disconnect(RemoteClient rc) throws RemoteException;
    RemoteClient getClient(String client) throws RemoteException;
    void binding() throws RemoteException;
    String getUrl() throws RemoteException;
}

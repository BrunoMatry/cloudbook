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
public interface RemoteClient extends Remote, RemoteOperations {
    RemoteServer getStub() throws RemoteException;
    void connect(String url) throws RemoteException;
    void connect() throws RemoteException;
    public void handleRequest(Sendable request) throws RemoteException;
}

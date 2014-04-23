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
public interface RemoteBufferedClient extends Remote, RemoteClient {
    
    /**
     * gets a Sendable received by a client
     * @param index index of the received object in the client data structure
     * @return required Sendable 
     * @throws RemoteException in case of remote access problem
     */
    Sendable getSendable(int index) throws RemoteException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for a remote server which stores the received Sendables
 * producer/consumer
 * @author Gwendal
 */
public interface RemoteBufferedServer extends RemoteServer, Remote {
    
    /**
     * Gets the request stored at the specified position and for the specified client.
     * @param receiver receiver of a request.
     * @param index position of the message in the data structure.
     * @return the corresponding request.
     * @throws RemoteException Problem with remote access.
     */
    Sendable getSendable(String receiver, int index) throws RemoteException;
}

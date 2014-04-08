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
 * service to be performed specificaction
 */
public interface IClientService extends Remote {
    void send(Sendable sendable) throws RemoteException;
}

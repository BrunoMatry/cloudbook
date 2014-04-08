/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.interfaces;

import model.request.Sendable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Gwendal
 */
public interface IProxyClientService extends Remote, IClientService {
    @Override
    public void send(Sendable sendable) throws RemoteException;
    @Override
    public ISendCommand getSent(int i) throws RemoteException;
}

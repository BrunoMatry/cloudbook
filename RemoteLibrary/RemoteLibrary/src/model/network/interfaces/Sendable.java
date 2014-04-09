/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Gwendal
 */
public interface Sendable extends Remote {
    public ISender getSender() throws RemoteException;

    public Date getDate() throws RemoteException;

    public Information getInfo() throws RemoteException;

    public int getRebounds() throws RemoteException;

    public int getRecipent() throws RemoteException;
    
    public int getId() throws RemoteException;
}

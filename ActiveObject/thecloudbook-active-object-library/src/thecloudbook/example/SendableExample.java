/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.interfarequestle;

/**
 *
 * @author Gwendal
 */
public class SendableExample extends UnicastRemoteObject implements Sendable {

    private int value;
    
    public SendableExample(int val) throws RemoteException {
        value = val;
    }
    
    @Override
    public String toString() {
        return "SendableExample{" + value + '}';
    }

    @Override
    public int getId() throws RemoteException {
        return value;
    }
    
}

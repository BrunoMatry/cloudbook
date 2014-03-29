/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.implementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import thecloudbook.interfaces.IClientService;
import thecloudbook.interfaces.ISendCommand;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public class SendCommand implements ISendCommand {

    protected Sendable request;
    
    public SendCommand(Sendable sendable) {
        request = sendable;
    }
    
    @Override
    public void call(IClientService servant) {
        servant.send(request);
    }

    @Override
    public boolean guard() {
        return true;
    }
    
}
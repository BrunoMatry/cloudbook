/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.implementation;

import thecloudbook.interfaces.IServant;
import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public abstract class Servant extends Thread implements IServant {

    protected Sendable request;
    
    @Override
    public void send(Sendable sendable) {
        request = sendable;
        start();
    }
    
}

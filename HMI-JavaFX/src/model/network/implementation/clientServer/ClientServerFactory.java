/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation.clientServer;

import model.network.interfaces.factory.NetworkFactory;
import model.network.interfaces.factory.RequestHandler;
import model.network.interfaces.factory.RequestSender;

/**
 *
 * @author Gwendal
 */
public class ClientServerFactory implements NetworkFactory {

    @Override
    public RequestHandler makeRequestHandler() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RequestSender makeRequestSender() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

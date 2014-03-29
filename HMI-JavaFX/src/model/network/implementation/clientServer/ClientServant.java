/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation.clientServer;

import model.network.implementation.Network;
import model.request.Request;
import thecloudbook.implementation.Servant;

/**
 *
 * @author Gwendal
 */
public class ClientServant extends Servant {

    @Override
    public void run() {
        Network.INSTANCE.handleRequest((Request)request);
    }
    
}

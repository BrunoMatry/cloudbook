/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation.clientServer;

import model.network.implementation.Network;
import thecloudbook.interfaces.IClientService;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public class ClientServantFactory implements ServantFactory {

    //the network responsible of the factory and its products
    protected Network master;
    
    public ClientServantFactory(Network network) {
        master = network;
    }
    
    @Override
    public IClientService makeServant() {
        return new ClientServant(master);
    }
    
}

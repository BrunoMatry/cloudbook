/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package server;

import thecloudbook.interfaces.IServant;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public class ServerServantFactory implements ServantFactory {

    @Override
    public IServant makeServant() {
        return new ServerServant();
    }
    
}

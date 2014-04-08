/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.example;

import thecloudbook.interfaces.IServant;
import thecloudbook.interfaces.ServantFactory;

/**
 *
 * @author Gwendal
 */
public class ServantExampleFactory implements ServantFactory {

    @Override
    public IServant makeServant() {
        return new ServantExample();
    }
    
}

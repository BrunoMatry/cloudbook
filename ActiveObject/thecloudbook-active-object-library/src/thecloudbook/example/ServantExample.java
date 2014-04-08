/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.example;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import thecloudbook.implementation.Servant;
import model.interfarequestle;

/**
 *
 * @author Gwendal
 */
public class ServantExample extends Servant {

    @Override
    public void run() {
        Sendable se = (Sendable)request;
        System.out.println(se);
        try {
            ServerExample.INSTANCE.setMessage(se.getId());
        } catch (RemoteException ex) {
            Logger.getLogger(ServantExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

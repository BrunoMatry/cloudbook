/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.example;

import thecloudbook.implementation.Servant;

/**
 *
 * @author Gwendal
 */
public class ServantExample extends Servant {

    @Override
    public void run() {
        SendableExample se = (SendableExample)request;
        System.out.println(se);
        ServerExample.INSTANCE.setMessage(se.getValue());
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.network;

import model.network.implementation.Network;
import modele.node.Message;
import org.junit.Test;

/**
 *
 * @author Gwendal
 */
public class NetworkTest {
    
    private final Network me;
    private final Message msg;
    
    public NetworkTest() {
        me = new Network();
        msg = new Message();
    }

    @Test
    public void mainTest() {
        Thread alice = new Thread(new Runnable() {

            @Override
            public void run() {
                //TODO
            }
        
        });
    }
}

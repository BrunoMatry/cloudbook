/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test.network;

import model.network.implementation.Network;
import org.junit.Test;

/**
 *
 * @author Gwendal
 */
public class NetworkTest {
    
    private final Network alice;
    private final Network bob;
    
    public NetworkTest() {
        alice = new Network();
        bob = new Network();
    }

    @Test
    public void mainTest() {
        
    }
}

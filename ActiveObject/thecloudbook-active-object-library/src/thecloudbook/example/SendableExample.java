/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.example;

import thecloudbook.interfaces.Sendable;

/**
 *
 * @author Gwendal
 */
public class SendableExample implements Sendable {

    private int value;
    
    public SendableExample(int val) {
        value = val;
    }
    
    @Override
    public String toString() {
        return "SendableExample{" + value + '}';
    }

    public int getValue() {
        return value;
    }
    
}

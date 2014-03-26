/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thecloudbook.example;

/**
 *
 * @author Gwendal
 * singleton
 */
public final class ServerExample {
    
    public static final ServerExample INSTANCE = new ServerExample();
    
    private int message;
    
    private ServerExample() {
        
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
   
}

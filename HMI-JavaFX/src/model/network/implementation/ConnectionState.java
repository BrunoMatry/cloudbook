/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.implementation;

import hmi.button.IconFlyWeight;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Gwendal
 */
public class ConnectionState {
    
    //indicates if the state is "connected" or not
    protected boolean connected;
    
    //image corresponding to the state
    protected ObjectProperty<Image> led;
    public final ObjectProperty<Image> ledProperty() {
        return led;
    }
    
    /**
     * Constructor
     * @param connected true if the client is connected to its network
     */
    public ConnectionState(boolean connected) {
        led = new SimpleObjectProperty<>();
        this.connected = connected;
        if(this.connected)
           led.set(IconFlyWeight.INSTANCE.getGreenLed());
        else
           led.set(IconFlyWeight.INSTANCE.getRedLed()); 
    }

    /**
     * Getter
     * @return connected attribute
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Setter
     * @param connected connected attribute
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
        if(this.connected)
           led.set(IconFlyWeight.INSTANCE.getGreenLed());
        else
           led.set(IconFlyWeight.INSTANCE.getRedLed()); 
    }
}
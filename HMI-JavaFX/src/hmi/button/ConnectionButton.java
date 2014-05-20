/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.button;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.engine.Engine;
import model.network.implementation.Network;

/**
 *
 * @author Gwendal
 */
public abstract class ConnectionButton extends Button {
    
    protected Network network;
    
    //images corresponding to the connection state
    protected ImageView iconConnected;
    
    /**
     * Construct a button with a led image
     * @param network network to be connected or disconnected
     */
    public ConnectionButton(Network network) {
        super();
        this.network = network;
        this.iconConnected = new ImageView();
        ObjectProperty<Image> model = this.network.getConnectionState().ledProperty();
        this.iconConnected.imageProperty().bind(model);
        setGraphic(this.iconConnected);
        setUp();
    }
    
    /**
     * Set up the button action
     */
    private void setUp() {
       this.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent t) {
               Engine engine = network.getEngine();
               if(network.getConnectionState().isConnected())
                   engine.setStopFlag(true);
               else
                   engine.start();
           }
       });
    }
    
}

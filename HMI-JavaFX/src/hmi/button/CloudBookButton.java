/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gwendal
 */
public class CloudBookButton extends Button {
    
    protected static final ImageView icon = new ImageView("file:res/cloud_picture.png");
    
    public CloudBookButton() {
        super();
        setGraphic(icon);
    }
    
    public CloudBookButton(String text) {
        super(text);
        setGraphic(icon);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Customized button with a cloud icon
 * @author Gwendal
 */
public class CloudBookButton extends Button {
    
    protected static final ImageView icon = new ImageView("file:res/cloud_picture.png");
    
    /**
     * see Button()
     */
    public CloudBookButton() {
        super();
        setGraphic(icon);
    }
    
    /**
     * see Button(String text)
     * @param text : label of the button
     */
    public CloudBookButton(String text) {
        super(text);
        setGraphic(icon);
    }
}

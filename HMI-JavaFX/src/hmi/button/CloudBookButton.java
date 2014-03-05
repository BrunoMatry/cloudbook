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
    
    /**
     * put a cloud image on the button
     */
    public CloudBookButton() {
        super();
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getCloud());
        setGraphic(icon);
    }
    
    /**
     * same as default constructor but add a label to the button
     * @param text : label of the button
     */
    public CloudBookButton(String text) {
        super(text);
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getCloud());
        setGraphic(icon);
    }
}

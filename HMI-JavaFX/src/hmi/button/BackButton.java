/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * button with a back arrow
 * @author Gwendal
 */
public class BackButton extends Button {
    
    /**
     * put a back arrow image on the button
     */
    public BackButton() {
        super();
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getBackArrow());
        setGraphic(icon);
    }
}

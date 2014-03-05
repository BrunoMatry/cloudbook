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
public class BackButton extends Button {
    
    /**
     * see Button()
     */
    public BackButton() {
        super();
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getBackArrow());
        setGraphic(icon);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Button with a home image
 * @author Gwendal
 */
public class HomeButton extends Button {
    
    /**
     * construct a button with a home image
     */
    public HomeButton() {
        super();
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getHome());
        setGraphic(icon);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.bar;

import hmi.button.ExitButton;
import javafx.scene.Parent;

/**
 *
 * @author Bruno
 */
public class MenuBar extends Parent {
    private ExitButton exitButton;
    
    public MenuBar() {
        this.exitButton = new ExitButton();
        
        this.getChildren().add(exitButton);
    }
}

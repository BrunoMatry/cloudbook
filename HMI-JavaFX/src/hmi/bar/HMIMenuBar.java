/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.bar;

import hmi.button.HMIExitButton;
import javafx.scene.Parent;

/**
 *
 * @author Bruno
 */
public class HMIMenuBar extends Parent {
    private HMIExitButton exitButton;
    
    public HMIMenuBar() {
        this.exitButton = new HMIExitButton();
        
        this.getChildren().add(exitButton);
    }
}
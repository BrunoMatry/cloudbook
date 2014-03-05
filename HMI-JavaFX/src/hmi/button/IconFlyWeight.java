/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.button;

import javafx.scene.image.Image;

/**
 * fly-weight for images that are displayed in the application
 * singleton
 * @author Gwendal
 */
public final class IconFlyWeight {
    
    public static final IconFlyWeight INSTANCE = new IconFlyWeight();
    
    /**
     * singleton constructor
     */
    private IconFlyWeight() {
        
    }
    
    private Image backArrow;
    private Image cloud;
    private Image exit;
   
    public Image getBackArrow() {
        if(backArrow == null)
            backArrow = new Image("file:res/back_button.png");
        return backArrow;
    }

    public Image getCloud() {
        if(cloud == null)
            cloud = new Image("file:res/cloud_picture.png");
        return cloud;
    }

    /*
    public Image getExit() {
        if(exit == null)
            exit = new Image();
        return exit;
    }
    */
}

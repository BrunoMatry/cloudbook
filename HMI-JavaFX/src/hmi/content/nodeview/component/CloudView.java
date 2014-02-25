/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.nodeview.component;

import javafx.scene.image.ImageView;

/**
 *
 * @author Gwendal
 */
public class CloudView extends ImageView {
    
    protected static final String defaultLogo = "file:res/default_logo.png";
    
    public CloudView() {
        super(defaultLogo);
    }
}

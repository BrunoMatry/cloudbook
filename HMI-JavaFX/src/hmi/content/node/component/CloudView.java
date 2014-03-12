/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import hmi.content.register.RegisterView;
import javafx.beans.property.ObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.node.Cloud;

/**
 * //Binding 
 * @author Gwendal
 */
public class CloudView extends ImageView {
    
    protected static final String defaultLogo = "file:res/default_logo.png";
    
    public CloudView(Cloud model) {
        super(model.getIcon().get());
        ObjectProperty<Image> pt = RegisterView.INSTANCE.getvBox().getClouds().valueProperty();
        imageProperty().bind(pt);
    }
}

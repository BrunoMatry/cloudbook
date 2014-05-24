/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Gwendal
 */
public class CloudImageProperty extends SimpleObjectProperty<Image> {

    public CloudImageProperty() {
    }

    public CloudImageProperty(Image t) {
        super(t);
    }
    
    public void bind(CloudProperty cloudProperty) {
        cloudProperty.addBoundProperty(this);
    }
}

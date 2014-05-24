/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import model.node.Cloud;

/**
 * Defines a cloud property bindable with an image
 * @author Gwendal
 */
public class CloudProperty extends SimpleObjectProperty<Cloud> {
    
    //Image properties "enslaved" by this cloud property
    private final List<CloudImageProperty> boundProperties;
    
    /**
     * Constructor
     */
    public CloudProperty() {
        super();
        boundProperties = new ArrayList<>();
    }
    
    
    /**
     * Constructor
     * @param cloud value of the property 
     */
    public CloudProperty(Cloud cloud) {
        super(cloud);
        boundProperties = new ArrayList<>();
    }
    
    /**
     * "Enslaves" the specified property
     * @param slave property to be "enslaved"
     */
    public void addBoundProperty(CloudImageProperty slave) {
        if(!boundProperties.contains(slave)) {
            boundProperties.add(slave);
            Image image = get().getIcon().get();
            slave.set(image);
        }
    }

    @Override
    public void set(Cloud t) {
        super.set(t);
        Image image = t.getIcon().get();
        for(CloudImageProperty cip : boundProperties)
            cip.set(image);
    } 
    
}

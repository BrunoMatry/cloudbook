/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import hmi.button.IconFlyWeight;
import java.util.HashMap;
import java.util.Map;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import model.node.Cloud;

/**
 * A relation associating a cloud and its corresponding image.
 * An image must be bound to an instance of this class,
 * and the same instance bound to a cloud in order to make the link
 * @author Gwendal
 */
public class CloudImageRelation extends SimpleObjectProperty<Cloud> {
    
    //Associations cloud - image
    private Map<Cloud, Image> relation;
    
    //property to bind to the view
    private ObjectProperty<Image> image;
    
    /**
     * Constructor
     */
    public CloudImageRelation() {
        super();
        initialize();
    }

    /**
     * Constructor
     * @param t initial value
     */
    public CloudImageRelation(Cloud t) {
        super(t);
        initialize();
    }
    
    /**
     * Sets up the correspondance table
     */
    private void initialize() {
        this.relation = new HashMap<>();
        this.image = new SimpleObjectProperty<>();
        Image google = IconFlyWeight.INSTANCE.getGoogle();
        Image amazon = IconFlyWeight.INSTANCE.getAmazon();
        Image windows = IconFlyWeight.INSTANCE.getWindows();
        Image defaultCloud = IconFlyWeight.INSTANCE.getDefaultCloud();
        this.relation.put(Cloud.GOOGLE, google);
        this.relation.put(Cloud.AMAZON, amazon);
        this.relation.put(Cloud.WINDOWS, windows);
        this.relation.put(Cloud.DEFAULT, defaultCloud);
    }

    /**
     * Binds and sets the image value corresponding to the master property value
     * @param ov master property
     */
    @Override
    public void bind(ObservableValue<? extends Cloud> ov) {
        super.bind(ov);
        Cloud cloud = ov.getValue();
        this.image.set(this.relation.get(cloud));
    }
  
    /**
     * Makes a specified image property be bound to the image side of the relation
     * @param imageProperty image Property to bind with
     */
    public void drive(ObjectProperty<Image> imageProperty) {
        imageProperty.bind(image);
    }
    
    /**
     * Sets the cloud value and the corresponding image to the image property as well
     * @param cloud cloud value
     */
    @Override
    public void set(Cloud cloud) {
        this.set(cloud);
        this.image.set(this.relation.get(cloud));
    }
}

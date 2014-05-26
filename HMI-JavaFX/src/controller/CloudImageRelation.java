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
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import model.node.Cloud;

/**
 * A relation associating a cloud and its corresponding image.
 * An image must be bound to an instance of this class,
 * and the same instance bound to a cloud in order to make the link
 * @author Gwendal
 */
public class CloudImageRelation extends Relation<Cloud, Image> {
    
    //Associations cloud - image
    private Map<Cloud, Image> relation;
    
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
        this.image.set(this.relation.get(t));
    }
    
    /**
     * Sets up the correspondance table
     */
    private void initialize() {
        this.relation = new HashMap<>();
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
        System.out.println("Appel du setter de l'image property dans bind");
        this.image.set(this.relation.get(cloud));
    }
    
    /**
     * Sets the cloud value and the corresponding image to the image property as well
     * @param cloud cloud value
     */
    @Override
    public void set(Cloud cloud) {
        super.set(cloud);
        System.out.println("Appel du setter de l'image property dans set");
        this.image.set(this.relation.get(cloud));
    }

    @Override
    public void setValue(Cloud t) {
        super.setValue(t);
        System.out.println("Appel du setter de l'image property dans setValue");
        this.image.set(this.relation.get(t));
    }
}

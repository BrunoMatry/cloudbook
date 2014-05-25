/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

/**
 * Defines the concept of relation to bind a model type and a view type
 * @author Gwendal
 * @param <M> Model type
 * @param <V> View Type
 */
public abstract class Relation<M, V> extends SimpleObjectProperty<M> {
    
    //property to bind to the view
    protected ObjectProperty<V> image;
    
    /**
     * Constructor
     */
    public Relation() {
        super();
        this.image = new SimpleObjectProperty<>();
    }

    /**
     * Constructor
     * @param m initial value
     */
    public Relation(M m) {
        super(m);
        this.image = new SimpleObjectProperty<>();
    }

    /**
     * Binds and sets the image value corresponding to the master property value
     * @param ov master property
     */
    @Override
    public void bind(ObservableValue<? extends M> ov) {
        super.bind(ov);
    }
  
    /**
     * Makes a specified image property be bound to the image side of the relation
     * @param imageProperty image Property to bind with
     */
    public void drive(ObjectProperty<V> imageProperty) {
        imageProperty.bind(image);
    }
    
    /**
     * Sets the model value and the corresponding image to the image property as well
     * @param m model value
     */
    @Override
    public void set(M m) {
        this.set(m);
    }
}

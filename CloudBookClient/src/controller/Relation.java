package controller;

import java.util.Observer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Defines the concept of relation to bind a model type and a view type
 * @param <M> Model type
 * @param <V> View Type
 */
public abstract class Relation<M, V> implements Observer {
    
    //property to bind to the view
    protected ObjectProperty<V> image;
    
    /**
     * Constructor
     */
    public Relation() {
        this.image = new SimpleObjectProperty<>();
    }

    /**
     * Constructor
     * @param m initial value
     */
    public Relation(M m) {
        this.image = new SimpleObjectProperty<>();
    }
  
    /**
     * Makes a specified image property be bound to the image side of the relation
     * @param imageProperty image Property to bind with
     */
    public void drive(ObjectProperty<V> imageProperty) {
        imageProperty.bind(image);
    }
    
}

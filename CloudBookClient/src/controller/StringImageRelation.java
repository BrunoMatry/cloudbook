package controller;

import hmi.button.IconFlyWeight;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;

/**
 * Defines a relation between an image and its imgae file name.
 * The image is set when its name is set in the property to which this one is bound.
 * @author Gwendal
 */
public class StringImageRelation extends SimpleStringProperty {
    
    //property to bind to the view
    protected ObjectProperty<Image> image;
    
    /**
     * Constructor
     */
    public StringImageRelation() {
        super();
        this.image = new SimpleObjectProperty<>();
    }

    /**
     * Constructor
     * @param value initial value
     */
    public StringImageRelation(String value) {
        super(value);
        Image icon = IconFlyWeight.INSTANCE.getByName(value);
        this.image = new SimpleObjectProperty<>(icon);
    }

    /**
     * Binds and sets the image value corresponding to the master property value
     * @param ov master property
     */
    @Override
    public void bind(ObservableValue<? extends String> ov) {
        super.bind(ov);
        String name = ov.getValue();
        Image icon = IconFlyWeight.INSTANCE.getByName(name);
        this.image.set(icon);
    }
  
    /**
     * Makes a specified image property be bound to the image side of the relation
     * @param imageProperty image Property to bind with
     */
    public void drive(ObjectProperty<Image> imageProperty) {
        imageProperty.bind(image);
    }
    
    /**
     * Sets the model value and the corresponding image to the image property as well.
     * @param value model value.
     */
    @Override
    public void set(String value) {
        super.set(value);
        Image icon = IconFlyWeight.INSTANCE.getByName(value);
        this.image.set(icon);
    }
}
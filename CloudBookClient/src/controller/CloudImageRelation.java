package controller;

import hmi.button.IconFlyWeight;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javafx.scene.image.Image;
import model.node.Cloud;

/**
 * A relation associating a cloud and its corresponding image.
 * An image must be bound to an instance of this class,
 * and the same instance bound to a cloud in order to make the link
 */
public class CloudImageRelation extends Relation<Cloud, Image> {
    
    /**
     * Defines the correspondance cloud - image
     */
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

    @Override
    public void update(Observable o, Object o1) {
        Cloud c = (Cloud)o1;
        this.image.set(this.relation.get(c));
    }
}

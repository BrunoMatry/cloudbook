package model.node.friend;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * the relevance must update the connection date for a given member
 */
public class RelevanceProperty extends SimpleDoubleProperty {
    
    //member to which the relevance is related
    protected Friend owner;
    
    /**
     * Constructor
     * @param owner member to which the confidence is related
     * @param value value of the property
     */
    public RelevanceProperty(Friend owner, double value) {
        super(value);
        this.owner = owner;
    }

    @Override
    public void set(double d) {
        super.set(d);
        this.owner.updateLastConnectionDate();
    }
}

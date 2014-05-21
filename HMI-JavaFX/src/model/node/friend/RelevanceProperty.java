/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.node.friend;

import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author Gwendal
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

    /**
     * Set the current value as the specified one
     * The owner last connection date is updated
     * @param d double to be set as the current value
     */
    @Override
    public void set(double d) {
        super.set(d);
        this.owner.updateLastConnectionDate();
    }
}

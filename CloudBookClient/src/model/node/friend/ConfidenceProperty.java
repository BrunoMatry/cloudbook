/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.node.friend;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Gwendal
 * the confidence must update the connection date for a given member
 */
public class ConfidenceProperty extends SimpleIntegerProperty {
    
    //member to which the confidence is related
    protected Friend owner;
    
    /**
     * Constructor
     * @param owner member to which the confidence is related
     * @param value value of the property
     */
    public ConfidenceProperty(Friend owner, int value) {
        super(value);
        this.owner = owner;
    }

    /**
     * Set the current value as the specified one
     * The owner last connection date is updated
     * @param i integer to be set as the current value
     */
    @Override
    public void set(int i) {
        super.set(i);
        this.owner.updateLastConnectionDate();
    }
    
    
}

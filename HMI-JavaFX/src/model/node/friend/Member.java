/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.node.friend;

import model.network.interfaces.Sender;
import model.node.AppVector;

/**
 *
 * @author Gwendal
 * A simple member of the network
 */
public class Member implements Sender {
    
    protected String _id;
    protected AppVector vector;
    
    /**
     * Constructor
     * @param _id identifier of the member
     * @param vector vector of characteristics of the application
     */
    public Member(String _id, AppVector vector) {
        this._id = _id;
        this.vector = vector;
    }
    
    /**
     * Getter
     * @return _id field
     */
    public String getId() {
        return _id;
    }
    
    /**
     * Getter
     * @return vector field
     */
    public AppVector getVector() {
        return vector;
    }
}

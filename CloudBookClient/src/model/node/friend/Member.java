package model.node.friend;

import model.network.interfaces.Sender;
import model.node.AppVector;
import model.node.Cloud;

/**
 * A simple member of the network
 */
public class Member implements Sender {
    
    protected String _id;
    protected AppVector vector;
    protected Cloud _cloud;
    
    /**
     * Constructor
     * @param _id identifier of the member
     * @param vector vector of characteristics of the application
     * @param _cloud cloud on which the member runs
     */
    public Member(String _id, AppVector vector, Cloud _cloud) {
        this._id = _id;
        this.vector = vector;
        this._cloud = _cloud;
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
    
    /**
     * Getter
     * @return cloud field
     */
    public Cloud getCloud() {
        return _cloud;
    }
    
    /**
     * Saves vector properties
     */
    public void beforeSerialization() {
        vector.saveProperties();
    }
    
    /**
     * Restores vector properties
     */
    public void afterDeserialization() {
        vector.restoreProperties();
    }
}
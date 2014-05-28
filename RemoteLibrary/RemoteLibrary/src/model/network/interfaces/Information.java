package model.network.interfaces;

import java.io.Serializable;

/**
 * Sharable information. 
 * @author Gwendal
 */
public interface Information extends Serializable {
    
    /**
     * Saves the class properties before the serialization.
     */
    void saveProperties();
    
    /**
     * Restores the class properties after the deserialization.
     */
    void restoreProperties();
}

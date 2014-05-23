/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.network.interfaces;

import java.io.Serializable;

/**
 *
 * @author Gwendal
 */
public interface Sender extends Serializable {
    
    /**
     * Getter
     * @return identifier of the sender
     */
    String getId();
}

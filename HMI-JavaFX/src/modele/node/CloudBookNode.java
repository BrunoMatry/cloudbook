/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Gwendal
 * 
 * Representation of a node
 */
public class CloudBookNode implements Serializable {
    private List<Friend> friends;
    private List<Information> informations;
    private List<Message> message;
    private List<Mesure> mesure;
    private List<State> state;
    private AppVector vector;
    
    public void addMesure(Mesure m) {
        mesure.add(m);
    }
}

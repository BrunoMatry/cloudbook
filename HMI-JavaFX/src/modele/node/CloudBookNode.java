/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author Gwendal
 * 
 * Representation of a node
 */
public class CloudBookNode implements Serializable {
    private List<Friend> friends;
    private List<Information> informations;
    private List<Message> messages;
    private List<Mesure> mesure;
    private Stack<State> state;
    private AppVector vector;
    
    public CloudBookNode(Cloud c, int appType, int performance, int speed) {
        this.friends = new ArrayList<>();
        this.informations = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.mesure = new ArrayList<>();
        this.state = new Stack<>();
        this.vector = new AppVector(appType, performance, speed);
        
        this.state.push(new State(c));
    }
    
    public void addMesure(Mesure m) {
        mesure.add(m);
        State currentState = this.state.pop();
        currentState.majEndDate();
    }
}

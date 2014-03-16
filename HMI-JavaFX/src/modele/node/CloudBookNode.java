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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author Gwendal
 * 
 * Representation of a node
 */
public class CloudBookNode implements Serializable {
    private final List<Friend> friends;
    private final List<Information> informations;
    private final List<Message> messages;
    private final List<Mesure> mesure;
    private final Stack<State> state;
    private final ObjectProperty<AppVector> vector;
    
    public CloudBookNode(Cloud c, int appType, int performance, int speed) {
        this.friends = new ArrayList<>();
        this.informations = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.mesure = new ArrayList<>();
        this.state = new Stack<>();    
        this.vector = new SimpleObjectProperty<>();
              
        this.state.push(new State(c));
        this.vector.set(new AppVector(appType, performance, speed));
    }

    public void majCurrentState(Cloud c) {
        State currentState = this.state.peek();
        if(!c.equals(currentState.getCloud())) {
            currentState.notCurrentAnymore();
            state.push(new State(c));
        }    
    }
  
    public void addMesure(Mesure m) { mesure.add(m); }
    public void addMessage(Message m) { messages.add(m); } 
    public void addInformation(Information info) { informations.add(info); }
    public void addFriend(Friend f) { friends.add(f); }
    
    public ObjectProperty<AppVector> getVector() { return vector; }
    public void setVector(int appType, int performance, int speed) { 
        this.vector.set(new AppVector(appType, performance, speed));
    }
}

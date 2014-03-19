/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modele.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import java.util.Stack;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gwendal
 * TODO : gerer la serialisation
 * Representation of a node
 */
public class CloudBookNode implements Serializable {

    protected List<Friend> friends;
    protected List<Information> informations;
    
    //list of the received messages
    protected List<Message> message;
    
    //message to be shown at the first place
    protected Message topMessage;
    public StringProperty topMessageProperty() {
        return topMessage.descriptionProperty();
    }
    
    //list of all the mesures
    protected List<Mesure> mesure;
    
    //mesure to be shown at the first place
    protected Mesure topMesure;
    public StringProperty topMesureProperty() {
        return topMesure.descriptionProperty();
    }
    
    //history of the cloud platforms
    protected Stack<State> state;
    
    protected AppVector vector;
    
    //name of the application
    protected MySimpleStringProperty name;
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    //logo of the application
    protected MySimpleObjectProperty<Image> logo;
    public SimpleObjectProperty<Image> logoProperty() {
        return logo;
    }
    
    //current platform of the application
    protected Cloud platform;
    public ObjectProperty<Image> platformProperty() {
        return platform.iconProperty();
    }
    
    /**
     * default constructor
     */
    public CloudBookNode() {
        friends = new ArrayList<>();
        this.informations = new ArrayList<>();
        state = new Stack<>();
        mesure = new ArrayList<>();
        message = new ArrayList<>();    
        this.vector = new AppVector(0,0,0);
        topMesure = new Mesure();
        topMessage = new Message();
        name = new MySimpleStringProperty();
        logo = new MySimpleObjectProperty<>();
    }
    
    /**
     * arg constructor
     * @param image logo
     * @param string name
     * @param cloud cloud-platform
     * @param appType type of the application
     * @param performance TODO
     * @param speed TODO
     */
    public CloudBookNode(Image image, String string, Cloud cloud, int appType, int performance, int speed) {
        state = new Stack<>();
        mesure = new ArrayList<>();
        message = new ArrayList<>();
        topMesure = new Mesure();
        topMessage = new Message();
        logo = new MySimpleObjectProperty<>(image);
        name = new MySimpleStringProperty(string);
        platform = cloud;
        this.state.push(new State(cloud));
        this.vector = new AppVector(appType, performance, speed);
    }

    public void addMesure(Mesure m) {
        mesure.add(m);
    }

    public List<Friend> getFriends() {
        return friends;
    }
    
    /**
     * updates the cloud-platform of the current application
     * @param c new cloud-platform
     */
    public void majCurrentState(Cloud c) {
        State currentState = this.state.peek();
        if(!c.equals(currentState.getCloud())) {
            currentState.notCurrentAnymore();
            state.push(new State(c));
        }    
    }
  
    public void addMessage(Message m) { message.add(m); } 
    public void addInformation(Information info) { informations.add(info); }
    public void addFriend(Friend f) { friends.add(f); }
    
    public AppVector getVector() { return vector; }
    public void setVector(int appType, int performance, int speed) { 
        this.vector = new AppVector(appType, performance, speed);
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Information> getInformations() {
        return informations;
    }

    public void setInformations(List<Information> informations) {
        this.informations = informations;
    }

    public void setVector(AppVector vector) {
        this.vector = vector;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public SimpleObjectProperty<Image> getLogo() {
        return logo;
    }

}

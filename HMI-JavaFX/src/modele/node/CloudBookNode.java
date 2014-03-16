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
    protected List<ObjectProperty<Message>> message;
    public ObjectProperty<Message> messageProperty(int i) {
        return message.get(i);
    }
    
    //message to be shown at the first place
    protected ObjectProperty<Message> topMessage;
    public ObjectProperty<Message> topMessageProperty() {
        return topMessage;
    }
    
    //list of all the mesures
    protected List<ObjectProperty<Mesure>> mesure;
    public ObjectProperty<Mesure> mesureProperty(int i) {
        return mesure.get(i);
    }
    
    //mesure to be shown at the first place
    protected ObjectProperty<Mesure> topMesure;
    public ObjectProperty<Mesure> topMesureProperty() {
        return topMesure;
    }
    
    //history of the cloud platforms
    protected Stack<ObjectProperty<State>> state;
    public ObjectProperty<State> stateProperty(int i) {
        return state.get(i);
    }
    
    protected ObjectProperty<AppVector> vector;
    
    //name of the application
    protected SimpleStringProperty name;
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    //logo of the application
    protected SimpleObjectProperty<Image> logo;
    public SimpleObjectProperty<Image> logoProperty() {
        return logo;
    }
    
    //current platform of the application
    protected SimpleObjectProperty<Cloud> platform;
    public SimpleObjectProperty<Cloud> platformProperty() {
        return platform;
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
        this.vector = new SimpleObjectProperty<>();
        topMesure = new SimpleObjectProperty<>(new Mesure());
        topMessage = new SimpleObjectProperty<>(new Message());
        name = new SimpleStringProperty();
        logo = new SimpleObjectProperty<>();
        platform = new SimpleObjectProperty<>();
    }
    
    /**
     * arg constructor
     * @param image : 
     * @param string
     * @param cloud 
     * @param appType 
     * @param performance 
     * @param speed 
     */
    public CloudBookNode(Image image, String string, Cloud cloud, int appType, int performance, int speed) {
        state = new Stack<>();
        mesure = new ArrayList<>();
        message = new ArrayList<>();
        topMesure = new SimpleObjectProperty<>(new Mesure());
        topMessage = new SimpleObjectProperty<>(new Message());
        logo = new SimpleObjectProperty<>(image);
        name = new SimpleStringProperty(string);
        platform = new SimpleObjectProperty<>(cloud);
        this.vector = new SimpleObjectProperty<>();
        this.state.push(new SimpleObjectProperty<>(new State(cloud)));
        this.vector.set(new AppVector(appType, performance, speed));
    }

    public void addMesure(Mesure m) {
        mesure.add(new SimpleObjectProperty<>(m));
    }

    public List<Friend> getFriends() {
        return friends;
    }
    
    public void majCurrentState(Cloud c) {
        State currentState = this.state.peek().get();
        if(!c.equals(currentState.getCloud())) {
            currentState.notCurrentAnymore();
            state.push(new SimpleObjectProperty<>(new State(c)));
        }    
    }
  
    public void addMessage(Message m) { message.add(new SimpleObjectProperty<>(m)); } 
    public void addInformation(Information info) { informations.add(info); }
    public void addFriend(Friend f) { friends.add(f); }
    
    public ObjectProperty<AppVector> getVector() { return vector; }
    public void setVector(int appType, int performance, int speed) { 
        this.vector.set(new AppVector(appType, performance, speed));
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

    public void setVector(ObjectProperty<AppVector> vector) {
        this.vector = vector;
    }

    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public SimpleObjectProperty<Image> getLogo() {
        return logo;
    }

    public void setLogo(SimpleObjectProperty<Image> logo) {
        this.logo = logo;
    }

    public SimpleObjectProperty<Cloud> getPlatform() {
        return platform;
    }

    public void setPlatform(SimpleObjectProperty<Cloud> platform) {
        this.platform = platform;
    }

    
}

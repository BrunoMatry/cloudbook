package model.node;

import model.node.friend.Friend;
import model.network.interfaces.Information;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 * Representation of a node
 */
public class MyNode implements Serializable {

    /************************************** ATTRIBUTS **************************************/
    
    /* Attributs serialisables */
    protected String _name; //name of the application
    /* TODO : retirer transient ; copier l'image dans le système de fichiers de
    l'application quand elle est chargée et référencer  le chemin */
    protected transient Image _logo; //logo of the application
    protected Mesure topMesure; //mesure to be shown at the first place
    protected Cloud _platform; //current platform of the application
    
    protected InformationBox<Friend> friends;
    protected List<Information> informations;
    protected InformationBox<State> states; //history of the cloud platforms
    protected InformationBox<Mesure> mesures; //list of all the mesures
    protected InformationBox<Message> messages; //list of the received messages
    protected AppVector vector;
    
    //port of the application
    protected int nodePort;
    
    /* Proprietes non serialisables */
    protected transient StringProperty name;
    protected transient ObjectProperty<Image> logo;
    private transient ObjectProperty<Cloud> platform;
    
    /************************************ CONSTRUCTEURS ************************************/
    
    /**
     * default constructor
     */
    public MyNode() {
        //topMessage = new Message();
        
        friends = new InformationBox<>();
        informations = new ArrayList<>();
        states = new InformationBox<>();
        mesures = new InformationBox<>();
        messages = new InformationBox<>();    
        vector = new AppVector(mesures);
        
        name = new SimpleStringProperty();
        logo = new SimpleObjectProperty<>();
        platform = new SimpleObjectProperty<>();
    }
    
    /**
     * arg constructor
     * @param image logo
     * @param string name
     * @param cloud cloud-platform
     * @param nodePort port of the current application
     * @param appType type of the application
     * @param performance TODO
     * @param speed TODO
     * @throws java.net.UnknownHostException
     * @throws java.rmi.RemoteException
     */
    public MyNode(Image image, String string, Cloud cloud, int nodePort, int appType, int performance, int speed) throws UnknownHostException, RemoteException {
        platform = new SimpleObjectProperty<>(cloud);
        this.nodePort = nodePort;
        friends = new InformationBox<>();
        informations = new ArrayList<>();
        states = new InformationBox<>();
        mesures = new InformationBox<>();
        messages = new InformationBox<>();
        vector = new AppVector(mesures);
        
        name = new SimpleStringProperty(string);
        logo = new SimpleObjectProperty<>(image);
        
        states.push(new State(cloud));
    }
    
    /**
     * Add to the node a mesure
     * @param m the mesure to add to the node
     */
    public void addMesure(Mesure m) {
        this.mesures.push(m);
        vector.recalculateVector();
    }
    
    /**
     * Add to the node a list of mesures
     * @param mesures the list of mesures to add to the node
     */
    public void addMesures(List<Mesure> mesures) {
        for(Mesure m : mesures)
            this.mesures.push(m);
        vector.recalculateVector();
    }
    
    /********************************** SETTERS / GETTERS **********************************/
    
    public void addMessage(Message m) { messages.push(m); } 
    public void addInformation(Information info) { informations.add(info); }
    public void addFriend(Friend f) { friends.push(f); }
    
    public InformationBox getFriends() { return friends; }
    public AppVector getVector() { return vector; }
    public List<Information> getInformations() { return informations; }
    public Message getMessage(int i) { return messages.get(i); }
    public InformationBox getMessages() { return messages; }
    public InformationBox getMesures() { return mesures; }
    public InformationBox getStates() { return states; }
    
    /**
     * Getter
     * @return nodePort field
     */
    public int getNodePort() {
        return nodePort;
    }
    
    /**
     * Getter
     * @return platform field
     */
    public final Cloud getPlatform() {
        return this.platform.get();
    }
      
    public StringProperty topMesureProperty() { return topMesure.dateProperty(); }
    public StringProperty nameProperty() { return name; }
    public ObjectProperty<Image> logoProperty() { return logo; }
    public ObjectProperty<Cloud> platformProperty() { return platform; }
    
    /* ATTENTION ! Mauvaise pratique ! */
    public void setInformations(List<Information> informations) { this.informations = informations; }
    public void setVector(AppVector vector) { this.vector = vector; }
    
    /*************************************** METHODES **************************************/
    
    /**
     * updates the cloud-platform of the current application
     * @param c new cloud-platform
     */
    public void majCurrentState(Cloud c) {
        int lastIndex = this.states.size() - 1;
        State currentState = this.states.get(lastIndex);
        if(!c.equals(currentState.getCloud())) {
            currentState.notCurrentAnymore();
            states.push(new State(c));
            platform.set(c);
        }    
    }
    
    /**
     * Computes the full identifier on the network
     * @return identifier = "name@ip:port"
     * @throws java.net.UnknownHostException
     */
    public String getMemberId() throws UnknownHostException {
        return name.get() + "@"
                + InetAddress.getLocalHost().getHostAddress().toString()
                + ":" + nodePort;
    }
    
    public void save() throws IOException, ClassNotFoundException {
        _name = name.get();
        _logo = logo.get();
        _platform = platform.get();
        friends.saveProperties();
        for(Information info : informations)
            info.saveProperties();
        mesures.saveProperties();
        messages.saveProperties();
        vector.saveProperties();
        states.saveProperties();
        FileEngineRelation.INSTANCE.save(this, name.get() + ".ser");
    }
    
    public static MyNode load(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        MyNode res = (MyNode) ois.readObject();
        res.name = new SimpleStringProperty(res._name);
        res.friends.restoreProperties();
        for(Information info : res.informations)
            info.restoreProperties();
        res.mesures.restoreProperties();
        res.messages.restoreProperties();
        res.states.restoreProperties();
        res.logo = new SimpleObjectProperty<>(res._logo);
        res.platform = new SimpleObjectProperty<>(res._platform);
        res.vector.restoreProperties();
        return res;
    }
}
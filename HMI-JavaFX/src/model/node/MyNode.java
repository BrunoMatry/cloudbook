package model.node;

import java.io.File;
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

/**
 * Representation of a node
 */
public class MyNode implements Serializable {

    /************************************** ATTRIBUTS **************************************/
    
    /* Attributs serialisables */
    protected String _name; //name of the application
    private String _logo; //logo of the application
    protected Mesure topMesure; //mesure to be shown at the first place
    protected Cloud _platform; //current platform of the application
    private AppVector _vector;
    
    protected InformationBox<Friend> friends;
    protected List<Information> informations;
    protected InformationBox<State> states; //history of the cloud platforms
    protected InformationBox<Mesure> mesures; //list of all the mesures
    protected InformationBox<Message> messages; //list of the received messages
    
    //port of the application
    protected int nodePort;
    
    /* Proprietes non serialisables */
    protected transient StringProperty name;
    private transient StringProperty logo;
    private transient ObjectProperty<Cloud> platform;
    private transient ObjectProperty<AppVector> vector;
    
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
        _vector = new AppVector(mesures);
        vector = new SimpleObjectProperty<>(_vector);
        name = new SimpleStringProperty();
        logo = new SimpleStringProperty();
        platform = new SimpleObjectProperty<>();
    }
    
    /**
     * arg constructor
     * @param logoFile File contaning the logo of the application
     * @param string name
     * @param cloud cloud-platform
     * @param nodePort port of the current application
     * @param appType type of the application
     * @param performance TODO
     * @param speed TODO
     * @throws java.net.UnknownHostException
     * @throws java.rmi.RemoteException
     */
    public MyNode(File logoFile, String string, Cloud cloud, int nodePort, int appType, int performance, int speed) throws UnknownHostException, RemoteException {
        platform = new SimpleObjectProperty<>(cloud);
        this.nodePort = nodePort;
        friends = new InformationBox<>();
        informations = new ArrayList<>();
        states = new InformationBox<>();
        mesures = new InformationBox<>();
        messages = new InformationBox<>();    
        _vector = new AppVector(mesures);
        vector = new SimpleObjectProperty<>(_vector);
        name = new SimpleStringProperty(string);
        logo = new SimpleStringProperty(logoFile.getName());
        
        states.push(new State(cloud));
    }
    
    /**
     * Add to the node a mesure
     * @param m the mesure to add to the node
     */
    public void addMesure(Mesure m) {
        this.mesures.push(m);
        AppVector changedValue = vector.get();
        changedValue.recalculateVector();
        vector.set(changedValue);
    }
    
    /**
     * Add to the node a list of mesures
     * @param mesures the list of mesures to add to the node
     */
    public void addMesures(List<Mesure> mesures) {
        for(Mesure m : mesures)
            this.mesures.push(m);
        AppVector changedValue = vector.get();
        changedValue.recalculateVector();
        vector.set(changedValue);
    }
    
    /********************************** SETTERS / GETTERS **********************************/
    
    public void addMessage(Message m) { messages.push(m); } 
    public void addInformation(Information info) { informations.add(info); }
    public void addFriend(Friend f) { friends.push(f); }
    
    public InformationBox getFriends() { return friends; }
    public AppVector getVector() { return vector.get(); }
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
    public StringProperty logoProperty() { return logo; }
    public ObjectProperty<Cloud> platformProperty() { return platform; }
    public ObjectProperty<AppVector> vectorProperty() { return vector; }
    
    /* ATTENTION ! Mauvaise pratique ! */
    public void setInformations(List<Information> informations) { this.informations = informations; }
    public void setVector(AppVector vector) { this.vector.set(vector); }
    
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
        _vector = vector.get();
        friends.saveProperties();
        for(Information info : informations)
            info.saveProperties();
        mesures.saveProperties();
        messages.saveProperties();
        _vector.saveProperties();
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
        res.logo = new SimpleStringProperty(res._logo);
        res.platform = new SimpleObjectProperty<>(res._platform);
        res._vector.restoreProperties();
        res.vector = new SimpleObjectProperty<>(res._vector);
        return res;
    }
}
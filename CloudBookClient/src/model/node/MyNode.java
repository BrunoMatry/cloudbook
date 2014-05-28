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
    private transient CloudProperty platform;
    private transient ObjectProperty<AppVector> vector;
    
    /**
     * default constructor
     */
    public MyNode() {
        friends = new InformationBox<>();
        informations = new ArrayList<>();
        states = new InformationBox<>();
        mesures = new InformationBox<>();
        messages = new InformationBox<>();
        name = new SimpleStringProperty();
        logo = new SimpleStringProperty();
        platform = new CloudProperty();
    }
    
    /**
     * MyNode constructor
     * @param logoFile File contaning the logo of the application
     * @param string name
     * @param cloud cloud-platform
     * @param nodePort port of the current application
     * @param appType type of the application
     * @param performance performance of the application
     * @param speed speed of the application
     * @throws java.net.UnknownHostException
     * @throws java.rmi.RemoteException
     */
    public MyNode(File logoFile, String string, Cloud cloud, int nodePort, int appType, int performance, int speed) throws UnknownHostException, RemoteException {
        platform = new CloudProperty(cloud);
        this.nodePort = nodePort;
        friends = new InformationBox<>();
        informations = new ArrayList<>();
        states = new InformationBox<>();
        mesures = new InformationBox<>();
        messages = new InformationBox<>();    
        _vector = new AppVector(appType, performance, speed);
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
    }
    
    /**
     * Add to the node a list of mesures
     * @param mesures the list of mesures to add to the node
     */
    public void addMesures(List<Mesure> mesures) {
        for(Mesure m : mesures)
            this.mesures.push(m);
    }
    
    /**
     * Adds a message to the node.
     * @param m message to be added.
     */
    public void addMessage(Message m) { messages.push(m); }
    
    /**
     * Adds an information to the node.
     * @param info information to be added.
     */
    public void addInformation(Information info) { informations.add(info); }
    
    /**
     * Adds a friend to the node.
     * @param f friend to be added.
     */
    public void addFriend(Friend f) { friends.push(f); }
    
    /**
     * Getter
     * @return the friend set.
     */
    public InformationBox getFriends() { return friends; }
    
    /**
     * Getter
     * @return the vector of features.
     */
    public AppVector getVector() { return vector.get(); }
    
    /**
     * Getter
     * @return the information list.
     */
    public List<Information> getInformations() { return informations; }
    
    /**
     * Getter
     * @param i index of the message.
     * @return searched message.
     */
    public Message getMessage(int i) { return messages.get(i); }
    
    /**
     * Getter
     * @return the message set.
     */
    public InformationBox getMessages() { return messages; }
    
    /**
     * Getter
     * @return the mesure set.
     */
    public InformationBox getMesures() { return mesures; }
    
    /**
     * Getter
     * @return the application history.
     */
    public InformationBox getStates() { return states; }
    
    /**
     * Getter
     * @return the port used by the client to communicate on the network.
     */
    public int getNodePort() { return nodePort; }
    
    /**
     * Getter
     * @return the current platform of the application.
     */
    public final Cloud getPlatform() { return this.platform.get(); }
    
    /**
     * Representative mesure of the mesure set.
     * @return Representative mesure of the mesure set.
     */
    public StringProperty topMesureProperty() { return topMesure.dateProperty(); }
    
    /**
     * Application name.
     * @return Application name.
     */
    public StringProperty nameProperty() { return name; }
    
    /**
     * Application logo.
     * @return Application logo.
     */
    public StringProperty logoProperty() { return logo; }
    
    /**
     * Current cloud platform of the application.
     * @return Current cloud platform of the application.
     */
    public CloudProperty platformProperty() { return platform; }
    
    /**
     * Application features vector.
     * @return Application feautures vector.
     */
    public ObjectProperty<AppVector> vectorProperty() { return vector; }
    
    /**
     * Setter
     * @param informations information list.
     */
    public void setInformations(List<Information> informations) { this.informations = informations; }
    
    /**
     * Setter
     * @param vector application features vector.
     */
    public void setVector(AppVector vector) { this.vector.set(vector); }
      
    /**
     * Updates the cloud-platform of the current application
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
    
    /**
     * Save the state of the current node
     * @throws IOException
     * @throws ClassNotFoundException 
     */
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
    
    /**
     * Deserialize a node
     * @param fileName the file to deserialize
     * @return the deserilaized node
     * @throws IOException
     * @throws ClassNotFoundException 
     */
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
        res.platform = new CloudProperty(res._platform);
        res._vector.restoreProperties();
        res.vector = new SimpleObjectProperty<>(res._vector);
        return res;
    }
}
package model.node;

import model.node.friend.Friend;
import hmi.home.ObservableFileList;
import model.network.interfaces.Information;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import model.engine.Engine;
import model.network.interfaces.RemoteServer;

/**
 *
 * Representation of a node
 */
public class CloudBookNode implements Serializable {

    /************************************** ATTRIBUTS **************************************/
    
    /* Attributs serialisables */
    protected String _name; //name of the application
    /* TODO : retirer transient ; copier l'image dans le système de fichiers de
    l'application quand elle est chargée et référencer  le chemin */
    protected transient Image _logo; //logo of the application
    /* Note Bruno : pourquoi ces trois variables ?? Ne faut-il changer les structures de donnees putlôt ? */
    //protected Message topMessage; //message to be shown at the first place
    protected Mesure topMesure; //mesure to be shown at the first place
    protected Cloud platform; //current platform of the application
    
    protected InformationBox<Friend> friends;
    protected List<Information> informations;
    protected Stack<State> states; //history of the cloud platforms
    protected InformationBox<Mesure> mesures; //list of all the mesures
    protected InformationBox<Message> messages; //list of the received messages
    protected AppVector vector;
    
    //host of the server
    protected String serverHost;
    
    //port of the server
    protected int serverPort;
    
    //port of the application
    protected int nodePort;
    
    //engine which run the node
    protected transient Engine engine;
    
    /* Proprietes non serialisables */
    protected transient StringProperty name;
    protected transient ObjectProperty<Image> logo;
    
    /************************************ CONSTRUCTEURS ************************************/
    
    /**
     * default constructor
     */
    public CloudBookNode() {
        //topMessage = new Message();
        
        friends = new InformationBox<>();
        informations = new ArrayList<>();
        states = new Stack<>();
        mesures = new InformationBox<>();
        messages = new InformationBox<>();    
        vector = new AppVector(0,0,0);
        
        name = new SimpleStringProperty();
        logo = new SimpleObjectProperty<>();
    }
    
    /**
     * arg constructor
     * @param image logo
     * @param string name
     * @param cloud cloud-platform
     * @param host host name of the server
     * @param serverPort port of the server
     * @param nodePort port of the current application
     * @param appType type of the application
     * @param performance TODO
     * @param speed TODO
     * @throws java.net.UnknownHostException
     * @throws java.rmi.RemoteException
     */
    public CloudBookNode(Image image, String string, Cloud cloud, String host, int serverPort, int nodePort, int appType, int performance, int speed) throws UnknownHostException, RemoteException {
        platform = cloud;
        serverHost = host;
        this.serverPort = serverPort;
        this.nodePort = nodePort;
        friends = new InformationBox<>();
        informations = new ArrayList<>();
        states = new Stack<>();
        mesures = new InformationBox<>();
        messages = new InformationBox<>();
        vector = new AppVector(appType, performance, speed);
        
        name = new SimpleStringProperty(string);
        logo = new SimpleObjectProperty<>(image);
        
        states.push(new State(cloud));
        engine = new Engine(this);
    }
    
    /********************************** SETTERS / GETTERS **********************************/
    
    public void addMesure(Mesure m) { mesures.push(m); }
    public void addMessage(Message m) { messages.push(m); } 
    public void addInformation(Information info) { informations.add(info); }
    public void addFriend(Friend f) { friends.push(f); }
    
    public InformationBox getFriends() { return friends; }
    public AppVector getVector() { return vector; }
    public List<Information> getInformations() { return informations; }
    public Message getMessage(int i) { return messages.get(i); }
    public InformationBox getMessages() { return messages; }
    public InformationBox getMesures() { return mesures; }
    
    /**
     * getter of the server url. Computed from serverHost and serverPort
     * @return url of the server
     */
    public String getServerUrl() { return "rmi://" + serverHost + ":" + serverPort + "/" + RemoteServer.NAME; }

    /**
     * getter
     * @return server Ip
     */
    public String getServerHost() {
        return serverHost;
    }

    /**
     * getter
     * @return server port
     */
    public int getServerPort() {
        return serverPort;
    }
    
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
        return this.platform;
    }

    /**
     * Getter
     * @return engine field
     */
    public final Engine getEngine() {
        return engine;
    }
    
    
    /* Attention !*/ 
    //public StringProperty topMessageProperty() { return topMessage.descriptionProperty(); }
    public StringProperty topMesureProperty() { return topMesure.dateProperty(); }
    public StringProperty nameProperty() { return name; }
    public ObjectProperty<Image> logoProperty() { return logo; }
    public ObjectProperty<Image> platformProperty() { return platform.iconProperty(); }
    
    
    public void setVector(int appType, int performance, int speed) { 
        vector = new AppVector(appType, performance, speed);
    }
    
    /* ATTENTION ! Mauvaise pratique ! */
    public void setInformations(List<Information> informations) { this.informations = informations; }
    public void setVector(AppVector vector) { this.vector = vector; }
    
    /*************************************** METHODES **************************************/
    
    /**
     * updates the cloud-platform of the current application
     * @param c new cloud-platform
     */
    public void majCurrentState(Cloud c) {
        State currentState = this.states.peek();
        if(!c.equals(currentState.getCloud())) {
            currentState.notCurrentAnymore();
            states.push(new State(c));
        }    
    }
    
    public void save() throws IOException {
        _name = name.get();
        _logo = logo.get();
        friends.saveProperties();
        for(Information info : informations)
            info.saveProperties();
        mesures.saveProperties();
        messages.saveProperties();
        for(State s : states)
            s.saveProperties();
        ObservableFileList.INSTANCE.add(this, name.get() + ".ser");
    }
    
    public static CloudBookNode load(String fileName) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
        CloudBookNode res = (CloudBookNode) ois.readObject();
        res.name = new SimpleStringProperty(res._name);
        res.friends.restoreProperties();
        for(Information info : res.informations)
            info.restoreProperties();
        res.mesures.restoreProperties();
        res.messages.restoreProperties();
        for(State s : res.states)
            s.restoreProperties();
        res.logo = new SimpleObjectProperty<>(res._logo);
        res.engine = new Engine(res);
        return res;
    }
}
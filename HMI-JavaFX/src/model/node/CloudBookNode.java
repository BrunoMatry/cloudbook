package model.node;

import model.network.interfaces.Information;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
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
    protected Message topMessage; //message to be shown at the first place
    protected Mesure topMesure; //mesure to be shown at the first place
    protected Cloud platform; //current platform of the application
    
    protected List<Friend> friends;
    protected List<Information> informations;
    protected Stack<State> states; //history of the cloud platforms
    protected List<Mesure> mesures; //list of all the mesures
    protected List<Message> messages; //list of the received messages
    protected AppVector vector;
    
    //host of the server
    protected String serverHost;
    
    //port of the server
    protected int serverPort;
    
    /* Proprietes non serialisables */
    protected transient StringProperty name;
    protected transient ObjectProperty<Image> logo;
    
    /************************************ CONSTRUCTEURS ************************************/
    
    /**
     * default constructor
     */
    protected CloudBookNode() {
        topMesure = new Mesure();
        topMessage = new Message();
        
        friends = new ArrayList<>();
        informations = new ArrayList<>();
        states = new Stack<>();
        mesures = new ArrayList<>();
        messages = new ArrayList<>();    
        vector = new AppVector(0,0,0);
        
        name = new SimpleStringProperty();
        logo = new SimpleObjectProperty<>();
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
        
        topMesure = new Mesure(); /* Ne pas utiliser ces constructeurs !!!!!! */
        topMessage = new Message(); /* Ne pas utiliser ces constructeurs !!!!!! */
        platform = cloud;
        
        friends = new ArrayList<>();
        informations = new ArrayList<>();
        states = new Stack<>();
        mesures = new ArrayList<>();
        messages = new ArrayList<>();
        vector = new AppVector(appType, performance, speed);
        
        name = new SimpleStringProperty(string);
        logo = new SimpleObjectProperty<>(image);
        
        states.push(new State(cloud));
    }
    
    /********************************** SETTERS / GETTERS **********************************/
    
    public void addMesure(Mesure m) { mesures.add(m); }
    public void addMessage(Message m) { messages.add(m); } 
    public void addInformation(Information info) { informations.add(info); }
    public void addFriend(Friend f) { friends.add(f); }
    
    public List<Friend> getFriends() { return friends; }
    public AppVector getVector() { return vector; }
    public List<Information> getInformations() { return informations; }
    public Message getMessage(int i) { return messages.get(i); }
    
    /**
     * getter of the server url. Computed from serverHost and serverPort
     * @return url of the server
     */
    public String getServerUrl() { return "rmi://" + serverHost + ":" + serverPort + "/" + RemoteServer.NAME; }
    
    /* Attention !*/ 
    public StringProperty topMessageProperty() { return topMessage.descriptionProperty(); }
    public StringProperty topMesureProperty() { return topMesure.dateProperty(); }
    public StringProperty nameProperty() { return name; }
    public ObjectProperty<Image> logoProperty() { return logo; }
    public ObjectProperty<Image> platformProperty() { return platform.iconProperty(); }
    
    
    public void setVector(int appType, int performance, int speed) { 
        vector = new AppVector(appType, performance, speed);
    }
    
    /* ATTENTION ! Mauvaise pratique ! */
    public void setFriends(List<Friend> friends) { this.friends = friends; }
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
        for(Friend f : friends)
            f.saveProperties();
        for(Information info : informations)
            info.saveProperties();
        for(Mesure mes : mesures)
            mes.saveProperties();
        for(Message msg : messages)
            msg.saveProperties();
        for(State s : states)
            s.saveProperties();
        topMessage.saveProperties();
        topMesure.saveProperties();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("node_save.ser"))) {
            oos.writeObject(this);
            oos.flush();
        }
    }
    
    public static CloudBookNode load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("node_save.ser"));
        CloudBookNode res = (CloudBookNode) ois.readObject();
        //res.logo.set(res._logo);
        res.name = new SimpleStringProperty(res._name);
        for(Friend f : res.friends)
            f.restoreProperties();
        for(Information info : res.informations)
            info.restoreProperties();
        for(Mesure mes : res.mesures)
            mes.restoreProperties();
        for(Message msg : res.messages)
            msg.restoreProperties();
        for(State s : res.states)
            s.restoreProperties();
        res.topMessage.restoreProperties();
        res.topMesure.restoreProperties();
        return res;
    }
}
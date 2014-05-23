package model.engine;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.friendmanager.FriendManager;
import model.friendmanager.IFriendManager;
import model.network.interfaces.Information;
import model.monitoring.IMonitoring;
import model.monitoring.Monitoring;
import model.network.implementation.Network;
import model.network.interfaces.RemoteClient;
import model.request.IRequestManager;
import model.node.MyNode;
import model.request.RequestManager;
import model.network.interfaces.Sendable;
import model.node.AppVector;
import model.node.Message;
import model.node.friend.Friend;
import model.request.Request;

public class Engine extends Thread implements IEngine {
    
    private final static long nbSecUpdate = 3;
    private final static long nbSecSave = 20;
    private final static long nbSecShare = 5;
    
    protected IRequestManager requestManager;
    protected IFriendManager friendManager;
    protected Monitoring monitoring;
    protected MyNode node;
    protected RemoteClient network;
    protected boolean stopFlag;

    /* Getters and setters */
    public IFriendManager getFriendManager() { return friendManager; }
    public IMonitoring getMonitoring() { return monitoring; }
    public RemoteClient getNetwork() { return network; }
    public MyNode getNode() { return node; }
    public IRequestManager getRequestManager() { return requestManager; }
    
    @Override
    public void setNetwork(RemoteClient network) { this.network = network; }
    
    /**
     * Constructor
     * Initializes all the modules of the application
     * @param n current instance of MyNode
     * @throws RemoteException remote access problem
     * @throws UnknownHostException host unknown
     */
    public Engine(MyNode n) throws UnknownHostException, RemoteException {
        stopFlag = false;
        monitoring = new Monitoring(this);
        node = n;
        network = new Network(InetAddress.getLocalHost().getHostAddress(),
                node.getNodePort(),
                this);
        friendManager = new FriendManager(node);
        requestManager = new RequestManager(friendManager, this);
    }

    /**
     * Starts the communication with the network
     */
    @Override
    public void run() {
        // [Q] Pourquoi ce test ?
        if(monitoring != null && network != null) {
            try {
                network.connect(node.getServerHost() + ":" + node.getServerPort());
            } catch (RemoteException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
            monitoring.start();
        }
        stopFlag = false;
        int cpt = Integer.MIN_VALUE;
        while(!stopFlag) {
            try {
                sleep(1000);
                cpt++;
                if(cpt % nbSecUpdate == 0)
                    updateInformation();
                if(cpt % nbSecShare == 0)
                    shareMesures(3, 3);
                if(cpt % nbSecSave == 0)
                    save();
                if(cpt >= Integer.MAX_VALUE)
                    cpt = Integer.MIN_VALUE;
            } catch (InterruptedException | UnknownHostException | RemoteException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            network.disconnect();
        } catch (RemoteException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        monitoring.setStopFlag(true);
    }
    
    @Override
    public void save() throws IOException {
        try {
            node.save();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setInformation(Information info) {
        node.addInformation(info);
    }

    @Override
    public void handleRequest(Sendable req) {
        requestManager.handleRequest(req);
    }
    
    /**
     * Update the current node with information from monitoring
     */
    protected void updateInformation(){
        monitoring.pushInformation();
    }

    /**
     * Share mesure with friends
     * @param nbMesures number of mesures to share
     * @param nbFriends max number of friends to target
     * @throws UnknownHostException unknown host
     * @throws RemoteException remote access problem
     */
    protected void shareMesures(int nbMesures, int nbFriends) throws UnknownHostException, RemoteException {
        // Get the last mesures from the node (can be modified)
        ArrayList<Information> mesures = node.getMesures().getLastValues(nbMesures);
        
        //generation of corresponding messages
        List<Message> messages = makeMessages(mesures);
        
        // Creating requests
        List<Request> requests = requestManager.createRequests(messages);
        
        //Retriving of all members to which the request is to be sent
        List<Friend> friends = friendManager.getRelevantFriends(nbFriends);
        
        if(friends == null || friends.isEmpty())
            broadcast(requests);
        else
            send(requests, friends);
    }
    
    /**
     * Generates a list of messages containing the specified information
     * @param informationList list of information to be sent
     * @return list of messages containing the specified information
     * @throws UnknownHostException
     */
    protected List<Message> makeMessages(List<Information> informationList) throws UnknownHostException {
        List<Message> res = new ArrayList<>();
        String id = node.getMemberId();
        AppVector vector = node.getVector();
        for(Information info : informationList) {
            Message msg = new Message(id, vector, info, true);
            res.add(msg);
        }
        return res;
    }

    /**
     * Sends some requests to some friends
     * @param requests requests to be sent
     * @param friends friends to which the requests are to be sent
     * @throws RemoteException required for RMI
     */
    protected void send(List<Request> requests, List<Friend> friends) throws RemoteException {
        for(Friend f : friends) {
            String[] temp = f.getId().split("@");
            for(Request req : requests)
                network.send(req, temp[1]);
        }
    }
    
    /**
     * Sends a list of requests to all the members of the network
     * @param requests list of requests to be broadcasted
     * @throws RemoteException required for RMI
     */
    protected void broadcast(List<Request> requests) throws RemoteException {
        for(Request req : requests)
            network.broadcast(req);
    }
    
    /**
     * Getter
     * @return stopFlag field
     */
    public final boolean isStopFlag() {
        // [Q] Ne serait-il pas plus judiscieux de faire une BooleanProperty ?
        return stopFlag;
    }

    /**
     * Setter
     * @param stopFlag stopFlag field
     */
    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }
}

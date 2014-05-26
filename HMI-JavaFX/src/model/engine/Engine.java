package model.engine;

import java.io.IOException;
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
import model.node.Cloud;
import model.node.Message;
import model.node.friend.Friend;
import model.node.friend.Member;
import model.request.Request;

public class Engine implements IEngine {
    
    protected IRequestManager requestManager;
    protected IFriendManager friendManager;
    protected Monitoring monitoring;
    protected MyNode node;
    protected RemoteClient network;
    private EngineThread thread;

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
        monitoring = new Monitoring(this);
        node = n;
        network = new Network(InetAddress.getLocalHost().getHostAddress(),
                node.getNodePort(),
                this);
        friendManager = new FriendManager(node);
        requestManager = new RequestManager(friendManager, this);
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
    
    public void update(Member member) {
        friendManager.update(member);
    }
    
    /**
     * Looks for the best platform.
     * The choice is based on the current data.
     */
    public void updateState() {
        Cloud cloud = friendManager.bestCloud();
        node.majCurrentState(cloud);
    }
    
    /**
     * Starts the engine thread
     * If the thread is running, it stops it befor creating and starting a new one
     * @throws InterruptedException problem when stopping the thread
     */
    public void start() throws InterruptedException {
        if(thread != null)
            stop();
        thread = new EngineThread(this);
        thread.start();
    }
    
    /**
     * Stops the engine thread
     * @throws InterruptedException problem when waiting the thread ending
     */
    public void stop() throws InterruptedException {
        thread.setStopFlag(true);
        thread.join();
    }
}

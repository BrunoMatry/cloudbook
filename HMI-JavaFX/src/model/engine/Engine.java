package model.engine;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
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
import model.node.CloudBookNode;
import model.request.RequestManager;
import model.network.interfaces.Sendable;

/**
 *
 * @author Bruno
 */
public class Engine implements IEngine {
    
    private final static long TIME = 3000;
    
    protected IRequestManager requestManager;
    protected IFriendManager friendManager;
    protected Monitoring monitoring;
    protected CloudBookNode node;
    protected RemoteClient network;

    /* Getters and setters */
    public IFriendManager getFriendManager() { return friendManager; }
    public IMonitoring getMonitoring() { return monitoring; }
    public RemoteClient getNetwork() { return network; }
    public CloudBookNode getNode() { return node; }
    public IRequestManager getRequestManager() { return requestManager; }
    
    public void setNetwork(RemoteClient network) { this.network = network; }
    public void setNode(CloudBookNode node) throws RemoteException { this.node = node; }
    
    /**
     * Constructor
     * Initializes all the modules of the application
     * @param node current instance of CloudBookNode
     * @throws RemoteException remote access problem
     * @throws java.net.UnknownHostException host unknown
     */
    public Engine(CloudBookNode node) throws UnknownHostException, RemoteException {
        monitoring = new Monitoring(this);
        this.node = node;
        this.network = new Network(InetAddress.getLocalHost().getHostAddress(),
                node.getNodePort(),
                this);
        friendManager = new FriendManager(node);
        requestManager = new RequestManager(friendManager, this);
    }

    /**
     * Starts the communication with the network
     * @throws RemoteException connexion problem
     */
    public void start() throws RemoteException {
        if(monitoring != null && network != null) {
            network.connect(node.getServerHost() + ":" + node.getServerPort());
            monitoring.start();
        }
        while(true) {
            try {
                sleep(TIME);
                updateInformation();
            } catch (InterruptedException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void save() throws IOException {
        node.save();
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
     * Internal method - Update the current node with information from monitoring
     */
    protected void updateInformation(){
        monitoring.pushInformation();
    }
}

package model.engine;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
import model.node.Mesure;

/**
 *
 * @author Bruno
 */
public class Engine extends Thread implements IEngine {
    
    private final static long TIME = 3000;
    
    protected IRequestManager requestManager;
    protected IFriendManager friendManager;
    protected Monitoring monitoring;
    protected CloudBookNode node;
    protected RemoteClient network;
    protected boolean stopFlag;

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
        stopFlag = false;
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
     */
    @Override
    public void run() {
        if(monitoring != null && network != null) {
            try {
                network.connect(node.getServerHost() + ":" + node.getServerPort());
            } catch (RemoteException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
            monitoring.start();
        }
        while(!stopFlag) {
            try {
                sleep(TIME);
                updateInformation();
                //shareLastMesures(3);
            } catch (InterruptedException ex) {
                Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        stopFlag = false;
        try {
            network.disconnect();
        } catch (RemoteException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        monitoring.setStopFlag(true);
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

    protected void shareLastMesures(int nb) {
        // Get the last mesures from the node
        ArrayList<Mesure> mesures = node.getMesures().getLastValues(nb);
        // Recuperer les amis
        // Creer une requête
        // Envoi de la requête
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Getter
     * @return stopFlag field
     */
    public final boolean isStopFlag() {
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

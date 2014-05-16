package model.engine;

import java.io.IOException;
import java.rmi.RemoteException;
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
public final class Engine implements IEngine {
    
    public static final Engine INSTANCE = new Engine();
    
    protected IRequestManager requestManager;
    protected IFriendManager friendManager;
    protected Monitoring monitoring;
    protected CloudBookNode node;
    protected RemoteClient network;

    /**
     * Constructor
     */
    private Engine() {
        
    }
    
    public void initialize(CloudBookNode node) throws RemoteException {
        monitoring = (Monitoring)AppMounter.mountMonitoring();
        this.node = node;
        this.network = new Network(node.getServerHost(), node.getServerPort());
        friendManager = new FriendManager(node);
        requestManager = new RequestManager(friendManager);
        monitoring.start();
    }

    public CloudBookNode getNode() {
        return node;
    }
    
    @Override
    public void save() throws IOException {
        node.save();
    }

    @Override
    public void setInformation(Information info) {
        //TODO il faudra certainement encapsuler cette information
        node.addInformation(info);
    }

    @Override
    public void handleRequest(Sendable req) {
        requestManager.handleRequest(req);
    }
    
    protected void shareInformation(){
        /* TODO */
    }
    
    protected void updateInformation(){
        monitoring.pushInformation();
    }

    /**
     * Setter
     * @param node new value of the node field
     * @throws RemoteException in case of network problems
     */
    public void setNode(CloudBookNode node) throws RemoteException {
        this.node = node;
    }

    public IRequestManager getRequestManager() {
        return requestManager;
    }

    public IFriendManager getFriendManager() {
        return friendManager;
    }

    public IMonitoring getMonitoring() {
        return monitoring;
    }
    
    /**
     * getter
     * @return network attribute
     */
    public RemoteClient getNetwork() {
        return network;
    }

    /**
     * setter
     * @param network attribute
     */
    public void setNetwork(RemoteClient network) {
        this.network = network;
    }
    
}

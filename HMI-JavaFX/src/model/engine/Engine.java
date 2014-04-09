package model.engine;

import java.io.IOException;
import model.friendmanager.FriendManager;
import model.friendmanager.IFriendManager;
import model.node.Information;
import model.monitoring.IMonitoring;
import model.request.IRequestManager;
import model.node.CloudBookNode;
import model.request.RequestManager;
import model.request.Sendable;

/**
 *
 * @author Bruno
 */
public final class Engine implements IEngine {
    
    public static final Engine INSTANCE = new Engine();
    
    protected IRequestManager requestManager;
    protected IFriendManager friendManager;
    protected IMonitoring monitoring;
    protected CloudBookNode node;

    protected Engine() {
        monitoring = AppMounter.mountMonitoring();
        try {
            node = CloudBookNode.load();
        } catch (IOException | ClassNotFoundException e) {
            // Si on ne peut pas charger le noeud on en cree un nouveau
            node = AppMounter.mountNode();
        }
        friendManager = new FriendManager(node);
        requestManager = new RequestManager(friendManager);
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

    public void setNode(CloudBookNode node) {
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
}

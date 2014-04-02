package model.engine;

import java.io.IOException;
import model.friendmanager.IFriendManager;
import model.friendmanager.Information;
import model.monitoring.IMonitoring;
import model.request.IRequestManager;
import model.request.Request;
import modele.node.CloudBookNode;

/**
 *
 * @author Bruno
 */
public final class Engine implements IEngine {
    
    public static final Engine INSTANCE = new Engine();
    
    private IRequestManager requestManager;
    private IFriendManager friendManager;
    private IMonitoring monitoring;
    private CloudBookNode node;

    private Engine() {
        requestManager = AppMounter.mountRequestManager();
        friendManager = AppMounter.mountFriendManager();
        monitoring = AppMounter.mountMonitoring();
        try {
            node = CloudBookNode.load();
        } catch (IOException | ClassNotFoundException e) {
            // TODO
            // this.node = new CloudBookNode();
        }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleRequest(Request req) {
        requestManager.handleRequest(req);
    }
    
    private void shareInformation(){
        /* TODO */
    }
    
    private void updateInformation(){
        monitoring.pushInformation();
    }

    public void setNode(CloudBookNode node) {
        this.node = node;
    }
}

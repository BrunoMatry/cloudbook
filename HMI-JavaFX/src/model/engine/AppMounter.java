package model.engine;

import model.friendmanager.IFriendManager;
import model.monitoring.IMonitoring;
import model.monitoring.Monitoring;
import model.request.IRequestManager;
import modele.node.CloudBookNode;

public class AppMounter {

    public static IFriendManager mountFriendManager() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static IMonitoring mountMonitoring() {
        return new Monitoring();
    }

    public static CloudBookNode mountNode() {
        return null;
        
    }
    
    public static IRequestManager mountRequestManager() {
        return null;
    }
}

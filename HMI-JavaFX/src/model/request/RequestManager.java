package model.request;

import model.friendmanager.FriendManager;
import model.node.Information;

public class RequestManager implements IRequestManager {
    FriendManager friendManager;
    
    public RequestManager(FriendManager friendManager) {
        this.friendManager = friendManager;
    }

    /**
     * Traitement d'une requête reçue
     * @param req   request which has been received
     */
    @Override
    public void handleRequest(Request req) {
        friendManager.update(req.getSender());
        //TODO : A compléter
        System.out.println(req.getInfo()); //Pour les tests
    }

    @Override
    public Request createRequest(int target, Information data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private int computeRelevance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

package model.request;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.friendmanager.IFriendManager;
import model.node.Information;

public class RequestManager implements IRequestManager {
    
    protected IFriendManager friendManager;
    protected List<Sendable> inbox;
    
    /**
     * Constructor
     * @param friendManager responsible of all the friends
     */
    public RequestManager(IFriendManager friendManager) {
        this.friendManager = friendManager;
        inbox = new ArrayList<>();
    }

    /**
     * Handling of a received request
     * @param req   request which has been received
     */
    @Override
    public void handleRequest(Sendable req) {/*
        try {
            friendManager.update(req.getSender());
        } catch (RemoteException ex) {
            Logger.getLogger(RequestManager.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        inbox.add(req);
    }

    @Override
    public Request createRequest(int target, Information data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private int computeRelevance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IFriendManager getFriendManager() {
        return friendManager;
    }

    public List<Sendable> getInbox() {
        return inbox;
    }
}

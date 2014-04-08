package model.request;

import java.util.ArrayList;
import java.util.List;
import model.friendmanager.FriendManager;
import model.node.Information;

public class RequestManager implements IRequestManager {
    
    protected FriendManager friendManager;
    protected List<Request> inbox;
    
    /**
     * Constructor
     * @param friendManager responsible of all the friends
     */
    public RequestManager(FriendManager friendManager) {
        this.friendManager = friendManager;
        inbox = new ArrayList<>();
    }

    /**
     * Handling of a received request
     * @param req   request which has been received
     */
    @Override
    public void handleRequest(Request req) {
        friendManager.update(req.getSender());
        inbox.add(req);
    }

    @Override
    public Request createRequest(int target, Information data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private int computeRelevance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public FriendManager getFriendManager() {
        return friendManager;
    }

    public List<Request> getInbox() {
        return inbox;
    }
}

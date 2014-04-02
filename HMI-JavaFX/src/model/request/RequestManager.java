package model.request;

import model.friendmanager.Information;

/**
 * @author Bruno
 */
public class RequestManager implements IRequestManager {

    @Override
    public void handleRequest(Request req) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Request createRequest(int target, Information data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private int computeRelevance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

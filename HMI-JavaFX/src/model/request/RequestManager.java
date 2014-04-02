package model.request;

import model.friendmanager.Information;

/**
 * @author Bruno
 */
public class RequestManager implements IRequestManager {

    /**
     * TODO : add the relevant behaviour
     * @param req request which has been received
     */
    @Override
    public void handleRequest(Request req) {
        System.out.println(req.getInfo());
    }

    @Override
    public Request createRequest(int target, Information data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private int computeRelevance() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

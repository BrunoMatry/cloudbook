/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.request;

import modele.node.Information;

/**
 *
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

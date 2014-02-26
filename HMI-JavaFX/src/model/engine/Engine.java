/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.engine;

import model.friendmanager.IFriendManager;
import model.monitoring.IMonitoring;
import model.request.IRequestManager;
import model.request.Request;
import model.request.RequestManager;

/**
 *
 * @author Bruno
 */
public class Engine implements IEngine {
    private IRequestManager requestManager;
    private IFriendManager friendManager;
    private IMonitoring monitoring;
    // TODO Node

    public Engine() {
        requestManager = AppMounter.mountRequestManager();
        friendManager = AppMounter.mountFriendManager();
        monitoring = AppMounter.mountMonitoring();
    }
    
    @Override
    public void save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInformations() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void handleRequest(Request req) {
        requestManager.handleRequest(req);
    }
    
    private void shareInformation(){
        
    }
    
    private void updateInformation(){
        monitoring.pushInformation();
    }
}

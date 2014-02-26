/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.engine;

import model.friendmanager.IFriendManager;
import model.monitoring.IMonitoring;

/**
 *
 * @author Bruno
 */
public class AppMounter implements IAppMounter {

    @Override
    public IFriendManager mountFriendManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IMonitoring mountMonitoring() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mountNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

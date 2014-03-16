/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.engine;

import model.friendmanager.IFriendManager;
import model.monitoring.IMonitoring;
import model.request.IRequestManager;

/**
 *
 * @author Bruno
 */
public class AppMounter {

    public static IFriendManager mountFriendManager() {
        return null;
    }

    public static IMonitoring mountMonitoring() {
        return null;
    }

    public static void mountNode() {
        
    }
    
    public static IRequestManager mountRequestManager() {
        return null;
    }
}

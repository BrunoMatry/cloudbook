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
public interface IAppMounter {
    IFriendManager mountFriendManager();
    IMonitoring mountMonitoring();
    void mountNode();
}

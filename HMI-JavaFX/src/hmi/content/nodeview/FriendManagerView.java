/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.nodeview;

import javafx.stage.Stage;
import model.friendmanager.FriendManager;

/**
 *
 * @author Gwendal
 * 
 * panel showing the state of a member of The CloudBook
 */
public class FriendManagerView extends Stage {
    
    //model
    private FriendManager model;
    
    //representation of the current member
    private NodeView member;
    
    /**
     * update all the components of the panel
     */
    public void updateAll() {
        //TODO
    }
}

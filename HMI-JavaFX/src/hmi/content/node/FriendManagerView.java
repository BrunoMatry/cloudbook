/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import javafx.stage.Stage;
import model.friendmanager.FriendManager;

/**
 *
 * @author Gwendal
 * 
 * panel showing the state of a member of The CloudBook
 * singleton
 */
public final class FriendManagerView extends Stage {
    
    public static final FriendManagerView INSTANCE = new FriendManagerView();
    
    //model
    private FriendManager model;
    
    /**
     * private constructor of singleton
     */
    private FriendManagerView() {
        
    }
    
    /**
     * update all the components of the panel
     */
    public void updateAll() {
        //TODO
    }
    
}

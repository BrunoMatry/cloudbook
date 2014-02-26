/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.Mounter;
import javafx.scene.Scene;
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
    
    //representation of the current member
    private NodeView member;
    
    /**
     * private constructor of singleton
     */
    private FriendManagerView() {
        setTitle("Friend Manager");
        member = new NodeView();
        Scene scene = Mounter.getStandardScene(member);
        setScene(scene);
    }
    
    /**
     * update all the components of the panel
     */
    public void updateAll() {
        //TODO
    }
    
    public void buildAndShow(String title, Scene scene) {
        setTitle(title);
        setScene(scene);
        show();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.Mounter;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * Parent is customized in order to make the changing of context easier
 * @author Gwendal
 */
public class Activity extends Parent {
    
    //title to display at the top of the window
    protected String title;
    
    //standard scene corresponding to the activity
    protected Scene scene;
    
    public Activity() {
        super();
        scene = Mounter.getStandardScene(this);
    }
    
    /**
     * Launch the activity in the main window
     */
    public void launch() {
        FriendManagerView.INSTANCE.setTitle(title);
        FriendManagerView.INSTANCE.setScene(scene);
        FriendManagerView.INSTANCE.show();
    }
    
    public void setTitle(String t) {
        title = t;
    }
}

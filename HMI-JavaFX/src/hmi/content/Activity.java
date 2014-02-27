/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content;

import hmi.Launcher;
import hmi.Mounter;
import javafx.scene.Node;
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
        Launcher.STAGE.setTitle(title);
        Launcher.STAGE.setScene(scene);
        Launcher.STAGE.show();
    }
    
    /**
     * set title attribute
     * @param t : new title
     */
    public void setTitle(String t) {
        title = t;
    }
    
    /**
     * Center a node in the corresponding pane
     * @param node 
     */
    public void center(Node node) {
        node.setLayoutX((getScene().getWidth()-node.getBoundsInParent().getWidth())/2);
        node.setLayoutY((getScene().getHeight()-node.getBoundsInParent().getHeight())/2);
    }
}

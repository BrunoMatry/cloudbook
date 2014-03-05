/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content;

import hmi.Launcher;
import hmi.Mounter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Parent is customized in order to make the changing of context easier
 * @author Gwendal
 * @param <L> type of the containing layout
 */
public abstract class AActivity<L extends Pane> {
    
    private Class<L> clazzz;
    
    //title to display at the top of the window
    protected String title;
    
    //standard scene corresponding to the activity
    protected Scene scene;
    
    //Layout containing all the components of the activity
    protected L pane;
    
    public AActivity(L pane) {
        this.pane = pane;
        scene = Mounter.getStandardScene(this.pane);
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
        node.setLayoutX((pane.getScene().getWidth()-node.getBoundsInParent().getWidth())/2);
        node.setLayoutY((pane.getScene().getHeight()-node.getBoundsInParent().getHeight())/2);
    }    

    /**
     * see Pane.getChildren()
     * @return result of pane.getChildren()
     */
    public ObservableList<Node> getChildren() {
        return pane.getChildren(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

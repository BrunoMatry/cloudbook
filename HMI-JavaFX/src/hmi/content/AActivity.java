package hmi.content;

import hmi.Launcher;
import hmi.Mounter;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

/**
 * Defines the standard type of window of the application :
 * all the elements are disposed in a BorderPane (super type)
 * the background color is cornflower blue
 */
public abstract class AActivity extends BorderPane {
    
    //title to display at the top of the window
    protected String title;
    
    //standard scene corresponding to the activity
    protected Scene scene;
    
    public AActivity() {
        scene = Mounter.getStandardScene(this);
    }
    
    /**
     * Launch the current activity
     */
    public void launch() {
        Launcher.STAGE.setTitle(title);
        Launcher.STAGE.setScene(scene);
        Launcher.STAGE.show();
    }
    
    /**
     * Set title attribute
     * @param t new title
     */
    public void setTitle(String t) {
        title = t;
    }   
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

import hmi.content.AActivity;
import hmi.content.Activity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

/**
 *
 * @author Gwendal
 * Show the list of all the nodes registered on this computer
 */
public class NodeList extends Activity {

    //action to be performed at click on the OK button
    protected NodeListAction action;
    
    //list of save files
    protected SaveGroup sg;
    
    /**
     * Constructor
     * @param p parent activity
     * @param action action to be performed at click on the OK button
     */
    public NodeList(AActivity p, NodeListAction action) {
        super(p);
        this.action = action;
        this.sg = new SaveGroup();
        setCenter(sg);
    }
    
    /**
     * save file selectors container
     */
    private static final class SaveGroup extends VBox {
        
        //radio group of save buttons
        private ToggleGroup radioGroup;
        
        //radio button allowing the selection of a save file
        private List<RadioButton> saveButtons;
        
        /**
         * Constructor
         */
        public SaveGroup() {
            super();
            this.radioGroup = new ToggleGroup();
            this.saveButtons = new ArrayList<>();
            scanSaveFiles();
            getChildren().addAll(this.saveButtons);
        }
        
        /**
         * Fill the save files list
         */
        private void scanSaveFiles() {
            File folder = new File("");
            File[] files = folder.listFiles();
            for (File file : files) {
                String extName = file.getName();
                if(extName.endsWith(".ser")) {
                    String name = extName.replaceFirst(".ser", "");
                    RadioButton rb = new RadioButton(name);
                    saveButtons.add(rb);
                    rb.setToggleGroup(radioGroup);
                } 
            }
        }
    }
}

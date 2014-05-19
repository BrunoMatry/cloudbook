package hmi.home;

import hmi.content.AActivity;
import hmi.content.Activity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static sun.misc.ClassFileTransformer.add;

/**
 * Show the list of all the nodes registered on this computer
 */
public class NodeList extends Activity {

    //action to be performed at click on the OK button
    protected NodeListAction action;
    
    //list of save files
    protected SaveGroup sg;
    
    //OK button
    protected Button ok;
    
    /**
     * Constructor
     * @param p parent activity
     * @param action action to be performed at click on the OK button
     */
    public NodeList(AActivity p, NodeListAction action) {
        super(p);
        this.action = action;
        this.sg = new SaveGroup();
        setUpOkButton();
        setCenter(sg);
        setBottom(ok);
    }
    
    private void setUpOkButton() {
        this.ok = new Button("OK");
        this.ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                action.execute();
            }
        
        });
        this.ok.setAlignment(Pos.BOTTOM_CENTER);
    }
    
    /**
     * save file selectors container
     */
    private class SaveGroup extends VBox {
        
        //radio button allowing the selection of a save file
        private List<HBox> registries;
        
        /**
         * Constructor
         */
        public SaveGroup() {
            super();
            setAlignment(Pos.CENTER);
            setSpacing(10);
            this.registries = new ArrayList<>();
            scanSaveFiles();
            getChildren().addAll(this.registries);
        }
        
        /**
         * Fill the save files list
         */
        private void scanSaveFiles() {
            File folder = new File(".");
            File[] files = folder.listFiles();
            for (File file : files) {
                String extName = file.getName();
                if(extName.endsWith(".ser")) {
                    String name = extName.replaceFirst(".ser", "");
                    setUpRegistry(name);
                } 
            }
        }
        
        /**
         * Add a radio button corresponding to the save of name name
         * @param name name of the corresponding save file
         */
        private void setUpRegistry(final String name) {
            HBox registry = new HBox();
            ImageView connectionState = new ImageView();
            
            Button launcher = new Button(name);/*
            .add(rb);
            rb.setToggleGroup(radioGroup);
            rb.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent t) {
                    action.setNode(name);
                }
            });*/
        }
        
    }
}

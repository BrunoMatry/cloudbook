package hmi.home;

import hmi.button.IconFlyWeight;
import hmi.content.AActivity;
import hmi.content.HomeActivity;
import hmi.content.register.RegisterView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.node.CloudBuilder;

/**
 * Show the list of all the nodes registered on this computer
 * singleton
 */
public final class NodeList extends HomeActivity {

    public static final NodeList INSTANCE = new NodeList();
    
    //list of save files
    protected SaveGroup sg;
    
    //button allowing to add a node
    protected Button adder;
    
    //child view used to add nodes on this computer
    protected RegisterView rv;
    
    /**
     * Constructor
     */
    public NodeList() {
        super();
        title = "The CloudBook - Menu";
        this.sg = new SaveGroup();
        setUpAdderButton();
        setCenter(sg);
        setBottom(adder);
    }
    
    private void setUpAdderButton() {
        Image plus = IconFlyWeight.INSTANCE.getPlus();
        ImageView iv = new ImageView(plus);
        this.adder = new Button();
        this.adder.setGraphic(iv);
        this.adder.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                CloudBuilder cb = new CloudBuilder();
                rv.setBuilder(cb);
                cb.logoProperty().bind(rv.logoProperty());
                cb.nameProperty().bind(rv.nameProperty());
                cb.platformProperty().bind(rv.cloudProperty());
                cb.hostProperty().bind(rv.hostProperty());
                cb.serverPortProperty().bind(rv.serverPortProperty());
                cb.nodePortProperty().bind(rv.nodePortProperty());
                rv.launch();
            }
        
        });
        this.adder.setAlignment(Pos.BOTTOM_CENTER);
    }
    
    /**
     * save file selectors container
     */
    private class SaveGroup extends VBox {
        
        //radio button allowing the selection of a save file
        private List<BorderPane> registries;
        
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
            Image led = IconFlyWeight.INSTANCE.getRedLed();
            ImageView connectionState = new ImageView(led);
            Button launcher = new LoadNode(name);
            Button deleter = new DeleteNode(name);
            registry.getChildren().addAll(
                    connectionState,
                    launcher,
                    deleter
            );
        }
        
    }
}

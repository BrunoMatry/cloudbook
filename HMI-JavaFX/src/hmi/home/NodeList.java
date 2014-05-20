package hmi.home;

import hmi.button.ConnectionButton;
import hmi.button.IconFlyWeight;
import hmi.content.HomeActivity;
import hmi.content.register.RegisterView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.network.implementation.Network;
import model.node.ApplicationList;
import model.node.CloudBookNode;
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
        rv = new RegisterView(this);
        try {
            this.sg = new SaveGroup();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(NodeList.class.getName()).log(Level.SEVERE, null, ex);
        }
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
     * Getter
     * @return sg attribute
     */
    public SaveGroup getSG() {
        return sg;
    }
    
    /**
     * save file selectors container
     */
    public class SaveGroup extends VBox implements Observer {
        
        //radio button allowing the selection of a save file
        private List<BorderPane> registries;
        
        /**
         * Constructor
         * @throws java.io.IOException
         * @throws java.lang.ClassNotFoundException
         */
        public SaveGroup() throws IOException, ClassNotFoundException {
            super();
            setAlignment(Pos.CENTER_LEFT);
            setSpacing(10);
            this.registries = new ArrayList<>();
            ObservableFileList.INSTANCE.addObserver(this);
            buildRegistries();
            getChildren().addAll(this.registries);
        }
        
        /**
         * Set up the save registries
         */
        private void buildRegistries() throws IOException, ClassNotFoundException {
            for (File file : ObservableFileList.INSTANCE.getFiles()) {
                String extName = file.getName();
                if(extName.endsWith(".ser")) {
                    CloudBookNode node = CloudBookNode.load(extName);
                    ApplicationList.INSTANCE.add(node);
                    setUpRegistry(node);
                } 
            }
        }
        
        /**
         * Add a radio button corresponding to the save of name name
         * @param name name of the corresponding save file
         */
        private void setUpRegistry(final CloudBookNode node) {
            String name = node.nameProperty().get();
            BorderPane registry = new BorderPane();
            HBox box = new HBox();
            Network network = (Network)node.getEngine().getNetwork();
            ConnectionButton connectionState = new ConnectionButton(network);
            Button launcher = new LoadNode(name);
            box.getChildren().addAll(connectionState, launcher);
            Button deleter = new DeleteNode(name);
            registry.setLeft(box);
            registry.setRight(deleter);
            registry.setStyle("-fx-background-color: white;");
            registries.add(registry);
        }

        @Override
        public void update(Observable o, Object o1) {
            getChildren().removeAll(registries);
            registries = new ArrayList<>();
            try {
                buildRegistries();
            } catch (    IOException | ClassNotFoundException ex) {
                Logger.getLogger(NodeList.class.getName()).log(Level.SEVERE, null, ex);
            }
            getChildren().addAll(registries);
        }
        
    }
}

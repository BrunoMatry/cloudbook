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
import model.engine.Engine;
import model.network.implementation.Network;
import model.node.ApplicationList;
import model.node.MyNode;
import model.node.NodeBuilder;

/**
 * Show the list of all the nodes registered on this computer
 * singleton
 */
public final class MainView extends HomeActivity {

    public static final MainView INSTANCE = new MainView();
    
    
    protected SaveGroup saveGroup; //list of save files
    protected Button adder; //button allowing to add a node
    protected RegisterView registerView; //child view used to add nodes on this computer
    
    /**
     * Constructor
     */
    public MainView() {
        super();
        title = "The CloudBook - Menu";
        registerView = new RegisterView(this);
        try {
            saveGroup = new SaveGroup();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
        setUpAdderButton();
        setCenter(saveGroup);
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
                NodeBuilder cb = new NodeBuilder();
                registerView.setBuilder(cb);
                cb.logoProperty().bind(registerView.logoProperty());
                cb.nameProperty().bind(registerView.nameProperty());
                cb.platformProperty().bind(registerView.cloudProperty());
                cb.hostProperty().bind(registerView.hostProperty());
                cb.serverPortProperty().bind(registerView.serverPortProperty());
                cb.nodePortProperty().bind(registerView.nodePortProperty());
                registerView.launch();
            }
        
        });
        this.adder.setAlignment(Pos.BOTTOM_CENTER);
    }
    
    /**
     * Getter
     * @return sg attribute
     */
    public SaveGroup getSaveGroup() {
        return saveGroup;
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
            Engine tmpEngine;
            MyNode node;
            for (File file : ObservableFileList.INSTANCE.getFiles()) {
                String extName = file.getName();
                if(extName.endsWith(".ser")) {
                    node = MyNode.load(extName);
                    tmpEngine = new Engine(node);
                    if(!ApplicationList.INSTANCE.containsNode(node)) {
                        ApplicationList.INSTANCE.add(tmpEngine);
                    }
                    setUpRegistry(tmpEngine);
                } 
            }
        }
        
        /**
         * Add a radio button corresponding to the save of name name
         * @param name name of the corresponding save file
         */
        private void setUpRegistry(final Engine engine) {
            String name = engine.getNode().nameProperty().get();
            BorderPane registry = new BorderPane();
            HBox box = new HBox();
            Network network = (Network) engine.getNetwork();
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
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            getChildren().addAll(registries);
        }
        
    }
}

package hmi.home;

import hmi.button.ConnectionButton;
import hmi.button.IconFlyWeight;
import hmi.content.HomeActivity;
import hmi.content.register.RegisterView;
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
import model.node.FileEngineRelation;
import model.node.NodeBuilder;

/**
 * Show the list of all the nodes registered on this computer
 * singleton
 */
public final class MainView extends HomeActivity {

    public static final MainView INSTANCE = new MainView();
    
    protected SaveGroup saveGroup; //list of saved files
    protected Button addButton; //button allowing to add a node
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
        setBottom(addButton);
    }
    
    private void setUpAdderButton() {
        Image plus = IconFlyWeight.INSTANCE.getPlus();
        ImageView iv = new ImageView(plus);
        this.addButton = new Button();
        this.addButton.setGraphic(iv);
        this.addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                NodeBuilder cb = new NodeBuilder();
                registerView.setBuilder(cb);
                cb.logoProperty().bind(registerView.imageFileProperty());
                cb.nameProperty().bind(registerView.nameProperty());
                cb.platformProperty().bind(registerView.cloudProperty());
                cb.nodePortProperty().bind(registerView.nodePortProperty());
                cb.appTypeProperty().bind(registerView.appTypeProperty());
                cb.performanceProperty().bind(registerView.PerfromanceProperty());
                cb.speedProperty().bind(registerView.SpeedProperty());
                registerView.launch();
            }
        
        });
        this.addButton.setAlignment(Pos.BOTTOM_CENTER);
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
            FileEngineRelation.INSTANCE.addObserver(this);
            buildRegistries();
            getChildren().addAll(this.registries);
        }
        
        /**
         * Set up the save registries
         */
        private void buildRegistries() throws IOException, ClassNotFoundException {
            for (Engine e : FileEngineRelation.INSTANCE.values())
                setUpRegistry(e);
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

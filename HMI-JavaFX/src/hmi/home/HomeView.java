package hmi.home;

import hmi.Launcher;
import hmi.button.CloudBookButton;
import hmi.content.HomeActivity;
import hmi.content.monitor.MonitorView;
import hmi.content.node.NodeView;
import hmi.content.register.RegisterView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Dialogs;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.engine.Engine;
import model.monitoring.Monitoring;
import model.node.CloudBookNode;
import model.node.CloudBuilder;

/**
 * Activity launched when the application starts
 * Pattern : Singleton
 */
public final class HomeView extends HomeActivity {
    
    public static final HomeView INSTANCE = new HomeView();
    
    protected CloudBookNode node; 
    protected final HomeVBox homeVBox; //container of the launchers buttons
    protected final RegisterView registerView; //child view : modify application settings
    
    /**
     * Empty constructor
     * Initialize the container
     */
    private HomeView() {
        super();
        title = "The CloudBook - Home";
        registerView = new RegisterView(this);
        homeVBox = new HomeVBox();
        setCenter(homeVBox);
    }
    
    public void loadNode(CloudBookNode node) {
        this.node = node;
    }
    
    /**
     * Class describing the content of the container
     */
    public class HomeVBox extends VBox {
          
        private CloudBookButton friendManagButton; //friend management button
        private CloudBookButton registerButton; //"register application" button
        private CloudBookButton logsButton; //monitor logs button
        
        /**
         * Initializes and add the components
         */
        public HomeVBox() {
            super();
            setSpacing(10);
            setAlignment(Pos.CENTER);
            getChildren().addAll(getfriendManagButton(), getRegisterButton(), getLogsButton());
        }

        /**
         * Getter
         * If friendManagButton is null, it is initialized
         * @return fManagButton attribute 
         */
        public final CloudBookButton getfriendManagButton() {
            if(friendManagButton == null) {
                friendManagButton = new CloudBookButton("Friend management");
                friendManagButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        if(Engine.INSTANCE.getNode() != null) {
                            bindMessage();
                            bindMesure();
                            bindState();
                            NodeView.INSTANCE.launch();
                        } else {
                            Dialogs.showInformationDialog(Launcher.STAGE,
                                    "There isn't a profile yet",
                                    "Profile error",
                                    "No Profile");
                        }
                    }
                });
            }
            return friendManagButton;
        }
        
        public void bindMessage() {
            Text txtMsg = (Text)NodeView.INSTANCE.getMessage().getView();
            StringProperty msgp = Engine.INSTANCE.getNode().getMessages().descriptionProperty();
            txtMsg.textProperty().bind(msgp);
        }
        
        public void bindMesure() {
            Text txtM = (Text)NodeView.INSTANCE.getMesures().getView();
            StringProperty mp = Engine.INSTANCE.getNode().getMesures().descriptionProperty();
            txtM.textProperty().bind(mp);
        }
        
        public void bindState() {
            ImageView iv = (ImageView)NodeView.INSTANCE.getState().getView();
            ObjectProperty<Image> cloud = Engine.INSTANCE.getNode().platformProperty();
            iv.imageProperty().bind(cloud);
        }

        /**
         * getter
         * if registerButton is null, it is initialized
         * @return registerButton attribute 
         */
        public final CloudBookButton getRegisterButton() {
            if(registerButton == null) {
                registerButton = new CloudBookButton("Register an application");
                registerButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        CloudBuilder cb = new CloudBuilder();
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
            }
            return registerButton;
        }
        
        /**
         * constructs the logs button if not defined yet.
         * Otherwise, simply returns it.
         * @return functional logs view button
         */
        public final CloudBookButton getLogsButton() {
            if(logsButton == null) {
                logsButton = new CloudBookButton("Monitor logs");
                logsButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        Monitoring model = (Monitoring)Engine.INSTANCE.getMonitoring();
                        MonitorView view = MonitorView.INSTANCE;
                        view.logsTextProperty().bind(model.logsProperty());
                        view.launch();
                    }
                });
            }
            return logsButton;
        }   
    }
}
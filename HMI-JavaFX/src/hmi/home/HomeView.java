/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import model.node.CloudBuilder;

/**
 * Activity launched when the application starts
 * singleton
 * @author Gwendal
 */
public final class HomeView extends HomeActivity {   
    public static final HomeView INSTANCE = new HomeView();
    
    /*
    protected MenuBar menuBar;
    protected TabList tabList;
    */
    
    //container of the launchers buttons
    private final HomeVBox hVBox;
    
    /**
     * initialize the container
     */
    private HomeView() {
        super();
        title = "The CloudBook - Home";
        /* Initialisation des attributs prives */
        /*
        this.tabList = Mounter.getTabList();
        this.menuBar = new MenuBar();
                */
        hVBox = new HomeVBox();
        setCenter(hVBox);
    }
    
    /**
     * Class describing the content of the container
     */
    public class HomeVBox extends VBox {
        
        //friend management button
        private CloudBookButton fManagButton;
        
        //"register application" button
        private CloudBookButton registerButton;
        
        //monitor logs button
        private CloudBookButton logsButton;
        
        /**
         * initializes and add the components
         */
        public HomeVBox() {
            super();
            setSpacing(10);
            setAlignment(Pos.CENTER);
            getChildren().addAll(
                    getfManagButton(),
                    getRegisterButton(),
                    getLogsButton()
            );
        }

        /**
         * getter
         * if fManagButton is null, it is initialized
         * @return fManagButton attribute 
         */
        public final CloudBookButton getfManagButton() {
            if(fManagButton == null) {
                fManagButton = new CloudBookButton("Friend management");
                fManagButton.setOnAction(new EventHandler<ActionEvent>() {

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
            return fManagButton;
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
                        RegisterView.INSTANCE.setBuilder(cb);
                        cb.logoProperty().bind(RegisterView.INSTANCE.logoProperty());
                        cb.nameProperty().bind(RegisterView.INSTANCE.nameProperty());
                        cb.platformProperty().bind(RegisterView.INSTANCE.cloudProperty());
                        cb.hostProperty().bind(RegisterView.INSTANCE.hostProperty());
                        cb.portProperty().bind(RegisterView.INSTANCE.portProperty());
                        RegisterView.INSTANCE.launch();
                    }
            
                });
            }
            return registerButton;
        }
        
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

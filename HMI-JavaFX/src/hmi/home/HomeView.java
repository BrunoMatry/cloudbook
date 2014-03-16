/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

import hmi.button.CloudBookButton;
import hmi.content.HomeActivity;
import hmi.content.node.NodeView;
import hmi.content.register.RegisterView;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.engine.Engine;
import modele.node.Cloud;
import modele.node.CloudBuilder;
import modele.node.Message;
import modele.node.Mesure;

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
    private HomeVBox hVBox;
    
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
        
        /**
         * initializes and add the components
         */
        public HomeVBox() {
            super();
            setSpacing(10);
            setAlignment(Pos.CENTER);
            getChildren().addAll(
                    getfManagButton(),
                    getRegisterButton()
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
                        }
                    }
            
                });
            }
            return fManagButton;
        }
        
        public void bindMessage() {
            Text txtMsg = (Text)NodeView.INSTANCE.getMessage().getView();
            ObjectProperty<Message> msgp = Engine.INSTANCE.getNode().topMessageProperty();
            txtMsg.textProperty().bind(msgp.get().descriptionProperty());
        }
        
        public void bindMesure() {
            Text txtM = (Text)NodeView.INSTANCE.getMesures().getView();
            ObjectProperty<Mesure> mp = Engine.INSTANCE.getNode().topMesureProperty();
            txtM.textProperty().bind(mp.get().descriptionProperty());
        }
        
        public void bindState() {
            ImageView iv = (ImageView)NodeView.INSTANCE.getState().getView();
            ObjectProperty<Cloud> cloud = Engine.INSTANCE.getNode().platformProperty();
            iv.imageProperty().bind(cloud.get().iconProperty());
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
                        cb.logoProperty().bind(RegisterView.INSTANCE.logoProperty());
                        cb.nameProperty().bind(RegisterView.INSTANCE.nameProperty());
                        cb.platformProperty().bind(RegisterView.INSTANCE.cloudProperty());
                        RegisterView.INSTANCE.launch();
                    }
            
                });
            }
            return registerButton;
        }
        
    }
}
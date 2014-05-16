/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

import hmi.button.CloudBookButton;
import hmi.content.HomeActivity;
import hmi.content.register.RegisterView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import model.node.CloudBuilder;

/**
 *
 * @author Gwendal
 * singleton
 * Menu in which the user can load an existing node, create or delete a new one 
 */
public final class MenuView extends HomeActivity {
    public static final MenuView INSTANCE = new MenuView();
    
    //container of the buttons
    private final MenuVBox mVBox;
    
    /**
     * Initializes the container
     */
    private MenuView() {
        super();
        title = "The CloudBook - Menu";
        /* Initialisation des attributs prives */
        /*
        this.tabList = Mounter.getTabList();
        this.menuBar = new MenuBar();
                */
        mVBox = new MenuVBox();
        setCenter(mVBox);
    }
    
    /**
     * Class describing the content of the container
     */
    public class MenuVBox extends VBox {
        
        //Load button
        private CloudBookButton loadButton;
        
        //"register application" button
        private CloudBookButton registerButton;
        
        //Delete button
        private CloudBookButton deleteButton;
        
        /**
         * initializes and add the components
         */
        public MenuVBox() {
            super();
            setSpacing(10);
            setAlignment(Pos.CENTER);
            getChildren().addAll(
                    getLoadButton(),
                    getRegisterButton(),
                    getDeleteButton()
            );
        }

        /**
         * getter
         * if loadButton is null, it is initialized
         * @return loadButton attribute 
         */
        public final CloudBookButton getLoadButton() {
            if(loadButton == null) {
                loadButton = new CloudBookButton("Load...");
                loadButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        //launch the node list activity
                    }
            
                });
            }
            return loadButton;
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
        
        /**
         * constructs the delete button if not defined yet.
         * Otherwise, simply returns it.
         * @return functional delete button
         */
        public final CloudBookButton getDeleteButton() {
            if(deleteButton == null) {
                deleteButton = new CloudBookButton("Delete...");
                deleteButton.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        //launch node list activity
                    }
                
                });
            }
            return deleteButton;
        }
        
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

import hmi.Mounter;
import hmi.bar.MenuBar;
import hmi.button.CloudBookButton;
import hmi.content.HomeActivity;
import hmi.content.node.NodeView;
import hmi.content.register.RegisterView;
import hmi.tab.TabList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

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
    private HomeVBox hVBox;
    
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
    
    public class HomeVBox extends VBox {
        
        private CloudBookButton fManagButton;
        private CloudBookButton registerButton;
        
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
                        NodeView.INSTANCE.launch();
                    }
            
                });
            }
            return fManagButton;
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
                        RegisterView.INSTANCE.launch();
                    }
            
                });
            }
            return registerButton;
        }
        
    }
}

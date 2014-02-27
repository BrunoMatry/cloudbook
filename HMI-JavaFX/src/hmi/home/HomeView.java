/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.home;

import hmi.Mounter;
import hmi.bar.MenuBar;
import hmi.button.CloudBookButton;
import hmi.content.Activity;
import hmi.content.node.NodeView;
import hmi.content.register.RegisterView;
import hmi.tab.TabList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Activity launched when the application starts
 * singleton
 * TODO : use a builder ?
 * TODO : add a home button to the standard activities
 * TODO : class HomeActivity
 * @author Gwendal
 */
public final class HomeView extends Activity {
    
    public static final HomeView INSTANCE = new HomeView();
    
    protected MenuBar menuBar;
    protected TabList tabList;
    protected CloudBookButton fManagButton;
    protected CloudBookButton registerButton;
    
    private HomeView() {
        super();
        title = "The CloudBook - Home";
        /* Initialisation des attributs prives */
        this.tabList = Mounter.getTabList();
        this.menuBar = new MenuBar();
        setUpFManagButton();
        setUpRegisterButton();
        
        ButtonStack sp = new ButtonStack();
        center(sp);
        sp.setSpacing(10);
        getChildren().add(tabList);
        getChildren().add(menuBar);
        sp.getChildren().addAll(fManagButton, registerButton);
        getChildren().add(sp);
    }
    
    /**
     * configure fManagButton
     */
    private void setUpFManagButton() {
        fManagButton = new CloudBookButton("Friend management");
        fManagButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                NodeView.INSTANCE.launch();
            }
            
        });
    }
    
    /**
     * configure fManagButton
     */
    private void setUpRegisterButton() {
        registerButton = new CloudBookButton("Register an application");
        registerButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                RegisterView.INSTANCE.launch();
            }
            
        });
    }
    
    /**
     * set up the sp attribute
     */
    private void setUpStackPane() {
        
    }
}

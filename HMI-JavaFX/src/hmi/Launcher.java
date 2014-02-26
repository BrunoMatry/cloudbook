
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi;

import hmi.bar.MenuBar;
import hmi.button.CloudBookButton;
import hmi.content.node.NodeView;
import hmi.tab.TabList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Bruno
 */
public class Launcher extends Application {
    private MenuBar menuBar;
    private TabList tabList;
    private CloudBookButton fManagButton;

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        /* Initialisation des attributs prives */
        this.tabList = Mounter.getTabList();
        this.menuBar = new MenuBar();
        setUpFManagButton();
        
        Group root = new Group();
        root.getChildren().add(tabList);
        root.getChildren().add(menuBar);
        root.getChildren().add(fManagButton);
    
        Scene scene = Mounter.getStandardScene(root);
        
        primaryStage.setTitle("The CloudBook");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * configure fManagButton
     */
    private void setUpFManagButton() {
        fManagButton = new CloudBookButton("Friend management");
        fManagButton.setLayoutX(250);
        fManagButton.setLayoutY(250);
        fManagButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                NodeView.INSTANCE.launch();
            }
            
        });
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi;

import hmi.bar.HMIMenuBar;
import hmi.button.CloudBookButton;
import hmi.content.nodeview.FriendManagerView;
import hmi.tab.HMITabList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Bruno
 */
public class HMICloudbook extends Application {
    private HMIMenuBar menuBar;
    private HMITabList tabList;
    private CloudBookButton fManagButton;

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        /* Initialisation des attributs prives */
        this.tabList = HMIMounter.getTabList();
        this.menuBar = new HMIMenuBar();
        setUpFManagButton();
        
        Group root = new Group();
        root.getChildren().add(tabList);
        root.getChildren().add(menuBar);
        root.getChildren().add(fManagButton);
    
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.CORNFLOWERBLUE);
        
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
                FriendManagerView.INSTANCE.show();
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

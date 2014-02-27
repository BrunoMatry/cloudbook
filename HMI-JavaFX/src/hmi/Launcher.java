
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi;

import hmi.home.HomeView;
import hmi.bar.MenuBar;
import hmi.button.CloudBookButton;
import hmi.content.node.NodeView;
import hmi.content.register.RegisterView;
import hmi.tab.TabList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Bruno
 */
public class Launcher extends Application {
    
    //stage corresponding to the main window
    public static Stage STAGE;

    /**
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        STAGE = primaryStage;
        HomeView.INSTANCE.launch();
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

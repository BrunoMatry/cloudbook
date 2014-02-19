
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi;

import hmi.bar.HMIMenuBar;
import hmi.tab.HMITabList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Bruno
 */
public class HMICoudbook extends Application {
    private HMIMenuBar menuBar;
    private HMITabList tabList;

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
        
        Group root = new Group();
        root.getChildren().add(tabList);
        root.getChildren().add(menuBar);
    
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.CORNFLOWERBLUE);
        
        primaryStage.setTitle("Cloudbook");
        primaryStage.setScene(scene);
        primaryStage.show();
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

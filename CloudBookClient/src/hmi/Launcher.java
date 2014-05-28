package hmi;

import hmi.home.MainView;
import javafx.application.Application;
import javafx.stage.Stage;
import model.engine.Engine;
import model.node.FileEngineRelation;

/**
 * Launches the application.
 * @author Gwendal
 */
public class Launcher extends Application {
    
    //stage corresponding to the main window
    public static Stage STAGE;

    /**
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        STAGE = primaryStage;
        MainView.INSTANCE.launch();
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

    /**
     * Saves the registered applications state before closing the program.
     * @throws Exception 
     */
    @Override
    public void stop() throws Exception {
        for(Engine engine : FileEngineRelation.INSTANCE.values())
            engine.save();
        super.stop();
    }
}
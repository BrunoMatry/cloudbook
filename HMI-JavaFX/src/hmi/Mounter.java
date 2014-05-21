package hmi;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Mounter {
    
    /**
     * 
     * @param root : parent of the scene
     * @return a scene which design matches the standards
     */
    public static Scene getStandardScene(Parent root) {
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.CORNFLOWERBLUE);
        return scene;
    }
}

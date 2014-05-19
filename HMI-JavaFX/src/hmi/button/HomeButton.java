package hmi.button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Button with a home image
 */
public class HomeButton extends Button {
    
    /**
     * Construct a button with a home image
     */
    public HomeButton() {
        super();
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getHome());
        setGraphic(icon);
    }
}

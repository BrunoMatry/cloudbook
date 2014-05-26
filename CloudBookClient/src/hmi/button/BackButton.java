package hmi.button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Button with a back arrow
 */
public class BackButton extends Button {
    
    /**
     * Constructor
     * Put a back arrow image on the button
     */
    public BackButton() {
        super();
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getBackArrow());
        setGraphic(icon);
    }
}

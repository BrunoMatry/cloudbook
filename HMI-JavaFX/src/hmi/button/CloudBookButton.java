package hmi.button;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/**
 * Customized button with a cloud icon
 */
public class CloudBookButton extends Button {
    
    /**
     * Put a cloud image on the button
     */
    public CloudBookButton() {
        super();
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getCloud());
        setGraphic(icon);
    }
    
    /**
     * same as default constructor but add a label to the button
     * @param text : label of the button
     */
    public CloudBookButton(String text) {
        super(text);
        ImageView icon = new ImageView();
        icon.setImage(IconFlyWeight.INSTANCE.getCloud());
        setGraphic(icon);
    }
}

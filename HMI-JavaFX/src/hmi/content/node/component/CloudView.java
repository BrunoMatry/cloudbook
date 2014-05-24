/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import controller.CloudImageProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gwendal
 */
public class CloudView extends ImageView {
    
    private CloudImageProperty cloudImage;
    public CloudImageProperty cloudImageProperty() {
        return cloudImage;
    }

    public CloudView() {
        super();
        cloudImage = new CloudImageProperty();
        imageProperty().bind(cloudImage);
    }

    public CloudView(Image image) {
        super(image);
        cloudImage = new CloudImageProperty(image);
        imageProperty().bind(cloudImage);
    }
    
    
    
}

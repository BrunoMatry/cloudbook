package modele.node;

import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

/**
 * Enum Cloud
 * 
 * Note : cet enum sera bien evidement amene a etre complete 
 * voir a etre remplace par une autre forme de structure de donnees.
 */
public enum Cloud implements Serializable {
    GDRIVE("file:res/google.png"),
    SKYDRIVE("file:res/skydrive.png"),
    DROPBOX("file:res/dropbox.png"),
    DEFAULT("file:res/default_logo.png");
    
    protected transient ObjectProperty<Image> icon;
    public ObjectProperty<Image> iconProperty() {
        return icon;
    }
    
    Cloud(String path) {
        icon = new SimpleObjectProperty<>();
        icon.set(new CloudImage(path, name()));
    }

    public ObjectProperty<Image> getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return name();
    }
    
    public class CloudImage extends Image {

        private final String name;
        
        public CloudImage(String string, String name) {
            super(string);
            this.name  = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
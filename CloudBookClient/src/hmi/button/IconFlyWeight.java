package hmi.button;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 * fly-weight for images that are displayed in the application.
 * All the getters instanciate the corresponding image only once.
 * Singleton.
 */
public final class IconFlyWeight {
    
    public static final IconFlyWeight INSTANCE = new IconFlyWeight();
    
    /**
     * singleton constructor
     */
    private IconFlyWeight() {
        relation = new HashMap<>();
    }
    
    private Image backArrow;
    private Image cloud;
    private Image defaultLogo;
    private Image home;
    private Image greenLed;
    private Image redLed;
    private Image trash;
    private Image plus;
    private Image google;
    private Image amazon;
    private Image windows;
    private Image defaultCloud;
    
    //Relation between image file name and its image content
    private final Map<String, Image> relation;
    
    /**
     * Retrieves an image using its file name.
     * @param name name of the image file.
     * @return The image which is in the sepcified file.
     */
    public Image getByName(String name) {
        if(relation.containsKey(name))
            return relation.get(name);
        else {
            Image image = new Image("file:res/" + name);
            relation.put(name, image);
            return image;
        }
    }
   
    /**
     * Getter
     * @return the back arrow image.
     */
    public Image getBackArrow() {
        if(backArrow == null)
            backArrow = new Image("file:res/back_button.png");
        return backArrow;
    }

    /**
     * Getter
     * @return the back cloud image.
     */
    public Image getCloud() {
        if(cloud == null)
            cloud = new Image("file:res/cloud_picture.png");
        return cloud;
    }
    
    /**
     * Getter
     * @return the default logo for an application.
     */
    public Image getDefaultLogo() {
        if(defaultLogo == null)
            defaultLogo = new Image("file:res/default_app.png");
        return defaultLogo;
    }

    /**
     * Getter
     * @return the home image.
     */
    public Image getHome() {
        if(home == null)
            home = new Image("file:res/home.png");
        return home;
    }
    
    /**
     * Getter
     * @return greenLed attribute 
     */
    public Image getGreenLed() {
        if(greenLed == null)
            greenLed = new Image("file:res/greenled.png");
        return greenLed;
    }
    
    /**
     * Getter
     * @return redLed attribute 
     */
    public Image getRedLed() {
        if(redLed == null)
            redLed = new Image("file:res/redled.png");
        return redLed;
    }
    
    /**
     * Getter
     * @return trash attribute
     */
    public Image getTrash() {
        if(trash == null)
            trash = new Image("file:res/trash.png");
        return trash;
    }
    
    /**
     * Getter
     * @return plus attribute
     */
    public Image getPlus() {
        if(plus == null)
            plus = new Image("file:res/adder.png");
        return plus;
    }
    
    /**
     * Getter
     * @return google icon
     */
    public Image getGoogle() {
        if(google == null)
            google = new Image("file:res/google.png");
        return google;
    }
    
    /**
     * Getter
     * @return amazon icon
     */
    public Image getAmazon() {
        if(amazon == null)
            amazon = new Image("file:res/amazon.png");
        return amazon;
    }
    
    /**
     * Getter
     * @return windows icon
     */
    public Image getWindows() {
        if(windows == null)
            windows = new Image("file:res/windows.png");
        return windows;
    }
    
    /**
     * Getter
     * @return defaultCloud icon
     */
    public Image getDefaultCloud() {
        if(defaultCloud == null)
            defaultCloud = new Image("file:res/default_logo.png");
        return defaultCloud;
    }
}

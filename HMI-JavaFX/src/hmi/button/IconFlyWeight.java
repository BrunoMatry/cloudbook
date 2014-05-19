package hmi.button;

import javafx.scene.image.Image;

/**
 * fly-weight for images that are displayed in the application
 * singleton
 */
public final class IconFlyWeight {
    
    public static final IconFlyWeight INSTANCE = new IconFlyWeight();
    
    /**
     * singleton constructor
     */
    private IconFlyWeight() {
        
    }
    
    private Image backArrow;
    private Image cloud;
    private Image exit;
    private Image defaultLogo;
    private Image home;
    private Image greenLed;
    private Image redLed;
   
    public Image getBackArrow() {
        if(backArrow == null)
            backArrow = new Image("file:res/back_button.png");
        return backArrow;
    }

    public Image getCloud() {
        if(cloud == null)
            cloud = new Image("file:res/cloud_picture.png");
        return cloud;
    }
    
    public Image getDefaultLogo() {
        if(defaultLogo == null)
            defaultLogo = new Image("file:res/default_app.png");
        return defaultLogo;
    }

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
    
    /*
    public Image getExit() {
        if(exit == null)
            exit = new Image();
        return exit;
    }
    */
}

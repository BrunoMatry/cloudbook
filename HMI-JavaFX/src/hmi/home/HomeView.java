package hmi.home;

import hmi.container.HomeVBox;
import hmi.content.HomeActivity;
import hmi.content.register.RegisterView;
import model.node.CloudBookNode;

/**
 * Activity launched when the application starts
 * Pattern : Singleton
 */
public final class HomeView extends HomeActivity {
    
    public static final HomeView INSTANCE = new HomeView();
    
    protected CloudBookNode node; 
    protected final HomeVBox homeVBox; //container of the launchers buttons
    protected final RegisterView registerView; //child view : modify application settings
    
    /**
     * Empty constructor
     * Initialize the container
     */
    private HomeView() {
        super();
        title = "The CloudBook - Home";
        registerView = new RegisterView(this);
        homeVBox = new HomeVBox(this);
        setCenter(homeVBox);
    }
    
    /**
     * Load a node in the HomeView
     * @param node node to load
     */
    public void loadNode(CloudBookNode node) {
        this.node = node;
    }
    
    /**
     * Test if the HomeView has already a node
     * @return true if the node is initialized, false otherwise
     */
    public boolean hasNode() {
        return node != null;
    }
    
    /**
     * Getter
     * @return homeVBox atttribute
     */
    public HomeVBox getHomeVBox() {
        return homeVBox;
    }
    
    /**
     * Getter
     * @return registerView attribute
     */
    public RegisterView getRegisterView() {
        return registerView;
    }
}
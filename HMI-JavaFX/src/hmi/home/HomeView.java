package hmi.home;

import hmi.button.CloudBookButton;
import hmi.button.homeview.FriendManagementButton;
import hmi.button.homeview.RegisterButton;
import hmi.content.HomeActivity;
import hmi.content.node.NodeView;
import hmi.content.register.RegisterView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.engine.Engine;
import model.node.CloudBookNode;

/**
 * Activity launched when the application starts
 * Pattern : Singleton
 */
public final class HomeView extends HomeActivity {
    
    public static final HomeView INSTANCE = new HomeView();
    
    protected CloudBookNode node; 
    public final HomeVBox homeVBox; //container of the launchers buttons
    public final RegisterView registerView; //child view : modify application settings
    
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
    
    public void loadNode(CloudBookNode node) {
        this.node = node;
    }
    
    public boolean hasNode() {
        return node != null;
    }
    
    /**
     * Class describing the content of the container
     */
    public class HomeVBox extends VBox {
          
        private final HomeView homeView;
        private final CloudBookButton friendManagButton; //friend management button
        private final CloudBookButton registerButton; //"register application" button
        private CloudBookButton logsButton; //monitor logs button
        
        /**
         * Initializes and add the components
         * @param hv
         */
        public HomeVBox(HomeView hv) {
            super();
            homeView = hv;
            setSpacing(10);
            setAlignment(Pos.CENTER);
            
            friendManagButton = new FriendManagementButton(homeView);
            registerButton = new RegisterButton(homeView);
            
            getChildren().addAll(friendManagButton, registerButton, getLogsButton());
        }

        
        public void bindMessage() {
            Text txtMsg = (Text)NodeView.INSTANCE.getMessage().getView();
            StringProperty msgp = Engine.INSTANCE.getNode().getMessages().descriptionProperty();
            txtMsg.textProperty().bind(msgp);
        }
        
        public void bindMesure() {
            Text txtM = (Text)NodeView.INSTANCE.getMesures().getView();
            StringProperty mp = Engine.INSTANCE.getNode().getMesures().descriptionProperty();
            txtM.textProperty().bind(mp);
        }
        
        public void bindState() {
            ImageView iv = (ImageView)NodeView.INSTANCE.getState().getView();
            ObjectProperty<Image> cloud = Engine.INSTANCE.getNode().platformProperty();
            iv.imageProperty().bind(cloud);
        }

        /**
         * Getter
         * @return friendManagButton attribute 
         */
        public final CloudBookButton getfriendManagButton() {
            return friendManagButton;
        }
        
        /**
         * Getter
         * @return registerButton attribute 
         */
        public final CloudBookButton getRegisterButton() {
            return registerButton;
        }
        
        /**
         * Getter
         * @return logsButton attribute
         */
        public final CloudBookButton getLogsButton() {
            return logsButton;
        }   
    }
}
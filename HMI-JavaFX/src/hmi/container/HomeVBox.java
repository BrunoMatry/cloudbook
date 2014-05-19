package hmi.container;

import hmi.button.CloudBookButton;
import hmi.button.homeview.FriendManagementButton;
import hmi.button.homeview.LogsButton;
import hmi.button.homeview.RegisterButton;
import hmi.content.node.NodeView;
import hmi.home.HomeView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.engine.Engine;

public class HomeVBox extends VBox {
     private final HomeView homeView;
     private final CloudBookButton friendManagButton, registerButton, logsButton;
        
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
       logsButton = new LogsButton();

       getChildren().addAll(friendManagButton, registerButton, logsButton);
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

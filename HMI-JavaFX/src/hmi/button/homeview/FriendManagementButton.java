package hmi.button.homeview;

import hmi.Launcher;
import hmi.button.CloudBookButton;
import hmi.content.node.NodeView;
import hmi.home.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Dialogs;
import model.node.ApplicationList;

public class FriendManagementButton extends CloudBookButton {
    
    protected HomeView homeView;
    
    public FriendManagementButton(HomeView hv) {
        super("Friend management");
        
        homeView = hv;
        this.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if(ApplicationList.INSTANCE.getCurrentNode() != null) {
                    homeView.getHomeVBox().bindMessage();
                    homeView.getHomeVBox().bindMesure();
                    homeView.getHomeVBox().bindState();
                    NodeView.INSTANCE.launch();
                } else {
                    Dialogs.showInformationDialog(Launcher.STAGE, 
                            "There isn't a profile yet",
                            "Profile error",
                            "No Profile");
                }
            }
        });
    }
}

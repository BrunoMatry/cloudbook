package hmi.button.homeview;

import hmi.Launcher;
import hmi.button.CloudBookButton;
import hmi.content.node.NodeView;
import hmi.home.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Dialogs;
import model.engine.Engine;

public class FriendManagementButton extends CloudBookButton {
    
    protected HomeView homeView;
    
    public FriendManagementButton(HomeView hv) {
        super("Friend management");
        
        homeView = hv;
        this.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                if(Engine.INSTANCE.getNode() != null) {
                    homeView.homeVBox.bindMessage();
                    homeView.homeVBox.bindMesure();
                    homeView.homeVBox.bindState();
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

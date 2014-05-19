package hmi.button.homeview;

import hmi.button.CloudBookButton;
import hmi.home.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.node.CloudBuilder;

public class RegisterButton extends CloudBookButton {
    
    protected HomeView homeView;
    
    public RegisterButton(HomeView hv) {
        super("Register an application");
        
        homeView = hv;
        
        this.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                CloudBuilder cb = new CloudBuilder();
                homeView.registerView.setBuilder(cb);
                cb.logoProperty().bind(homeView.registerView.logoProperty());
                cb.nameProperty().bind(homeView.registerView.nameProperty());
                cb.platformProperty().bind(homeView.registerView.cloudProperty());
                cb.hostProperty().bind(homeView.registerView.hostProperty());
                cb.serverPortProperty().bind(homeView.registerView.serverPortProperty());
                cb.nodePortProperty().bind(homeView.registerView.nodePortProperty());
                homeView.registerView.launch();
            }
        });
    }
    
}

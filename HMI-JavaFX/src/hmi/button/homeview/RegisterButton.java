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
                homeView.getRegisterView().setBuilder(cb);
                cb.logoProperty().bind(homeView.getRegisterView().logoProperty());
                cb.nameProperty().bind(homeView.getRegisterView().nameProperty());
                cb.platformProperty().bind(homeView.getRegisterView().cloudProperty());
                cb.hostProperty().bind(homeView.getRegisterView().hostProperty());
                cb.serverPortProperty().bind(homeView.getRegisterView().serverPortProperty());
                cb.nodePortProperty().bind(homeView.getRegisterView().nodePortProperty());
                homeView.getRegisterView().launch();
            }
        });
    }  
}
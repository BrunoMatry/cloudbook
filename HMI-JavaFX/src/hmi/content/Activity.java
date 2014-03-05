/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content;

import hmi.button.BackButton;
import hmi.button.HomeButton;
import hmi.home.HomeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * specifies the basic content of a standard activity (i.e no-home activity)
 * @author Gwendal
 */
public class Activity extends AActivity {
    
    protected MenuHBox mHBox;
    
    //activity that must be launch if the back button is pressed
    protected AActivity prec;
    
    public Activity(AActivity p) {
        super();
        prec = p;
        mHBox = new MenuHBox();
        setTop(mHBox);
    }
    
    public class MenuHBox extends HBox {
        
        //go to the previous page
        private BackButton goBack;
        
        //go to the home-page
        private HomeButton goHome;
    
        public MenuHBox() {
            super();
            getChildren().addAll(
                    getGoBack(),
                    getGoHome()
            );
        }
        
        public final BackButton getGoBack() {
            if(goBack == null) {
                goBack = new BackButton();
                goBack.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        prec.launch();
                    }
        
                });
            }
            return goBack;
        }
        
        public final HomeButton getGoHome() {
            if(goHome == null) {
                goHome = new HomeButton();
                goHome.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        HomeView.INSTANCE.launch();
                    }
        
                });
            }
            return goHome;
        }
    }
}

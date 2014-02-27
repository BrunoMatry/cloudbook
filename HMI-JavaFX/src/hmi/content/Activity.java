/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content;

import hmi.button.BackButton;
import hmi.content.node.NodeView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Gwendal
 */
public class Activity extends AActivity {
    
    protected BackButton goBack;
    
    //activity that must be launch if the back button is pressed
    protected AActivity prec;
    
    public Activity(AActivity p) {
        prec = p;
        goBack = new BackButton();
        goBack.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                prec.launch();
            }
        
        });
        goBack.setLayoutX(0);
        goBack.setLayoutY(0);
        getChildren().add(goBack);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content;

import hmi.button.BackButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * specifies the basic content of a standard activity (i.e no-home activity)
 * @author Gwendal
 */
public class Activity extends AActivity {
    
    protected BackButton goBack;
    
    //activity that must be launch if the back button is pressed
    protected AActivity prec;
    
    public Activity(AActivity p) {
        super();
        prec = p;
        goBack = new BackButton();
        goBack.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                prec.launch();
            }
        
        });
        setTop(goBack);
    }
}

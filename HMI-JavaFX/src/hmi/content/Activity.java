/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content;

import hmi.button.BackButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;

/**
 * specifies the basic content of a standard activity (i.e no-home activity)
 * @author Gwendal
 * @param <L> layout type
 */
public class Activity<L extends Pane> extends AActivity<L> {
    
    protected BackButton goBack;
    
    //activity that must be launch if the back button is pressed
    protected AActivity prec;
    
    public Activity(AActivity p, L pane) {
        super(pane);
        prec = p;
        goBack = new BackButton();
        goBack.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                prec.launch();
            }
        
        });
        goBack.setAlignment(Pos.TOP_LEFT);
        this.pane.getChildren().add(goBack);
    }
}

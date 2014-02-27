/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.button.BackButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author Gwendal
 */
public abstract class NodeComponentView extends Activity implements ComponentView {
    
    protected BackButton goBack;
    
    public NodeComponentView() {
        super();
        goBack = new BackButton();
        goBack.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                NodeView.INSTANCE.launch();
            }
        
        });
        goBack.setLayoutX(0);
        goBack.setLayoutY(0);
        getChildren().add(goBack);
    }
    
    @Override
    public void display() {
        
    }

    @Override
    public void hide() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public abstract SummarizedView makeSummarized();
  
}

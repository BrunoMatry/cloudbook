/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.button.BackButton;
import hmi.content.Activity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView;

/**
 *
 * @author Gwendal
 */
public abstract class NodeComponentView extends Activity implements ComponentView {
    
    protected BackButton goBack;
    protected TableView table;
    
    public NodeComponentView() {
        super();
        table = new TableView();
        table.setLayoutX((getScene().getWidth()-table.getBoundsInParent().getWidth())/2);
        table.setLayoutY((getScene().getHeight()-table.getBoundsInParent().getHeight())/2);
        getChildren().add(table);
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

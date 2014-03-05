/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.content.AActivity;
import hmi.content.Activity;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

/**
 *
 * @author Gwendal
 */
public abstract class NodeComponentView extends Activity implements ComponentView {
    
    protected TableView table;
    
    public NodeComponentView(AActivity p) {
        super(p);
        table = new TableView();
        table.setLayoutX((getScene().getWidth()-table.getBoundsInParent().getWidth())/2);
        table.setLayoutY((getScene().getHeight()-table.getBoundsInParent().getHeight())/2);
        getChildren().add(table);
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

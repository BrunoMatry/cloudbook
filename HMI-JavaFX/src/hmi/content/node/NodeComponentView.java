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
    
    public NodeComponentView(AActivity p) {
        super(p);
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
    
    /**
     * Computes the SummarizedView corresponding to the current view 
     * @return the SummarizedView corresponding to the current view
     */
    public abstract SummarizedView makeSummarized();
  
}

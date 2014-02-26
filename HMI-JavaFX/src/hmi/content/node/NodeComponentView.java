/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.Mounter;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Gwendal
 */
public abstract class NodeComponentView implements ComponentView {

    //container of the current component
    protected NodeView container;
    
    //summarized version of the current view
    protected SummarizedView summarized;
    
    public NodeComponentView(NodeView parent) {
        container = parent;
    }
    
    public NodeView getContainer() {
        return container;
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
    
    public abstract SummarizedView makeSummarizedView();
  
}

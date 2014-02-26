/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node;

import hmi.Mounter;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author Gwendal
 */
public abstract class NodeComponentView extends Parent implements ComponentView {

   //Scene based on this instance of Parent
    protected Scene scene;
    
    //title to display when the view is displayed
    protected String title;
    
    public NodeComponentView() {
        scene = Mounter.getStandardScene(this);
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
    
    public abstract SummarizedView makeSummarized(NodeView parent);
    
    public String getTitle() {
        return title;
    }
  
}

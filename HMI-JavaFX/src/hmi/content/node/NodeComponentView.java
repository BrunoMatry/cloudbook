package hmi.content.node;

import hmi.content.AActivity;
import hmi.content.OneNodeActivity;

public abstract class NodeComponentView extends OneNodeActivity implements IComponentView {
    
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

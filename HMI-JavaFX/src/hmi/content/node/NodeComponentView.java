package hmi.content.node;

import hmi.content.AActivity;
import hmi.content.OneNodeActivity;
import hmi.content.node.component.tableview.StandardizedTable;
import model.network.interfaces.Information;

public abstract class NodeComponentView<I extends Information> extends OneNodeActivity implements IComponentView {
    
    //Table displaying some information about the current application node 
    protected StandardizedTable<I> table;
    
    /**
     * Constructor
     * @param p parent activity
     */
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

package hmi.content.node;

import hmi.content.AbstractActivity;
import hmi.content.OneNodeActivity;
import hmi.content.node.component.tableview.StandardizedTable;
import javafx.scene.control.TableView;
import model.network.interfaces.Information;

public abstract class NodeComponentView<I extends Information> extends OneNodeActivity implements IComponentView {
    
    //Table displaying some information about the current application node 
    protected StandardizedTable<I> table;
    
    /**
     * Constructor
     * @param p parent activity
     */
    public NodeComponentView(AbstractActivity p) {
        super(p);
    }
    
    /**
     * Computes the SummarizedView corresponding to the current view 
     * @return the SummarizedView corresponding to the current view
     */
    public abstract SummarizedView makeSummarized();
    
    /**
     * Getter
     * @return table field
     */
    public TableView getTable() {
        return table;
    }
  
}

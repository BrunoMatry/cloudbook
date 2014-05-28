package hmi.content.node.component.tableview;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * builder
 * A table view which allow to resize columns according to the number of columns in the table
 */
public abstract class TableColumnBuilder {
    
    //table to which this column belongs
    protected TableView owner; 
    
    /**
     * Constructor
     * @param owner table to which this column belongs
     */
    public TableColumnBuilder(TableView owner) {
        this.owner = owner;
    }
    
    /**
     * Sets up the column with its specified name, bound property
     * and size computed from the number of columns in the table
     * @param name label of the column
     * @param propertyName name of the property which is bound to the column
     * @param columnCount number of columns
     * @return a column of name name, bound with the property propertyName and with intelligent size
     */
    public abstract TableColumn buildColumn(String name, String propertyName, int columnCount);

}

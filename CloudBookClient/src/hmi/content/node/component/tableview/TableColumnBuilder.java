/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 *
 * @author Gwendal
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

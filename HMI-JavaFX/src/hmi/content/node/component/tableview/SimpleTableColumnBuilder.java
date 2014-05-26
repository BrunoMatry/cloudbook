/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Builds a column representing a simple type (int, string, double etc...)
 * @author Gwendal
 */
public class SimpleTableColumnBuilder extends TableColumnBuilder {

    /**
     * Constructor
     * @param owner table to which this column belongs
     */
    public SimpleTableColumnBuilder(TableView owner) {
        super(owner);
    }
    
    /**
     * Set up the column with its specified name, bound property
     * and size computed from the number of columns in the table
     * @param name label of the column
     * @param propertyName name of the property which is bound to the column
     * @param columnCount number of columns
     * @return a column of name name, bound with the property propertyName and with intelligent size
     */
    @Override
    public TableColumn buildColumn(String name, String propertyName, int columnCount) {
        TableColumn column = new TableColumn(name);
        column.setCellValueFactory(new PropertyValueFactory(propertyName));
        column.prefWidthProperty().bind(owner.widthProperty().divide(columnCount));
        return column;
    }
}

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
 * @param <E> type of the object related to the table
 * @param <C> type of the column
 * builder
 * A table view which allow to resize columns according to the number of columns in the table
 */
public class TableColumnBuilder<E, C> {
    
    //table to which this column belongs
    protected TableView<E> owner; 
    
    /**
     * Constructor
     * @param owner table to which this column belongs
     */
    public TableColumnBuilder(TableView<E> owner) {
        this.owner = owner;
    }
    
    /**
     * Set up the column with its specified name, bound property
     * and size computed from the number of columns in the table
     * @param name label of the column
     * @param propertyName name of the property which is bound to the column
     * @param columnCount number of columns
     * @return a column of name name, bound with the property propertyName and with intelligent size
     */
    public TableColumn buildColumn(String name, String propertyName, int columnCount) {
        TableColumn column = new TableColumn(name);
        column.setCellValueFactory(new PropertyValueFactory(propertyName));
        column.prefWidthProperty().bind(owner.widthProperty().divide(columnCount));
        return column;
    }
    
    /**
     * Build a column representing an instance of the Object class.
     * The object is represented by its toString result.
     * @param name label of the column
     * @param propertyName name of the property which is bound to the column
     * @param columnCount number of columns
     * @return a column of name name, bound with the property propertyName and with intelligent size
     */
    public TableColumn buildColumnBasedOnToString(String name, String propertyName, int columnCount) {
        TableColumn column = buildColumn(name, propertyName, columnCount);
        column.setCellFactory(new Callback<TableColumn<E, C>, TableCell<E, C>>() {

            @Override
            public TableCell<E, C> call(TableColumn<E, C> p) {
                TableCell<E, C> cell = new TableCell<E, C>() {

                    @Override
                    protected void updateItem(C t, boolean bln) {
                        if(t != null) {
                            Text text = new Text(t.toString());
                            setGraphic(text);
                        }
                    }
                    
                };
                return cell;
            }
        });
        return column;
    }

}

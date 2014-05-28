package hmi.content.node.component.tableview;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * Builds columns which represent the toString result of the specified column type
 * @param <E> type related to the table
 * @param <C> type related to the column
 */
public class ToStringTableColumnBuilder<E, C> extends TableColumnBuilder {

    /**
     * Constructor
     * @param owner table to which this column belongs
     */
    public ToStringTableColumnBuilder(TableView owner) {
        super(owner);
    }
    
    /**
     * Build a column representing an instance of the Object class.
     * The object is represented by its toString result.
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

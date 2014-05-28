package hmi.content.node.component.tableview;

import controller.CloudImageRelation;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.node.Cloud;

/**
 * Builds a column with a cloud representation
 * @param <E> type of the table
 */
public class CloudTableColumnBuilder<E> extends TableColumnBuilder {

    /**
     * Constructor
     * @param owner table to which this column belongs
     */
    public CloudTableColumnBuilder(TableView owner) {
        super(owner);
    }

    /**
     * Sets up the column with its specified name, bound property
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
        column.setCellFactory(new Callback<TableColumn<E, Cloud>, TableCell<E, Cloud>>() {

            @Override
            public TableCell<E, Cloud> call(TableColumn<E, Cloud> p) {
                TableCell<E, Cloud> cell = new TableCell<E, Cloud>() {

                    @Override
                    protected void updateItem(Cloud t, boolean bln) {
                        if(t != null) {
                            ImageView iv = new ImageView();
                            CloudImageRelation binder = new CloudImageRelation(t);
                            binder.drive(iv.imageProperty());
                            setGraphic(iv);
                        }
                    }
                    
                };
                return cell;
            }
        });
        return column;
    }
    
}

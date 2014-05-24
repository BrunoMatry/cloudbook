package hmi.content.node.component.tableview;

import controller.CloudImageRelation;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.node.Cloud;
import model.node.Mesure;

/**
 * table representing a list of mesures
 */
public class MesureTableView extends TableView<Mesure> {
    
    //Columns corresponding to the important properties of Mesure
    protected TableColumn<Mesure, Cloud> cloudCol;
    protected TableColumn<Mesure, String> applicationNameCol;
    protected TableColumn<Mesure, String> dateCol;
    protected TableColumn<Mesure, Integer> value1Col;
    protected TableColumn<Mesure, Integer> value2Col;
    protected TableColumn<Mesure, Integer> value3Col;
    
    /**
     * Constructor
     */
    public MesureTableView() {
        TableColumnBuilder simpleBuilder = new SimpleTableColumnBuilder(this);
        applicationNameCol = simpleBuilder.buildColumn("Name", "applicationName", 6);
        buildPlatformLogo();
        dateCol = simpleBuilder.buildColumn("Date", "date", 6);
        value1Col = simpleBuilder.buildColumn("Value 1", "mesure1", 6);
        value2Col = simpleBuilder.buildColumn("Value 2", "mesure2", 6);
        value3Col = simpleBuilder.buildColumn("Value 3", "mesure3", 6);
        getColumns().setAll(
                applicationNameCol,
                cloudCol,
                dateCol,
                value1Col,
                value2Col,
                value3Col
        );
    }
    
    /**
     * Set up the cloud representation
     */
    private void buildPlatformLogo() {
        TableColumnBuilder simpleBuilder = new SimpleTableColumnBuilder(this);
        cloudCol = simpleBuilder.buildColumn("Cloud Platform", "cloud", 6);
        cloudCol.setCellFactory(new Callback<TableColumn<Mesure, Cloud>, TableCell<Mesure, Cloud>>() {

            @Override
            public TableCell<Mesure, Cloud> call(TableColumn<Mesure, Cloud> p) {
                TableCell<Mesure, Cloud> cell = new TableCell<Mesure, Cloud>() {

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
    }
    
}

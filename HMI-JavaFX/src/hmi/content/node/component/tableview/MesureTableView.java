package hmi.content.node.component.tableview;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.node.Mesure;

/**
 * table representing a list of mesures
 */
public class MesureTableView extends StandardizedTable<Mesure> {
    
    //Columns corresponding to the important properties of Mesure
    protected TableColumn<Mesure, Image> platformLogoCol;
    protected TableColumn<Mesure, String> applicationNameCol;
    protected TableColumn<Mesure, String> dateCol;
    protected TableColumn<Mesure, Integer> value1Col;
    protected TableColumn<Mesure, Integer> value2Col;
    protected TableColumn<Mesure, Integer> value3Col;
    
    /**
     * Constructor
     */
    public MesureTableView() {
        applicationNameCol = buildColumn("Name", "applicationName", 6);
        buildPlatformLogo();
        dateCol = buildColumn("Date", "date", 6);
        value1Col = buildColumn("Value 1", "mesure1", 6);
        value2Col = buildColumn("Value 2", "mesure2", 6);
        value3Col = buildColumn("Value 3", "mesure3", 6);
        getColumns().setAll(
                applicationNameCol,
                platformLogoCol,
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
        platformLogoCol = buildColumn("Cloud Platform", "platformLogo", 6);
        platformLogoCol.setCellFactory(new Callback<TableColumn<Mesure, Image>, TableCell<Mesure, Image>>() {

            @Override
            public TableCell<Mesure, Image> call(TableColumn<Mesure, Image> p) {
                TableCell<Mesure, Image> cell = new TableCell<Mesure, Image>() {

                    @Override
                    protected void updateItem(Image t, boolean bln) {
                        if(t != null) {
                            ImageView iv = new ImageView(t);
                            setGraphic(iv);
                        }
                    }
                    
                };
                return cell;
            }
        });
    }
    
}

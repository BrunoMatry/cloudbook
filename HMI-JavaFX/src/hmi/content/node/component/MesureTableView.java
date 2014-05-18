/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.node.Mesure;

/**
 *
 * @author Gwendal
 * table representing a list of mesures
 */
public class MesureTableView extends TableView<Mesure> {
    
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
        applicationNameCol = new TableColumn<>("Name");
        applicationNameCol.setCellValueFactory(new PropertyValueFactory("applicationName"));
        applicationNameCol.prefWidthProperty().bind(this.widthProperty().divide(6));
        buildPlatformLogo();
        dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));
        dateCol.prefWidthProperty().bind(this.widthProperty().divide(6));
        value1Col = new TableColumn<>("Value 1");
        value1Col.setCellValueFactory(new PropertyValueFactory("mesure1"));
        value1Col.prefWidthProperty().bind(this.widthProperty().divide(6));
        value2Col = new TableColumn<>("Value 2");
        value2Col.setCellValueFactory(new PropertyValueFactory("mesure2"));
        value2Col.prefWidthProperty().bind(this.widthProperty().divide(6));
        value3Col = new TableColumn<>("Value 3");
        value3Col.setCellValueFactory(new PropertyValueFactory("mesure3"));
        value3Col.prefWidthProperty().bind(this.widthProperty().divide(6));
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
        platformLogoCol = new TableColumn<>("Cloud Platform");
        platformLogoCol.setCellValueFactory(new PropertyValueFactory("platformLogo"));
        platformLogoCol.prefWidthProperty().bind(this.widthProperty().divide(6));
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import model.node.Cloud;
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
        platformLogoCol = new TableColumn<>("Cloud Platform");
        platformLogoCol.setCellValueFactory(new PropertyValueFactory("platformLogo"));
        dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory("date"));
        value1Col = new TableColumn<>("Value 1");
        value1Col.setCellValueFactory(new PropertyValueFactory("mesure1"));
        value2Col = new TableColumn<>("Value 2");
        value2Col.setCellValueFactory(new PropertyValueFactory("mesure2"));
        value3Col = new TableColumn<>("Value 3");
        value3Col.setCellValueFactory(new PropertyValueFactory("mesure3"));
        getColumns().setAll(
                applicationNameCol,
                platformLogoCol,
                dateCol,
                value1Col,
                value2Col,
                value3Col
        );
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.node.Cloud;
import model.node.State;

/**
 *
 * @author Gwendal
 */
public class StateTableView extends TableView<State> {
    
    protected TableColumn<State, Cloud> cloudCol;
    protected TableColumn<State, Date> fromCol;
    protected TableColumn<State, Date> toCol;
    protected TableColumn<State, Boolean> currentCol;
    
    /**
     * All clouds in which the application has ran
     */
    public StateTableView() {
        TableColumnBuilder simpleBuilder = new SimpleTableColumnBuilder(this);
        TableColumnBuilder dateBuilder = new ToStringTableColumnBuilder<State, Date>(this);
        TableColumnBuilder cloudBuilder = new CloudTableColumnBuilder<State>(this);
        cloudCol = cloudBuilder.buildColumn("Cloud platform", "cloud", 4);
        currentCol = simpleBuilder.buildColumn("Current ?", "current", 4);
        fromCol = dateBuilder.buildColumn("From", "from", 4);
        toCol = dateBuilder.buildColumn("To", "to", 4);
        getColumns().addAll(cloudCol, currentCol, fromCol, toCol);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.node.State;

/**
 *
 * @author Gwendal
 */
public class StateTableView extends TableView<State> {
    
    protected TableColumn<State, Integer> cloudCol;
    protected TableColumn<State, Date> fromCol;
    protected TableColumn<State, Date> toCol;
    protected TableColumn<State, Boolean> currentCol;
    
    /**
     * TODO
     */
    public StateTableView() {
        //currentCol = buildColumn("Current ?", "current", 4);
    }
}

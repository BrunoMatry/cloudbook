/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.node.friend.Friend;

/**
 *
 * @author Gwendal
 */
public class FriendTableView extends StandardizableTable<Friend> {
    
    //columns corresponding to the friend properties
    protected TableColumn<Friend, Integer> idCol;
    protected TableColumn<Friend, Double> relevanceCol;
    protected TableColumn<Friend, Integer> confidenceCol;
    protected TableColumn<Friend, Date> lastConnectionCol;
    
    /**
     * Constructor
     */
    public FriendTableView() {
        super();
        idCol = buildColumn("Id", "id", 4);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.node.friend.Friend;

/**
 *
 * @author Gwendal
 */
public class FriendTableView extends TableView<Friend> {
    
    //columns corresponding to the friend properties
    protected TableColumn<Friend, String> idCol;
    protected TableColumn<Friend, Double> relevanceCol;
    protected TableColumn<Friend, Integer> confidenceCol;
    protected TableColumn<Friend, Date> lastConnectionCol;
    
    /**
     * Constructor
     */
    public FriendTableView() {
        super();
        TableColumnBuilder idBuilder = new TableColumnBuilder<Friend, String>(this);
        idCol = idBuilder.buildColumn("Id", "id", 4);
        TableColumnBuilder rlvBuilder = new TableColumnBuilder<Friend, Double>(this);
        relevanceCol = rlvBuilder.buildColumn("Relevance", "relevance", 4);
        TableColumnBuilder confBuilder = new TableColumnBuilder<Friend, Integer>(this);
        confidenceCol = confBuilder.buildColumn("Confidence", "confidence", 4);
        TableColumnBuilder dateBuilder = new TableColumnBuilder<Friend, Date>(this);
        lastConnectionCol = dateBuilder.buildColumnBasedOnToString("Date of last connection", "lastConnection", 4);
        getColumns().addAll(idCol, relevanceCol, confidenceCol, lastConnectionCol);
    }
    
}

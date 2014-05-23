/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.node.AppVector;
import model.node.friend.Friend;

/**
 *
 * @author Gwendal
 */
public class FriendTableView extends TableView<Friend> {
    
    private static final int nbCol = 5;
    
    //columns corresponding to the friend properties
    protected TableColumn<Friend, String> idCol;
    protected TableColumn<Friend, Double> relevanceCol;
    protected TableColumn<Friend, Integer> confidenceCol;
    protected TableColumn<Friend, Date> lastConnectionCol;
    protected TableColumn<Friend, AppVector> appVectorCol;
    
    /**
     * Constructor
     */
    public FriendTableView() {
        super();
        TableColumnBuilder idBuilder = new TableColumnBuilder<Friend, String>(this);
        idCol = idBuilder.buildColumn("Id", "id", nbCol);
        TableColumnBuilder rlvBuilder = new TableColumnBuilder<Friend, Double>(this);
        relevanceCol = rlvBuilder.buildColumn("Relevance", "relevance", nbCol);
        TableColumnBuilder confBuilder = new TableColumnBuilder<Friend, Integer>(this);
        confidenceCol = confBuilder.buildColumn("Confidence", "confidence", nbCol);
        TableColumnBuilder dateBuilder = new TableColumnBuilder<Friend, Date>(this);
        lastConnectionCol = dateBuilder.buildColumnBasedOnToString("Date of last connection", "lastConnection", nbCol);
        TableColumnBuilder vectorBuilder = new TableColumnBuilder<Friend, AppVector>(this);
        appVectorCol = vectorBuilder.buildColumnBasedOnToString("Features", "appVector", 5);
        getColumns().addAll(idCol, relevanceCol, confidenceCol, lastConnectionCol, appVectorCol);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hmi.content.node.component.tableview;

import java.util.Date;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.network.interfaces.Information;
import model.node.Message;

/**
 *
 * @author Gwendal
 */
public class MessageTableView extends TableView<Message> {
    
    protected TableColumn<Message, String> idSenderCol;
    protected TableColumn<Message, Boolean> relevantCol;
    protected TableColumn<Message, Date> dateCol;
    protected TableColumn<Message, Information> contentCol;
    
    /**
     * Constructor
     */
    public MessageTableView() {
        TableColumnBuilder idBuilder = new TableColumnBuilder<Message, String>(this);
        idSenderCol = idBuilder.buildColumn("Id", "idSender", 4);
        TableColumnBuilder rlvBuilder = new TableColumnBuilder<Message, Boolean>(this);
        relevantCol = rlvBuilder.buildColumn("Relevant ?", "relevant", 4);
        TableColumnBuilder dateBuilder = new TableColumnBuilder<Message, Date>(this);
        dateCol = dateBuilder.buildColumnBasedOnToString("Date", "date", 4);
        TableColumnBuilder infoBuilder = new TableColumnBuilder<Message, Information>(this);
        contentCol = infoBuilder.buildColumn("Content", "content", 4);
        getColumns().addAll(idSenderCol, relevantCol, dateCol, contentCol);
    }
   
}
